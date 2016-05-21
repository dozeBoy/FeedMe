package fatsecret.platform;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import POJOs.Recipe;
import utils.StringParser;

/**
 * Created by serban on 5/21/16.
 */

public class RecipeRepository {

    private JSONObject jsonResponse;
    private List<Recipe> recipes;
    private FatSecretAPI fatSecretAPI;
    private String[] userIngredients;

    // input array of ingredients
    public void setIngredients(String[] ingredients) {
        userIngredients = ingredients;
        String ingredientsQuery = StringParser.makeArrayToString(ingredients);

        try {
            jsonResponse = fatSecretAPI.getRecipes(ingredientsQuery);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        createRecipesArray(jsonResponse);
    }

    public List<Recipe> getAllRecipes() {
        return this.recipes;
    }

    public void filterRecipesByTime(String time) {
        int minTime = calculateMinTime(time);
        int maxTime = calculateMaxTime(time);

        List<Recipe> recipeList = new ArrayList<Recipe>();
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            if (recipe.getTime() > minTime && recipe.getTime() < maxTime) {
                recipeList.add(recipe);
            }
        }
        recipes = recipeList;
    }

    public void filterRecipesByCalories(String type) {
        int minCalories = calculateMinCalories(type);
        int maxCalories = calculateMaxCalories(type);

        List<Recipe> recipeList = new ArrayList<Recipe>();
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            if (recipe.getTime() > minCalories && recipe.getTime() < maxCalories) {
                recipeList.add(recipe);
            }
        }
        recipes = recipeList;
    }

    private void createRecipesArray(JSONObject jsonResponse) {
        try {
            JSONObject json = jsonResponse.getJSONObject("recipes");
            JSONArray jsonArray = json.getJSONArray("recipe");
            int size = jsonArray.length();

            for (int i = 0; i < size; i++) {
                JSONObject recipeJSON = jsonArray.optJSONObject(i);
                long ID = recipeJSON.getLong("recipe_id");
                recipeJSON = fatSecretAPI.getRecipe(ID);
                recipes.add(RecipeFactory.getRecipe(recipeJSON, userIngredients));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int calculateMinTime(String time) {
        int minTime = 0;
        switch (time) {
            case "quick":
                minTime = 0;
                break;
            case "medium":
                minTime = 11;
                break;
            case "slow":
                minTime = 31;
                break;
        }
        return minTime;
    }

    private int calculateMaxTime(String time) {
        int maxTime = 1;
        switch (time) {
            case "quick":
                maxTime = 10;
                break;
            case "medium":
                maxTime = 30;
                break;
            case "slow":
                maxTime = 90;
                break;
        }
        return maxTime;
    }

    private int calculateMinCalories(String type) {
        int minCalories = 0;
        switch (type) {
            case "quick":
                minCalories = 0;
                break;
            case "medium":
                minCalories = 200;
                break;
            case "slow":
                minCalories = 500;
                break;
        }
        return minCalories;
    }

    private int calculateMaxCalories(String type) {
        int maxCalories = 1;
        switch (type) {
            case "quick":
                maxCalories = 199;
                break;
            case "medium":
                maxCalories = 499;
                break;
            case "slow":
                maxCalories = 2000;
                break;
        }
        return maxCalories;
    }
}
