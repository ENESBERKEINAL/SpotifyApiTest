import io.restassured.RestAssured;
import jdk.jfr.Description;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import specs.Item;
import specs.artistDTO;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SpotifyApiTest extends Service{


    @BeforeMethod
    public void beforeTest() throws IOException {
        RestAssured.baseURI = "https://api.spotify.com/v1";
    }

    @Test
    @Description("Check user detailed infos and create/delete some tracks for user")
    public void spotifyTest() throws IOException {
        shouldGetUserAllDetail();
        shouldCheckEmail(emailEnes);

        shouldGetUserId();

        artistDTO artist = shouldSearchArtist();
        List<Item> itemsList = artist.getArtist().getItems();
        String id = itemsList.get(0).getID();

        shouldGetTopTracksForSpesificID(id);

        shouldCreateNewPlaylist();

        String trackName0 = "Jimi Hendrix";
        String trackName1 = "The Knack";
        String trackName2 = "The Beatles";

        Assert.assertEquals(shouldGetPlaylistName(),"roadhouse blues");
        System.out.println("Play list Name: " + shouldGetPlaylistName());

        String trackUri0 = shouldGetTrackUriWithTrackName(trackName0);
        shouldAddItemsToPlaylist(trackUri0);

        String trackUri1 = shouldGetTrackUriWithTrackName(trackName1);
        shouldAddItemsToPlaylist(trackUri1);

        String trackUri2 = shouldGetTrackUriWithTrackName(trackName2);
        shouldAddItemsToPlaylist(trackUri2);

        assertEquals(trackUri0, shouldCheckIsItemAdded());

        shouldDeleteTrack(trackName0);




    }

    @Test(enabled = false)
    @Description("Get Auth token from spotify api for dynamic request flow")
    public void getAuthToken() throws IOException {
        Helper helper = new Helper();
        helper.shouldGetAuthTokenFromSpotifyApiUsingSecretKeys();
    }




}
