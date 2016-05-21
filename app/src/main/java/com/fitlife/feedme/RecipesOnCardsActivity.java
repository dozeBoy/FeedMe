package com.fitlife.feedme;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import POJOs.Recipe;
import adapters.RecipeRecycleViewAdapter;
import fatsecret.platform.RecipeRepository;

public class RecipesOnCardsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_on_cards);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        Bundle bundle = getIntent().getExtras();
        final RecipeRepository recipeRepository = (RecipeRepository) bundle.getSerializable(IngredientsActivity.SER_KEY);

        List<Recipe> recipesList = recipeRepository.getAllRecipes();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        RecipeRecycleViewAdapter adapter = new RecipeRecycleViewAdapter(this, recipesList, onRecipeItemClick);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);




    }


    private RecipeRecycleViewAdapter.OnRecyclerItemClick onRecipeItemClick = new RecipeRecycleViewAdapter.OnRecyclerItemClick() {

        @Override
        public void onItemClick(View view) {
            Intent intent = new Intent(RecipesOnCardsActivity.this, RecipeActivity.class);
            startActivity(intent);
        }

    };


}
