package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {
    RequestSpecification requestSpecification;
    private String baseUrl = "https://api.spotify.com/v1";
    private String token = "BQCGyyUAQTU5T6bUaGyV16f6x2LKyMbyBehx4vo-J9y9CqMhoTZRCS2GtPtYJf0X9KElscKpEh3Tlr5tVBSVEs8zf9Fi1CMU8wUqJg2RT-NMWxv48Fxwoz_Pi91fnZDaX_lY6OI1AS0nTD5YwZJVPB_fW5T_EJfUOTfS2NJSJ3qofjRyQlVXkRUGZd5vEqCHzUyWJf9MW5SMcTzA_krOguLXj4BYSd-j9q-fJ7RrBqZ6W7XTdKbt3b1XFuMP6--urcOKrkF0tZFSRtaAAbZNmb3BWlqjAcmLyIHGSAAC";
    public RequestSpec(){
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addHeader("Authorization","Bearer " +token)
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
}
