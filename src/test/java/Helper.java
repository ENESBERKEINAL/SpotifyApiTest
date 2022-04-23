import java.io.IOException;

public class Helper extends Service {


    public String shouldGetAuthTokenFromSpotifyApiUsingSecretKeys() throws IOException {
    String bearerToken = shouldGetBearerToken();

    System.out.println("Bearer token: " + bearerToken);
        return bearerToken;
    }
}
