package specs.MeDTOs;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

public class SpotifyMeDTO {
    private String country;
    private String displayName;
    private String email;
    private ExplicitContent explicitContent;
    private ExternalUrls externalUrls;
    private Followers followers;
    private String href;
    private String id;
    private List<Object> images;
    private String product;
    private String type;
    private String uri;

    @JsonProperty("country")
    public String getCountry() { return country; }
    @JsonProperty("country")
    public void setCountry(String value) { this.country = value; }

    @JsonProperty("display_name")
    public String getDisplayName() { return displayName; }
    @JsonProperty("display_name")
    public void setDisplayName(String value) { this.displayName = value; }

    @JsonProperty("email")
    public String getEmail() { return email; }
    @JsonProperty("email")
    public void setEmail(String value) { this.email = value; }

    @JsonProperty("explicit_content")
    public ExplicitContent getExplicitContent() { return explicitContent; }
    @JsonProperty("explicit_content")
    public void setExplicitContent(ExplicitContent value) { this.explicitContent = value; }

    @JsonProperty("external_urls")
    public ExternalUrls getExternalUrls() { return externalUrls; }
    @JsonProperty("external_urls")
    public void setExternalUrls(ExternalUrls value) { this.externalUrls = value; }

    @JsonProperty("followers")
    public Followers getFollowers() { return followers; }
    @JsonProperty("followers")
    public void setFollowers(Followers value) { this.followers = value; }

    @JsonProperty("href")
    public String getHref() { return href; }
    @JsonProperty("href")
    public void setHref(String value) { this.href = value; }

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("images")
    public List<Object> getImages() { return images; }
    @JsonProperty("images")
    public void setImages(List<Object> value) { this.images = value; }

    @JsonProperty("product")
    public String getProduct() { return product; }
    @JsonProperty("product")
    public void setProduct(String value) { this.product = value; }

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("uri")
    public String getURI() { return uri; }
    @JsonProperty("uri")
    public void setURI(String value) { this.uri = value; }
}
