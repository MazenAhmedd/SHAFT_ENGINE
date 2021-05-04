package restAssured_restful_booker;

import com.shaft.api.RestActions;
import com.shaft.api.RestActions.RequestType;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Assertions;
import com.shaft.validation.Verifications;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SuppressWarnings("ALL")
public class restful_booker_linearDesign {

    private RestActions apiObject;

    @BeforeClass
    void beforeClass() {
        apiObject = DriverFactory.getAPIDriver("https://restful-booker.herokuapp.com/");

        // Authentication
        JSONObject authentication = new JSONObject();
        authentication.put("username", "admin");
        authentication.put("password", "password123");
        Response createToken =
                apiObject.buildNewRequest("auth", RequestType.POST)
                        .setContentType(ContentType.JSON)
                        .setRequestBody(authentication)
                        .performRequest();
        String token = RestActions.getResponseJSONValue(createToken, "token");
        //Append a header to the current session to be used in all the following requests
        //commonly used for authentication tokens.
        apiObject.addHeaderVariable("Cookie", "token=" + token);

    }

    @Test
    void getBookingIds() {
        apiObject.buildNewRequest("booking", RequestType.GET).performRequest();
    }

    @Test
    void getBookingWithID() {
        apiObject.buildNewRequest("booking/" + "1", RequestType.GET).performRequest();
    }

    @Test
    void createBooking() {
        JSONObject createBookingBody = new JSONObject();
        createBookingBody.put("firstname", "Mazen");
        createBookingBody.put("lastname", "Ahmed");
        createBookingBody.put("totalprice", 10000);
        createBookingBody.put("depositpaid", true);
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2020-01-01");
        bookingDates.put("checkout", "2021-01-01");
        createBookingBody.put("bookingdates", bookingDates);
        createBookingBody.put("additionalneeds", "MoltenCakesEveryMorning");
        // Request a POST method and sets the return value of it as a Response object so we can retrieve any attribute from it
        Response createBookingResponse = apiObject.buildNewRequest("booking", RequestType.POST)
                .setContentType(ContentType.JSON)
                .setRequestBody(createBookingBody)
                .performRequest();
        // Retrieve the booking ID from the response and convert it to String
        String bookingId = RestActions.getResponseJSONValue(createBookingResponse, "bookingid");

        // Request a new GET method with the booking ID that already generated from the previous POST request
        // Set the Return value of the GET Request to a response so we can retrieve any attribute from it
        Response getBookingRes =
                apiObject.buildNewRequest("booking/" + bookingId, RequestType.GET)
                        .performRequest();
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
        Assertions.assertEquals("MoltenCakesEveryMorning", additionalneeds);
        Assertions.assertEquals("10000", totalPrice);

        //Assert JSON file content it compares the response body to an existed file in the project

        Assertions.assertJSONFileContent
                (getBookingRes,
                        System.getProperty("jsonFolderPath") + "restful_booker/booking.json");
        // Verifications are soft assertions which means it will continue the script if it fails
        // mainly used when we have more than 2 validations
        // but Assertions are Hard assertions which means it will block once the first asserstion fail happens

        Verifications.verifyEquals("Mazen", fn);
        Verifications.verifyEquals("Ahmed", ln);
        Verifications.verifyEquals("2020-01-01", checkin);
        Verifications.verifyEquals("2021-01-01", checkout);
        Verifications.verifyEquals("MoltenCakesEveryMorning", additionalneeds);
        Verifications.verifyEquals("10000", totalPrice);
    }

    @Test(dependsOnMethods = {"createBooking"})
    void deleteBooking() {
        // Get booking ID

        Response getBookingId = apiObject.buildNewRequest("booking/", RequestType.GET)
                .setUrlArguments("firstname=Mazen&lastname=Ahmed")
                .performRequest();

        String bookingID = RestActions.getResponseJSONValue(getBookingId, "bookingid[0]");

        // Delete Request

        Response deleteBooking = apiObject.buildNewRequest("booking/" + bookingID, RequestType.DELETE)
                .setContentType(ContentType.JSON)
//                we will use the  apiObject.addHeaderVariable() in the beforeClass() instead
//                .addHeader("Cookie", "token=" + token)
                .setTargetStatusCode(201)
                .performRequest();

        String deleteBookingBody = RestActions.getResponseBody(deleteBooking);
        Assertions.assertEquals("Created",deleteBookingBody);
    }
}
