package api.restfullBooker.objectModels;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

@SuppressWarnings("ALL")
public class RestfulBookerApiBooking {

    private RestActions apiObject;
    private String booking_serviceName = "booking/";


    public RestfulBookerApiBooking(RestActions apiObject) {
        this.apiObject = apiObject;
    }

    public Response getBookingIds() {
        return apiObject.buildNewRequest(booking_serviceName, RestActions.RequestType.GET)
                .performRequest();
    }

    public Response getBookingIds(String bookingId) {
        return apiObject.buildNewRequest(booking_serviceName + bookingId, RestActions.RequestType.GET)
                .performRequest();
    }

    public Response getBookingIds(String fn, String ln) {
        return apiObject.buildNewRequest(booking_serviceName, RestActions.RequestType.GET)
                .setUrlArguments("firstname="+fn+"&lastname="+ln)
                .performRequest();
    }
    public Response deleteBooking(String bookingId) {
        return  apiObject.buildNewRequest(booking_serviceName + bookingId, RestActions.RequestType.DELETE)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(RestfulBookerAPI.SUCCESS_DELETE).performRequest();
    }

    public Response createBooking(String fn, String ln, int totalPrice, boolean depositpaid,
                                  String checkInDate, String checkOutDate, String additionalneeds) {
        //JSON objects

        // Request a POST method and sets the return value of it as a Response object so we can retrieve any attribute from it
        return apiObject.buildNewRequest(booking_serviceName, RestActions.RequestType.POST)
                .setContentType(ContentType.JSON)
                .setRequestBody(createBookingBody(fn, ln, totalPrice, depositpaid
                        , checkInDate, checkOutDate, additionalneeds))
                .performRequest();
    }
    private JSONObject createBookingBody(String fn, String ln, int totalPrice, boolean depositpaid,
                                         String checkInDate, String checkOutDate, String additionalneeds) {
        JSONObject createBookingBody = new JSONObject();
        createBookingBody.put("firstname", fn);
        createBookingBody.put("lastname", ln);
        createBookingBody.put("totalprice", totalPrice);
        createBookingBody.put("depositpaid", depositpaid);
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkInDate);
        bookingDates.put("checkout", checkOutDate);
        createBookingBody.put("bookingdates", bookingDates);
        createBookingBody.put("additionalneeds", additionalneeds);
        return createBookingBody;
    }
}

