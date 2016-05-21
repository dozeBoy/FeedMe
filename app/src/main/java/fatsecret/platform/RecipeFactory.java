package fatsecret.platform;

import org.json.JSONObject;

import POJOs.Recipe;

/**
 * Created by serban on 5/21/16.
 */

public class RecipeFactory {

    public Recipe getRecipe(JSONObject jsonObject) {
        Recipe recipe = new Recipe();
        try {
            recipe.setDescription(jsonObject.get("recipe_description"));
            recipe.setRecipeID(jsonObject.get("recipe_id"));
            recipe.setImageURL(jsonObject.get("recipe_image"));
            recipe.setName(jsonObject.get("recipe_name"));
            recipe.setURL(jsonObject.get("recipe_url"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }
}
