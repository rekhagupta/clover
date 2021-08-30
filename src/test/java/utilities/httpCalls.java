package utilities;

import static io.restassured.RestAssured.given;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

public class httpCalls {

    //This is the call when Type=GET
    public static Response HTTPRequest(String url, Map<String, String> headerMap, String Type, Map<String, String> paramMap, String path){
        Response res = null;
        if(Type.trim().equalsIgnoreCase("GET")){
            res = given()
                .contentType(ContentType.JSON)
                .headers(headerMap)
                .formParams(paramMap)
                .when()
                .get(url + path)
                .then()
                .extract().response();
        }
        return res;
    }
    //Overloaded function for HTTPRequest for Post when <body> may be passed : NOT completed or tested
    public static Response HTTPRequest(String url,  Map<String, String> headersMap, String Type, Map<String, String> paramMap, String path, String body){
        Response res = null;
        if(Type.trim().equalsIgnoreCase("POST")){
            res = given()
                    .contentType(ContentType.JSON)
                    .headers(headersMap)
                    .formParams(paramMap)
                    .when()
                    .body(body)
                    .post(url + path)
                    .then()
                    .extract().response();
        }
        return res;
    }
}
