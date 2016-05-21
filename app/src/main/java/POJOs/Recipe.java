package POJOs;

/**
 * Created by serban on 5/21/16.
 */

public class Recipe {
    private String url;
    private String description;
    private long id;
    private String imageURL;
    private String name;
    private int preparationTime;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPreparationTime() { return  preparationTime; }

    public void setPreparationTime(int time) { preparationTime = time; }
}
