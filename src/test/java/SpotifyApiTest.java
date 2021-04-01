import io.restassured.RestAssured;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SpotifyApiTest extends Service{


    @BeforeMethod
    public void beforeTest() throws IOException {
        RestAssured.baseURI = "https://api.spotify.com/v1";
    }

    @Test
    public void spotifyTest() throws IOException {
        getUserAllDetail();
        checkEmail(emailEnes);

        getUserId();

        String id = searchArtist();
        id = "43ZHCT0cAZBISjO8DG9PnE";
        topTracks(id);


        createNewPlaylist();

        String trackName0 = "Jimi Hendrix";
        String trackName1 = "The Knack";
        String trackName2 = "The Beatles";

        Assert.assertEquals(getPlaylistName(),"roadhouse blues");
        System.out.println(getPlaylistName());

        String trackUri0 = getTrackUri(trackName0);
        addItemsToPlaylist(trackUri0);

        String trackUri1 = getTrackUri(trackName1);
        addItemsToPlaylist(trackUri1);

        String trackUri2 = getTrackUri(trackName2);
        addItemsToPlaylist(trackUri2);

        assertEquals(trackUri0,isItemAdded());

        deleteTrack(trackName0);




    }




}
