package specs;
import com.fasterxml.jackson.annotation.*;
import java.util.List;

public class Item {
    private ExternalUrls externalUrls;
    private Followers followers;
    private List<String> genres;
    private String href;
    private String id;
    private List<Image> images;
    private String name;
    private long popularity;
    private String type;
    private String uri;

    @JsonProperty("external_urls")
    public ExternalUrls getExternalUrls() { return externalUrls; }
    @JsonProperty("external_urls")
    public void setExternalUrls(ExternalUrls value) { this.externalUrls = value; }

    @JsonProperty("followers")
    public Followers getFollowers() { return followers; }
    @JsonProperty("followers")
    public void setFollowers(Followers value) { this.followers = value; }

    @JsonProperty("genres")
    public List<String> getGenres() { return genres; }
    @JsonProperty("genres")
    public void setGenres(List<String> value) { this.genres = value; }

    @JsonProperty("href")
    public String getHref() { return href; }
    @JsonProperty("href")
    public void setHref(String value) { this.href = value; }

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("images")
    public List<Image> getImages() { return images; }
    @JsonProperty("images")
    public void setImages(List<Image> value) { this.images = value; }

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("popularity")
    public long getPopularity() { return popularity; }
    @JsonProperty("popularity")
    public void setPopularity(long value) { this.popularity = value; }

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("uri")
    public String getURI() { return uri; }
    @JsonProperty("uri")
    public void setURI(String value) { this.uri = value; }
}
