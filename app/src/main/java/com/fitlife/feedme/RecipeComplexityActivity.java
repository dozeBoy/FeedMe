package com.fitlife.feedme;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import POJOs.RecipeCardView;
import POJOs.RecipeCardViewList;
import fatsecret.platform.RecipeRepository;

import static com.fitlife.feedme.R.id.textView;

public class RecipeComplexityActivity extends AppCompatActivity {

    Button quickButton;
    Button mediumButton;
    Button slowButton;
    ImageView imageView;
    RecipeRepository recipeRepository;
    ArrayList<Parcelable> recipeCardViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_complexity);

        quickButton = (Button) findViewById(R.id.button_quick);
        mediumButton = (Button) findViewById(R.id.button_medium);
        slowButton = (Button) findViewById(R.id.button_slow);
        imageView = (ImageView) findViewById(R.id.recipe_complexity_title);


        Bundle bundle = getIntent().getExtras();
        recipeRepository = (RecipeRepository) bundle.getSerializable(IngredientsActivity.SER_KEY);
        recipeCardViewList = bundle.getParcelable("LIST");
        //recipeRepository.setRecipes(recipeCardViewList);


        quickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeRepository.setComplexitySelection("quick");
                startCalorieActivity(recipeRepository);
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeRepository.setComplexitySelection("medium");
                startCalorieActivity(recipeRepository);
            }
        });

        slowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeRepository.setComplexitySelection("slow");
                startCalorieActivity(recipeRepository);
            }
        });

    }

    private void startCalorieActivity(RecipeRepository recipeRepository) {

        Intent intent = new Intent(this, CalorieIntakeActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(IngredientsActivity.SER_KEY, recipeRepository);
        intent.putExtras(mBundle);

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
