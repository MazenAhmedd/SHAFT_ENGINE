package api.restfullBooker.objectModels;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

@SuppressWarnings("ALL")
public class RestfulBookerAPI {

    private RestActions apiObject;
    private String auth_serviceName = "auth";
    public static final String Base_URL = System.getProperty("baseUrl");

    public static final int SUCCESS = 200;
    public static final int SUCCESS_DELETE = 201;

    public RestfulBookerAPI(RestActions apiObject) {
        this.apiObject = apiObject;
    }

    public void login(String userName, String password) {
        JSONObject authentication = new JSONObject();
        authentication.put("username", userName);
        authentication.put("password", password);
        Response createToken =
                apiObject.buildNewRequest(auth_serviceName, RestActions.RequestType.POST)
                        .setContentType(ContentType.JSON)
                        .setRequestBody(authentication).performRequest();
        String token = RestActions.getResponseJSONValue(createToken, "token");
        //Append a header to the current session to be used in all the following requests
        //commonly used for authentication tokens.
        apiObject.addHeaderVariable("Cookie", "token=" + token);
    }
}
