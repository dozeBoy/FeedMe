package fatsecret.platform;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import POJOs.Recipe;

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
        jsonResponse = fatSecretAPI.getResponseByIngredients(ingredients);
        createRecipesArray(jsonResponse);
    }

    private void createRecipesArray(JSONObject jsonResponse) {
        try {
            JSONObject json = jsonResponse.getJSONObject("recipes");
            JSONArray jsonArray = json.getJSONArray("recipe");
            int size = jsonArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject obj = jsonArray.optJSONObject(i);
                String ID = obj.getString("recipe_id");
                obj = fatSecretAPI.getRecipeByID(ID);
                recipes.add(recipeFactory.getRecipe(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Recipe> getAllRecipes(){
        return this.recipes;
    }

}
