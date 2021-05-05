package restful_booker_demo.api_objects;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class ReqPOSTAPI_createBooking {


    private RestActions apiObject;
    private String Create_ServiceName = "booking";

    public ReqPOSTAPI_createBooking(RestActions apiObject) {
        this.apiObject = apiObject;
    }

    public Response createBooking (String fn, String ln, int totalPrice,
                                   boolean depositpaid, String checkin,
                                   String checkout, String addOns) {
        JSONObject bookingBody = new JSONObject();
        bookingBody.put("firstname", fn);
        bookingBody.put("lastname", ln);
        bookingBody.put("totalprice", totalPrice);
        bookingBody.put("depositpaid", depositpaid);
        // booking dates are a json body inside the booking json body
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);
        bookingBody.put("bookingdates", bookingDates);
        bookingBody.put("additionalneeds", addOns);

        return apiObject.buildNewRequest(Create_ServiceName,
                RestActions.RequestType.POST).setContentType
                (ContentType.JSON)
                .setRequestBody(bookingBody)
                .performRequest();
    }

}
