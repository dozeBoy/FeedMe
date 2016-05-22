package com.fitlife.feedme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import POJOs.RecipeCardView;
import adapters.RecipeRecycleViewAdapter;
import fatsecret.platform.RecipeRepository;

public class RecipesOnCardsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private ProgressDialog progressDialog = null;
    RecipeRepository recipeRepository;
    RecipeRecycleViewAdapter adapter;
    List<RecipeCardView> recipesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_on_cards);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        Bundle bundle = getIntent().getExtras();
        recipeRepository = (RecipeRepository) bundle.getSerializable(IngredientsActivity.SER_KEY);

      //

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);



        List<RecipeCardView> recipesListUpdated = new ArrayList<>();

        RecipeCardView recipeCardView = new RecipeCardView();

        RecipeCardView recipeCardViewOne = new RecipeCardView();
        RecipeCardView recipeCardViewTwo = new RecipeCardView();
        RecipeCardView recipeCardViewThree = new RecipeCardView();
        RecipeCardView recipeCardViewFour  = new RecipeCardView();

//        for(RecipeCardView recipeCardView : recipesList)
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        new loadData().execute();
    }


    private RecipeRecycleViewAdapter.OnRecyclerItemClick onRecipeItemClick = new RecipeRecycleViewAdapter.OnRecyclerItemClick() {

        @Override
        public void onItemClick(View view) {
            Intent intent = new Intent(RecipesOnCardsActivity.this, RecipeActivity.class);
            startActivity(intent);
        }

    };

    private class loadData extends AsyncTask<String, String, JSONObject> {
        JSONObject jsonobj = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(RecipesOnCardsActivity.this, "Computing recipes ...", "Please wait ...");
        }

        @Override
        protected JSONObject doInBackground(String... params) {


            jsonobj = recipeRepository.setIngredients(recipeRepository.getUserIngredients());

           //recipeRepository.filterRecipesByTime(recipeRepository.getComplexitySelection());
            //recipeRepository.filterRecipesByCalories(recipeRepository.getCalorieIntake());
            recipesList = recipeRepository.getAllRecipes();
            Log.d("doInBackground", "");

            return jsonobj;

        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            adapter = new RecipeRecycleViewAdapter(recipesList, onRecipeItemClick);
            recyclerView.setAdapter(adapter);
            Log.d("onPostExecute", result.toString());
        }
    }


}
