import com.google.common.io.Resources;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import specs.RequestSpec;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class Service extends RequestSpec {

    public String emailEnes = "enesberke2@gmail.com";
    private String userId;
    private String playlistId;
    private String token = "BQA70RbHmqGu-ZXFwy7P2myeb66kuOuHqKPZrObNirvTHZBCpBIhPB0tN5x8BjWIso_gKG9-OYsPWP5KueS8tLRxQR_U0O1nkRQQIUeu_EXyg5MaCizxluTbkvi7y_KyUlqwVyZ_8BucAkaPKSbLtJsGEcFLQkB3rkVuN4Yr5BX5NZsb5yQU-FJDdqEUQFaMz2C2nGZ1FhxctWfCm7IgAHOtB_6aVmk5_iKLgMHGz_boyy1FRzfrpVMLxRrQPAL0JFT6Mo53fUI2qf8ZkroePtcEQJWb3J_tytbstSS0";

    public void getUserAllDetail(){
        Response response =
                given()
                        .contentType("application/json; charset=UTF-8")
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get("/me")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        String country =  response.getBody().jsonPath().getString("country");
        String email = response.getBody().jsonPath().getString("email");
        String product = response.getBody().jsonPath().getString("product");
        System.out.println("Country = " + country + "\n Email = " + email + " \n Product= "+ product);
    }
    public void checkEmail(String emailEnes) {
        Response response =
                given()
                        .contentType("application/json; charset=UTF-8")
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get("/me")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        String email = response.getBody().jsonPath().getString("email");
        Assert.assertEquals(email,emailEnes);
        System.out.println("E-mail checked");
    }
    public void getUserId() {
        Response response =
                given()
                        .contentType("application/json; charset=UTF-8")
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get("/me")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        userId =  response.getBody().jsonPath().getString("id");
    }

    public String searchArtist() {
        Response response =
                given()
                        .contentType("application/json; charset=UTF-8")
                        .header("Authorization", "Bearer " + token)
                        .queryParam("q","roadhouse blues")
                        .queryParam("type","artist")
                        .when()
                        .get("/search")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        System.out.println(response.getBody().jsonPath().getString("uri"));
        return response.getBody().jsonPath().getString("uri");
    }

    public void topTracks(String id) {
        Response response =
                given()
                        .contentType("application/json; charset=UTF-8")
                        .header("Authorization", "Bearer " + token)
                        .queryParam("market","TR")
                        .when()
                        .get("artists/{id}/top-tracks",id)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
    }

    public void createNewPlaylist() throws IOException {

        URL file = Resources.getResource("playListBody.json");
        String myJson = Resources.toString(file, Charset.defaultCharset());
        JSONObject json = new JSONObject(myJson);

        Response playlistResponse =
                given()
                        .contentType("application/json; charset=UTF-8")
                        .header("Authorization", "Bearer " + token)
                        .body(json.toString())
                        .when()
                        .post("/users/{userId}/playlists",userId)
                        .then()
                        .statusCode(201)
                        .extract()
                        .response();

        playlistId = playlistResponse.getBody().jsonPath().getString("id");

    }
    public String getPlaylistName(){
        Response nameResponse =
                given()
                        .contentType("application/json; charset=UTF-8")
                        .header("Authorization", "Bearer " + token)
                        .queryParam("playlist_id", playlistId)
                        .when()
                        .get("playlists/{playlist_id}",playlistId)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        return nameResponse.getBody().jsonPath().getString("name");
    }

    public String getTrackUri(String trackName){
        Response trackUriResponse =
                given()
                        .contentType("application/json; charset=UTF-8")
                        .header("Authorization", "Bearer " + token)
                        .queryParam("q",trackName )
                        .queryParam("type", "track")
                        .queryParam("market", "US")
                        .queryParam("limit","1")
                        .when()
                        .get("search")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        ArrayList arrayList = trackUriResponse.path("tracks.items.uri");
        return arrayList.get(0).toString();
    }

    public void addItemsToPlaylist(String trackUri){
        given()
                .spec(super.getRequestSpecification())
                .contentType("application/json; charset=UTF-8")
                .header("Authorization", "Bearer " + token)
                .queryParam("playlist_id",playlistId)
                .queryParam("uris",trackUri)
                .when()
                .post("playlists/{playlist_id}/tracks",playlistId)
                .then()
                .statusCode(201);
    }
    public String isItemAdded(){
        Response itemResponse =
                given()
                        .contentType("application/json; charset=UTF-8")
                        .header("Authorization", "Bearer " + token)
                        .queryParam("playlist_id", playlistId)
                        .queryParam("market", "TR")
                        .queryParam("limit", "1")
                        .when()
                        .get("playlists/{playlist_id}/tracks",playlistId)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        ArrayList arraylist =  itemResponse.path("items.track.uri");
        return arraylist.get(0).toString();
    }


    public void deleteTrack(String trackName) throws IOException {

        JSONObject json = new JSONObject().put("tracks",trackName);

        URL file = Resources.getResource("deletetrackBody.json");
        String myJson = Resources.toString(file, Charset.defaultCharset());;

        given()
                .contentType("application/json; charset=UTF-8")
                .header("Authorization", "Bearer " + token)
                .queryParam("playlist_id", playlistId)
                .body(json.toString())
                .when()
                .delete("playlists/{playlist_id}/tracks",playlistId)
                .then()
                .statusCode(200);

    }



}
