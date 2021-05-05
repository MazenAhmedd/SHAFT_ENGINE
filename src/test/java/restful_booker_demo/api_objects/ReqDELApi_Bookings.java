package restful_booker_demo.api_objects;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqDELApi_Bookings {

    private RestActions apiObject;
    private String booking_serviceName = "booking/";
    public ReqDELApi_Bookings(RestActions apiObject) {
        this.apiObject = apiObject;
    }

    public Response deleteBooking(String bookingId) {
        return  apiObject.buildNewRequest(booking_serviceName + bookingId, RestActions.RequestType.DELETE)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(AuthenticationObject.SUCCESS_DELETE).performRequest();
    }
}
