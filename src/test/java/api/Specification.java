package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {
    public static RequestSpecification requestSpec(String url) {

        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();

    }

    public static ResponseSpecification responseSpecOK200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }


    public static void InstallSpecification(RequestSpecification request, ResponseSpecification respone){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = respone;

    }

    public static ResponseSpecification responseSpecOK400() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }


    public static ResponseSpecification responseSpecUniqe(int status) {
        return new ResponseSpecBuilder()
                .expectStatusCode(240)
                .build();
    }

    public static ResponseSpecification responseSpecError400(){
        return null;
    }
}
    
