package restful_booker_demo.api_objects;

import com.shaft.api.RestActions;
import io.restassured.response.Response;

public class ReqGETApi_Bookings {

    private RestActions apiObject;
    private final String booking_ServiceName = "booking/";

    public ReqGETApi_Bookings(RestActions apiObject) {
        this.apiObject = apiObject;
    }

    // since performRequest() returns a Response Object we can set the method to return the response
    // get all the existed bookings
    public Response getBookingIds(){
       return apiObject.buildNewRequest(booking_ServiceName,
                RestActions.RequestType.GET).performRequest();
    }
    // get the bookings By ID
    public Response getBookingIds(String bookingId) {
        return apiObject.buildNewRequest(booking_ServiceName + bookingId,
                RestActions.RequestType.GET)
                .performRequest();
    }
    // get the bookings By first name and last name
    public Response getBookingIds(String fn, String ln) {
        return apiObject.buildNewRequest(booking_ServiceName,
                RestActions.RequestType.GET)
                .setUrlArguments("firstname="+fn+"&lastname="+ln)
                .performRequest();
    }
    // get the bookings by checkin and checkout dates
    public Response getBookingByDate(String checkin, String checkout) {
        return apiObject.buildNewRequest(booking_ServiceName,
                RestActions.RequestType.GET)
                .setUrlArguments("checkin="+checkin +"&checkout"+checkout)
                .performRequest();
    }
}
