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
            recipe.setDescription(jsonObject.getString("recipe_description"));
            recipe.setId(jsonObject.getLong("recipe_id"));
            recipe.setImageURL(jsonObject.getString("recipe_image"));
            recipe.setName(jsonObject.getString("recipe_name"));
            recipe.setUrl(jsonObject.getString("recipe_url"));
            recipe.setPreparationTime(jsonObject.getInt("preparation_time_min"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }
}
