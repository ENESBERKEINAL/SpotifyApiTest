package specs;
import com.fasterxml.jackson.annotation.*;

public class Welcome {
    private Artists artists;

    @JsonProperty("artists")
    public Artists getArtists() { return artists; }
    @JsonProperty("artists")
    public void setArtists(Artists value) { this.artists = value; }
}
