package com.fitlife.feedme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import fatsecret.platform.RecipeRepository;

public class CalorieIntakeActivity extends AppCompatActivity {
    Button fitmealButton;
    Button ordinaryButton;
    Button cheatmealButton;
    //ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_intake);

        fitmealButton = (Button) findViewById(R.id.button_fitmeal);
        ordinaryButton = (Button) findViewById(R.id.button_ordinary);
        cheatmealButton = (Button) findViewById(R.id.button_cheat);
        // imageView = (ImageView) findViewById(R.id.recipe_complexity_title);

        Bundle bundle = getIntent().getExtras();
        final RecipeRepository recipeRepository = (RecipeRepository) bundle.getSerializable(IngredientsActivity.SER_KEY);

        fitmealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeRepository.filterRecipesByCalories("fit");
                startRecipesCardList(recipeRepository);
            }
        });

        ordinaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeRepository.filterRecipesByCalories("medium");
                startRecipesCardList(recipeRepository);
            }
        });

        cheatmealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeRepository.filterRecipesByCalories("fat");
                startRecipesCardList(recipeRepository);
            }
        });


    }

    private void startRecipesCardList(RecipeRepository recipeRepository) {

        Intent intent = new Intent(this, RecipesOnCardsActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(IngredientsActivity.SER_KEY, recipeRepository);
        intent.putExtras(mBundle);
        startActivity(intent);

    }
}
