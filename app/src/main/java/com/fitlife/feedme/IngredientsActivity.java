package com.fitlife.feedme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import fatsecret.platform.RecipeRepository;

public class IngredientsActivity extends AppCompatActivity {
    EditText firstIngredient;
    EditText secondIngredient;
    EditText thirdIngredient;
    EditText fourthIngredient;
    EditText fifthIngredient;
    FloatingActionButton fab;
    List<String> ingredientsList = new ArrayList<>();
    RecipeRepository recipeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        firstIngredient = (EditText) findViewById(R.id.first_ingredient);
        secondIngredient = (EditText) findViewById(R.id.second_ingredient);
        thirdIngredient = (EditText) findViewById(R.id.third_ingredient);
        fourthIngredient = (EditText) findViewById(R.id.fourth_ingredient);
        fifthIngredient = (EditText) findViewById(R.id.fifth_ingredient);
        fab = (FloatingActionButton) findViewById(R.id.fab_ingredients);
        recipeRepository = new RecipeRepository();

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                ingredientsList.add(firstIngredient.getText().toString());
//                ingredientsList.add(secondIngredient.getText().toString());
//                ingredientsList.add(thirdIngredient.getText().toString());
//                ingredientsList.add(fourthIngredient.getText().toString());
//                ingredientsList.add(fifthIngredient.getText().toString());
//
//                String ingredientsArray[] = new String[ingredientsList.size()];
//                for (int j = 0; j < ingredientsList.size(); j++) {
//                    ingredientsArray[j] = ingredientsList.get(j);
//                }
//                recipeRepository.setIngredients(ingredientsArray);
                next();
            }
        });

    }

    private void next() {

        fab.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(IngredientsActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Computing recipes ...");
        progressDialog.show();


        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                progressDialog.dismiss();
            }
        }, 3000);

        startActivity(new Intent(this, RecipeComplexityActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void onSucces() {
        fab.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();

    }
}
