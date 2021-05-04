package restAssured_restful_booker;

import api.restfullBooker.objectModels.RestfulBookerAPI;
import api.restfullBooker.objectModels.RestfulBookerApiBooking;
import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Assertions;
import com.shaft.validation.Verifications;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RestfulBooker {

    private RestActions apiObject;
    private RestfulBookerAPI restfulBookerAPI;
    private RestfulBookerApiBooking restfulBookerApiBooking;

    @BeforeClass
    void beforeClass() {
        apiObject = DriverFactory.getAPIDriver(RestfulBookerAPI.Base_URL);
        restfulBookerAPI = new RestfulBookerAPI(apiObject);
        restfulBookerApiBooking = new RestfulBookerApiBooking(apiObject);

        restfulBookerAPI.login("admin", "password123");
    }

    @Test
    public void getBookingIds() {
        restfulBookerApiBooking.getBookingIds();
    }
    @Test
    public void getBookingWithId() {
        restfulBookerApiBooking.getBookingIds("1");
    }
    @Test
    public void createBooking() {
        Response createBookingRes =
                restfulBookerApiBooking.createBooking("Mazen","Ahmed",1000, true,
                "2020-01-01","2021-01-01","MoltenCake");
        String bookingId = RestActions.getResponseJSONValue(createBookingRes, "bookingid");

        Response getBookingRes = restfulBookerApiBooking.getBookingIds(bookingId);

        //Get the created booking values
        // Retrieve the Json value from the response and convert it to String so we can use it
        String fn = RestActions.getResponseJSONValue(getBookingRes, "firstname");
        String ln = RestActions.getResponseJSONValue(getBookingRes, "lastname");
        String checkin = RestActions.getResponseJSONValue(getBookingRes, "bookingdates.checkin");
        String checkout = RestActions.getResponseJSONValue(getBookingRes, "bookingdates.checkout  ");
        String additionalneeds = RestActions.getResponseJSONValue(getBookingRes, "additionalneeds");
        String totalPrice = RestActions.getResponseJSONValue(getBookingRes, "totalprice");

        // Check if the return value of the firstname attribute in the response equals to the value we send in the POST method
        Assertions.assertEquals("Mazen", fn);
        Assertions.assertEquals("Ahmed", ln);
        Assertions.assertEquals("2020-01-01", checkin);
        Assertions.assertEquals("2021-01-01", checkout);
        Assertions.assertEquals("MoltenCake", additionalneeds);
        Assertions.assertEquals("1000", totalPrice);

        //Assert JSON file content it compares the response body to an existed file in the project

        Assertions.assertJSONFileContent
                (getBookingRes,
                        System.getProperty("jsonFolderPath") + "restful_booker/booking.json");
        // Verifications are soft assertions which means it will continue the script if it fails
        // mainly used when we have more than 2 validations
        // but Assertions are Hard assertions which means it will block once the first assertions fail happens

        Verifications.verifyEquals("Mazen", fn);
        Verifications.verifyEquals("Ahmed", ln);
        Verifications.verifyEquals("2020-01-01", checkin);
        Verifications.verifyEquals("2021-01-01", checkout);
        Verifications.verifyEquals("MoltenCake", additionalneeds);
        Verifications.verifyEquals("1000", totalPrice);
    }

    @Test(dependsOnMethods = {"createBooking"})
    void deleteBooking() {
        // Get booking ID

        Response getBookingId = restfulBookerApiBooking.getBookingIds("Mazen","Ahmed");
        String bookingID = RestActions.getResponseJSONValue(getBookingId, "bookingid[0]");

        // Delete Request

        Response deleteBooking = restfulBookerApiBooking.deleteBooking(bookingID);

        // Validations
        String deleteBookingBody = RestActions.getResponseBody(deleteBooking);
        Assertions.assertEquals("Created",deleteBookingBody);
    }
}
