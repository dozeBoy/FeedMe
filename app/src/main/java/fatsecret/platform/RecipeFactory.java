package fatsecret.platform;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import POJOs.RecipeCardView;
import utils.StringParser;

/**
 * Created by serban on 5/21/16.
 */

public class RecipeFactory implements Serializable {

    public static RecipeCardView getRecipe(JSONObject jsonObject, String[] userIngredients) {
        RecipeCardView recipe = new RecipeCardView();
        try {

            JSONObject jsonResultRecipe = jsonObject.getJSONObject("recipe");
            JSONObject jsonResultDirection = jsonResultRecipe.getJSONObject("directions");
            JSONArray jsonArray = jsonResultDirection.getJSONArray("direction");


            recipe.setDescription(jsonObject.getString("recipe_description"));
            recipe.setId(jsonObject.getLong("recipe_id"));
            recipe.setImageURL(jsonObject.getString("recipe_image"));
            recipe.setName(jsonObject.getString("recipe_name"));
            recipe.setUrl(jsonObject.getString("recipe_url"));

            int time = jsonObject.getInt("preparation_time_min") + jsonObject.getInt("cooking_time_min");
            recipe.setTime(time);

            recipe.setCalories(jsonObject.getInt("calories"));

            List<String> ingredients = getIngredientsFromJSON(jsonObject);
            recipe.setIngredients(ingredients);
            List<String> userIngAsList = Arrays.asList(userIngredients);
            recipe.setUserIngredients(userIngAsList);
            recipe.setRemainingIngredients(StringParser.makeDiff(ingredients, userIngAsList));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    private static List<String> getIngredientsFromJSON(JSONObject jsonObject) {
        List<String> ingredients = new ArrayList<String>();

        try {
            JSONObject json = jsonObject.getJSONObject("recipes");
            JSONArray jsonArray = json.getJSONArray("recipe");
            int size = jsonArray.length();

            for (int i = 0; i < size; i++) {
                JSONObject ingredientJSON = jsonArray.optJSONObject(i);
                ingredients.add(ingredientJSON.getString("food_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ingredients;
    }
}
