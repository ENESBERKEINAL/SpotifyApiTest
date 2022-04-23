package specs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class artistDTO {
    @JsonProperty("artists")
    private Artists artists;

    public Artists getArtist() {
        return artists;
    }

    public void setArtist(Artists artists) {
        this.artists = artists;
    }

}
