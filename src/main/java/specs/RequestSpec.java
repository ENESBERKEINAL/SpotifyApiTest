package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {
    RequestSpecification requestSpecification;
    private String baseUrl = "https://api.spotify.com/v1";
    private String token = "BQAa9qR8U3TzYZeHI2NHXCSCCfOI79f3ozHaFQpsJorszaDVME2QkZjZ1C5buqwNvqIuTzdjNX_U7tF8EitRKeJ5_xh03AlJrppd89Rnf4rA-Xq0vh8tw7ybZBI1_dzf4LojOpmUa9ToX6EvfkMa_TPgI3cnsWyjZOr3YVvoROSAYt5Z29nhksilF3UtyUVlBwbRWffGbsy5AJ8zV011NBiC_CEQ7O7PDucDhw2qOlzjeaiO6ulg4eJDpAospsx5RDL66wvUbJ0BnyuztuVL7VlRysBTxKlBqTllABEE";
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
