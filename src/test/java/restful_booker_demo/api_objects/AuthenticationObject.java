package restful_booker_demo.api_objects;

import com.shaft.api.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class AuthenticationObject {

    // 1- create object of the RestActions class to use in the different Objects
    private RestActions apiObject;
    // 2- create an instructor of the api object
    public AuthenticationObject(RestActions apiObject) {
        this.apiObject = apiObject;
    }
    // 3- create the base URI as static value
    public static final String Base_URL = "https://restful-booker.herokuapp.com/";
    // 4- declare the response code that are often used
    public static final int SUCCESS_CODE = 200;
    public static final int SUCCESS_DELETE = 201;
    // 5- create a String variable for the service name
    private String auth_serviceName = "auth";
    // 6- create the method that creates the token
    public void login (String userName , String password){
        JSONObject AuthenticationBody = new JSONObject();
        AuthenticationBody.put("username", userName);
        AuthenticationBody.put("password", password);


        //build request takes a service name and the request type
        Response createToken = apiObject.buildNewRequest(auth_serviceName,
                RestActions.RequestType.POST)
                // set the content type if its mandatory to be set in the post REQ
                .setContentType(ContentType.JSON)
                // set the request body we can pass the parameters directly on the body
                // or we pass it as JSONObject
                .setRequestBody(AuthenticationBody)
                // at the end of each Request we should use the performRequest method
                .performRequest();

        // now since we had our POST request done we need the return value of the request (the token)
        // so we can use it in the following requests so we should take the return value as Response
        // so we can extract a value from the response and retrieve it as a String
        String token = RestActions.getResponseJSONValue(createToken, "token");

        // since we want to set the returned value ( token ) as a header in all the following requests
        // we can use this method addHeaderVariable
        apiObject.addHeaderVariable("Cookie", "token=" + token);
    }

}
