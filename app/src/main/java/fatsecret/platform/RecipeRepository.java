package fatsecret.platform;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import POJOs.RecipeCardView;
import utils.StringParser;

/**
 * Created by serban on 5/21/16.
 */

public class RecipeRepository implements Serializable {

    public final static String SER_KEY = "com.easyinfogeek.objectPass.ser";

    private JSONObject jsonResponse;

    public void setRecipes(List<RecipeCardView> recipes) {
        this.recipes = recipes;
    }

    private List<RecipeCardView> recipes = new ArrayList<RecipeCardView>();
    private transient FatSecretAPI fatSecretAPI;

    public String[] getUserIngredients() {
        return userIngredients;
    }

    public void setUserIngredients(String[] userIngredients) {
        this.userIngredients = userIngredients;
    }

    private String[] userIngredients;

    public String getComplexitySelection() {
        return complexitySelection;
    }

    public void setComplexitySelection(String complexitySelection) {
        this.complexitySelection = complexitySelection;
    }

    public String getCalorieIntake() {
        return calorieIntake;
    }

    public void setCalorieIntake(String calorieIntake) {
        this.calorieIntake = calorieIntake;
    }

    private String complexitySelection;
    private String calorieIntake;

    // input array of ingredients
    public JSONObject setIngredients(String[] ingredients) {
        userIngredients = ingredients;
        String ingredientsQuery = StringParser.makeArrayToString(ingredients);
        fatSecretAPI = new FatSecretAPI("5fd3c9b977b646b3b3020b73be726db4", "1a1cad108ee6471597bf41c3dfcd601e");
        try {
            jsonResponse = fatSecretAPI.getRecipes(ingredientsQuery);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        createRecipesArray(jsonResponse);
        return jsonResponse;
    }

    public List<RecipeCardView> getAllRecipes() {
        return this.recipes;
    }

    public void filterRecipesByTime(String time) {
        int minTime = calculateMinTime(time);
        int maxTime = calculateMaxTime(time);

        List<RecipeCardView> recipeList = new ArrayList<RecipeCardView>();
        for (int i = 0; i < recipes.size(); i++) {
            RecipeCardView recipe = recipes.get(i);
            if (recipe.getTime() > minTime && recipe.getTime() < maxTime) {
                recipeList.add(recipe);
            }
        }
        recipes = recipeList;
    }

    public void filterRecipesByCalories(String type) {
        int minCalories = calculateMinCalories(type);
        int maxCalories = calculateMaxCalories(type);

        List<RecipeCardView> recipeList = new ArrayList<RecipeCardView>();
        for (int i = 0; i < recipes.size(); i++) {
            RecipeCardView recipe = recipes.get(i);
            if (recipe.getTime() > minCalories && recipe.getTime() < maxCalories) {
                recipeList.add(recipe);
            }
        }
        recipes = recipeList;
    }

    private void createRecipesArray(JSONObject jsonResponse) {
        try {

//            jsonResponse = new JSONObject("{\n" +
//                    "   \"result\":{\n" +
//                    "      \"recipes\":{\n" +
//                    "         \"max_results\":\"5\",\n" +
//                    "         \"page_number\":\"0\",\n" +
//                    "         \"recipe\":[\n" +
//                    "            {\n" +
//                    "               \"recipe_description\":\"A tasty wrap that you can enjoy for breakfast or lunch.\",\n" +
//                    "               \"recipe_id\":\"276\",\n" +
//                    "               \"recipe_name\":\"Cheese and Chicken Egg Wrap\",\n" +
//                    "               \"recipe_url\":\"http:\\/\\/www.fatsecret.com\\/recipes\\/cheese-and-chicken-egg-wrap\\/Default.aspx\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "               \"recipe_description\":\"A simple quiche made with an egg and cottage cheese base.\",\n" +
//                    "               \"recipe_id\":\"447\",\n" +
//                    "               \"recipe_image\":\"http:\\/\\/m.ftscrt.com\\/static\\/recipe\\/baf965f2-64e6-4c11-a4da-2d4c39aa9f11.jpg\",\n" +
//                    "               \"recipe_name\":\"Spinach Quiche\",\n" +
//                    "               \"recipe_url\":\"http:\\/\\/www.fatsecret.com\\/recipes\\/spinach-quiche\\/Default.aspx\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "               \"recipe_description\":\"A tasty baked zucchini and egg recipe that's a great side dish.\",\n" +
//                    "               \"recipe_id\":\"628\",\n" +
//                    "               \"recipe_image\":\"http:\\/\\/m.ftscrt.com\\/static\\/recipe\\/d2d5cfdb-308a-41af-a60a-05c5a6aa7e7c.jpg\",\n" +
//                    "               \"recipe_name\":\"Zucchini and Egg Bake\",\n" +
//                    "               \"recipe_url\":\"http:\\/\\/www.fatsecret.com\\/recipes\\/zucchini-and-egg-bake\\/Default.aspx\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "               \"recipe_description\":\"A low carb and low fat banana bread recipe that's perfect for ripe bananas.\",\n" +
//                    "               \"recipe_id\":\"68579\",\n" +
//                    "               \"recipe_image\":\"http:\\/\\/m.ftscrt.com\\/static\\/recipe\\/a60a2aa2-2cfb-4685-9b16-b2dca0fecced.jpg\",\n" +
//                    "               \"recipe_name\":\"Banana Bread II\",\n" +
//                    "               \"recipe_url\":\"http:\\/\\/www.fatsecret.com\\/recipes\\/banana-bread-ii\\/Default.aspx\"\n" +
//                    "            },\n" +
//                    "            {\n" +
//                    "               \"recipe_description\":\"Easy to make curried eggs that are great for a salad. Also known as 'Hurry Curry'.\",\n" +
//                    "               \"recipe_id\":\"223\",\n" +
//                    "               \"recipe_image\":\"http:\\/\\/m.ftscrt.com\\/static\\/recipe\\/2386b1d5-bd64-4785-8e4a-ffb114ed8a6b.jpg\",\n" +
//                    "               \"recipe_name\":\"Curried Eggs\",\n" +
//                    "               \"recipe_url\":\"http:\\/\\/www.fatsecret.com\\/recipes\\/curried-eggs\\/Default.aspx\"\n" +
//                    "            }\n" +
//                    "         ],\n" +
//                    "         \"total_results\":\"154\"\n" +
//                    "      }\n" +
//                    "   }\n" +
//                    "}");

            JSONObject jsonResult = jsonResponse.getJSONObject("result");
            JSONObject json = jsonResult.getJSONObject("recipes");
            JSONArray jsonArray = json.getJSONArray("recipe");
            int size = jsonArray.length();

            for (int i = 0; i < size; i++) {
                JSONObject recipeJSON = jsonArray.optJSONObject(i);
                RecipeCardView recipeCardView = new RecipeCardView();
                recipeCardView.setName(recipeJSON.getString("recipe_name"));
                recipeCardView.setDescription(recipeJSON.getString("recipe_description"));
                recipeCardView.setId(recipeJSON.getLong("recipe_id"));
               // recipeCardView.setImageURL(recipeJSON.get("recipe_image").toString());
                recipes.add(recipeCardView);
//                long ID = recipeJSON.getLong("recipe_id");
//                jsonResult = jsonResponse.optJSONObject("result");
//                recipeJSON = fatSecretAPI.getRecipe(ID);
//               //jsonResult = recipeJSON.optJSONObject("result");
//                recipes.add(RecipeFactory.getRecipe(jsonResult, userIngredients));
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
            case "fit":
                minCalories = 0;
                break;
            case "medium":
                minCalories = 200;
                break;
            case "fat":
                minCalories = 500;
                break;
        }
        return minCalories;
    }

    private int calculateMaxCalories(String type) {
        int maxCalories = 1;
        switch (type) {
            case "fit":
                maxCalories = 199;
                break;
            case "medium":
                maxCalories = 499;
                break;
            case "fat":
                maxCalories = 2000;
                break;
        }
        return maxCalories;
    }
}
