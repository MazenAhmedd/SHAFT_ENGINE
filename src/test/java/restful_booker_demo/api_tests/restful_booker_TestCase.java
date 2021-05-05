package restful_booker_demo.api_tests;

import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Assertions;
import com.shaft.validation.Verifications;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import restful_booker_demo.api_objects.AuthenticationObject;
import restful_booker_demo.api_objects.ReqDELApi_Bookings;
import restful_booker_demo.api_objects.ReqGETApi_Bookings;
import restful_booker_demo.api_objects.ReqPOSTAPI_createBooking;

public class restful_booker_TestCase {

    private RestActions apiObject;
    private AuthenticationObject restfullBookerAPIObject;
    private ReqGETApi_Bookings reqApi_getBookings;
    private ReqDELApi_Bookings reqDELApi_bookings;
    private ReqPOSTAPI_createBooking reqPOSTAPI_createBooking;

    @BeforeClass
    void beforeClass() {
        apiObject = DriverFactory.getAPIDriver(AuthenticationObject.Base_URL);
        restfullBookerAPIObject = new AuthenticationObject(apiObject);
        reqApi_getBookings = new ReqGETApi_Bookings(apiObject);
        reqDELApi_bookings = new ReqDELApi_Bookings(apiObject);
        reqPOSTAPI_createBooking = new ReqPOSTAPI_createBooking(apiObject);
        restfullBookerAPIObject.login("admin", "password123");

    }
    @Test
    void getAllBookings(){
        reqApi_getBookings.getBookingIds();
    }
    @Test
    void getBookingById(){
        reqApi_getBookings.getBookingIds("1");
    }
    @Test(dependsOnMethods = {"getBookingById","getAllBookings"})
    void deleteBookingById(){
       Response deleteBody = reqDELApi_bookings.deleteBooking("1");
       String deletedResMessage = RestActions.getResponseBody(deleteBody);
        Assertions.assertEquals("Created"
        ,deletedResMessage);
    }
    @Test
    void createBooking_getBooking_DelBookingById(){
        //Create new booking
      Response NewBookingRes = reqPOSTAPI_createBooking.createBooking
              ("Mazen","Ahmed", 10000,
                true,"2021-12-18","2021-12-19",
                "Every Thing you have ");
        String bookingId = RestActions.getResponseJSONValue
                (NewBookingRes,"bookingid");
        // get the created booking with the generated ID
        Response createdBookingRes = reqApi_getBookings.getBookingIds(bookingId);
        //set the return values in the response as String so we can assert on them
        String fn =RestActions.getResponseJSONValue
                (createdBookingRes,"firstname");
        String ln =RestActions.getResponseJSONValue
                (createdBookingRes,"lastname");
        String price =RestActions.getResponseJSONValue
                (createdBookingRes,"totalprice");
        String depositpaid =RestActions.getResponseJSONValue
                (createdBookingRes,"depositpaid");
        String checkin =RestActions.getResponseJSONValue
                (createdBookingRes,"bookingdates.checkin");
        String checkout =RestActions.getResponseJSONValue
                (createdBookingRes,"bookingdates.checkout");
        String addsOn =RestActions.getResponseJSONValue
                (createdBookingRes,"additionalneeds");
        //Assertions on the Strings we returned
        Verifications.verifyEquals("Mazen",fn);
        Verifications.verifyEquals("Ahmed",ln);
        Verifications.verifyEquals("10000",price);
        Verifications.verifyEquals("true",depositpaid);
        Verifications.verifyEquals("2021-12-18",checkin);
        Verifications.verifyEquals("2021-12-19",checkout);
        Verifications.verifyEquals("Every Thing you have ",addsOn);

        Response getBookingId = reqApi_getBookings.getBookingIds("Mazen","Ahmed");
        String bookingID = RestActions.getResponseJSONValue
                (getBookingId, "bookingid[0]");
        reqApi_getBookings.getBookingIds(bookingID);
        // delete the created booking with the generated ID
        reqDELApi_bookings.deleteBooking(bookingID);
    }
}
