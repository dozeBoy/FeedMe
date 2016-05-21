package POJOs;

import java.util.List;

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
    private int calories;

    private List<String> ingredients;
    private List<String> userIngredients;
    private List<String> remainingIngredients;

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

    public int getTime() { return  preparationTime; }

    public void setTime(int time) { preparationTime = time; }

    public void setCalories(int calories) { this.calories = calories; }

    public int getCalories() { return calories; }

    public List<String> getIngredients() { return ingredients; }

    public void setIngredients(List<String> ingredients) {this.ingredients = ingredients; }

    public List<String> getUserIngredients() { return userIngredients; }

    public void setUserIngredients(List<String> userIngredients) { this.userIngredients = userIngredients; }

    public List<String> getRemainingIngredients() { return remainingIngredients; }

    public void setRemainingIngredients(List<String> remainingIngredients) { this.remainingIngredients = remainingIngredients; }
}