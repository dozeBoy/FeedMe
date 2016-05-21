package fatsecret.platform;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

import POJOs.Recipe;
import utils.StringParser;

/**
 * Created by serban on 5/21/16.
 */

public class RecipeRepository {
    private JSONObject jsonResponse;
    private List<Recipe> recipes;
    private RecipeFactory recipeFactory;
    private FatSecretAPI fatSecretAPI;

    // input array of ingredients
    public void setIngredients(String[] ingredients){
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

    private void createRecipesArray(JSONObject jsonResponse) {
        try {
            JSONObject json = jsonResponse.getJSONObject("recipes");
            JSONArray jsonArray = json.getJSONArray("recipe");
            int size = jsonArray.length();

            for (int i = 0; i < size; i++) {
                JSONObject recipeJSON = jsonArray.optJSONObject(i);
                long ID = recipeJSON.getLong("recipe_id");
                recipeJSON = fatSecretAPI.getRecipe(ID);
                recipes.add(recipeFactory.getRecipe(recipeJSON));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Recipe> getAllRecipes(){
        return this.recipes;
    }

}
