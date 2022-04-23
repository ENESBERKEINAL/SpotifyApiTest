import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.io.Resources;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import specs.MeDTOs.SpotifyMeDTO;
import specs.RequestSpec;
import specs.artistDTO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class Service extends RequestSpec {

    public String emailEnes = "enesberke2@gmail.com";
    private String userId;
    private String playlistId;
    private final String Username = "Your-Client ID";
    private final String Password = "Your-Client Secret";

    public void shouldGetUserAllDetail(){
        Type type = TypeFactory.defaultInstance().constructType(SpotifyMeDTO.class);

        SpotifyMeDTO response =
                given()
                        .spec(super.getRequestSpecification())
                        .when()
                        .get("/me")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response().as(type);
        String country =  response.getCountry();
        String email = response.getEmail();
        String product = response.getProduct();

        System.out.println("Country = " + country + "\n Email = " + email + " \n Product= "+ product);
    }
    public void shouldCheckEmail(String emailEnes) {
        Type type = TypeFactory.defaultInstance().constructType(SpotifyMeDTO.class);

        SpotifyMeDTO response =
                given()
                        .spec(super.getRequestSpecification())
                        .when()
                        .get("/me")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response().as(type);

        String email = response.getEmail();
        Assert.assertEquals(email,emailEnes);
        System.out.println("E-mail checked, E-mail is -> "+ email );
    }
    public void shouldGetUserId() {
        Type type = TypeFactory.defaultInstance().constructType(SpotifyMeDTO.class);

        SpotifyMeDTO response =
                given()
                        .spec(super.getRequestSpecification())
                        .when()
                        .get("/me")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response().as(type);
        userId = response.getID();
    }

    public artistDTO shouldSearchArtist() {
        Type type = TypeFactory.defaultInstance().constructType(artistDTO.class);
        artistDTO response =
                given()
                        .spec(super.getRequestSpecification())
                        .queryParam("q","roadhouse blues")
                        .queryParam("type","artist")
                        .when()
                        .get("/search")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response().as(type);
        return response;
    }

    public void shouldGetTopTracksForSpesificID(String id) {
        Response response =
                given()
                        .spec(super.getRequestSpecification())
                        .queryParam("market","TR")
                        .when()
                        .get("artists/{id}/top-tracks",id)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
    }

    public void shouldCreateNewPlaylist() throws IOException {

        URL file = Resources.getResource("playListBody.json");
        String myJson = Resources.toString(file, Charset.defaultCharset());
        JSONObject json = new JSONObject(myJson);

        Response playlistResponse =
                given()
                        .spec(super.getRequestSpecification())
                        .body(json.toString())
                        .when()
                        .post("/users/{userId}/playlists",userId)
                        .then()
                        .statusCode(201)
                        .extract()
                        .response();

        playlistId = playlistResponse.getBody().jsonPath().getString("id");
        System.out.println("Playlist ID: " + playlistId);
    }

    public String shouldGetPlaylistName(){
        Response nameResponse =
                given()
                        .spec(super.getRequestSpecification())
                        .queryParam("playlist_id", playlistId)
                        .when()
                        .get("playlists/{playlist_id}",playlistId)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        return nameResponse.getBody().jsonPath().getString("name");
    }

    public String shouldGetTrackUriWithTrackName(String trackName){
        Response trackUriResponse =
                given()
                        .spec(super.getRequestSpecification())
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
        System.out.println("Tracks Items URI: " + arrayList.get(0).toString());
        return arrayList.get(0).toString();
    }

    public void shouldAddItemsToPlaylist(String trackUri){
        Response response =
        given()
                .spec(super.getRequestSpecification())
                .queryParam("uris",trackUri)
                .when()
                .post("playlists/{playlist_id}/tracks",playlistId)
                .then()
                .extract()
                .response();


        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        String snapshotID = jsonObject.get("snapshot_id").toString();

        System.out.println("Snapshot_id : " + snapshotID);
    }
    public String shouldCheckIsItemAdded(){
        Response itemResponse =
                given()
                        .spec(super.getRequestSpecification())
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


    public void shouldDeleteTrack(String trackName) throws IOException {

        URL file = Resources.getResource("deletetrackBody.json");
        String myJson = Resources.toString(file, Charset.defaultCharset());;
        JSONObject deleteBody = new JSONObject(myJson);
        deleteBody.getJSONArray("tracks").getJSONObject(0).put("uri", shouldGetTrackUriWithTrackName(trackName));

        given()
                .spec(super.getRequestSpecification())
                .queryParam("playlist_id", playlistId)
                .body(deleteBody.toString())
                .when()
                .delete("playlists/{playlist_id}/tracks",playlistId)
                .then()
                .statusCode(200);

        System.out.println("Successfully track deleted ");

    }

    public String shouldGetBearerToken() throws JSONException {
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri("https://accounts.spotify.com/api/token").build();
        Response response =
                given()
                        .spec(spec)
                        .auth().preemptive().basic(Username, Password)
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("grant_type", "client_credentials")
                        //Can't Access to token with this scopes for
                        .formParam("scope", "user-read-private, user-read-email")
                .when()
                .post();

        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        System.out.println(" Token object for auth token: "+jsonObject + response.getBody().asString());
        String accessToken = jsonObject.get("access_token").toString();
        String tokenType = jsonObject.get("token_type").toString();
        System.out.println("Auth Token with type: " + tokenType +  "  AuthToken: " + accessToken);
        return accessToken;
    }

}
