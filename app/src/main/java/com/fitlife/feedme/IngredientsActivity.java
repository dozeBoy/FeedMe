package com.fitlife.feedme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import fatsecret.platform.FatSecretAPI;
import fatsecret.platform.RecipeRepository;
import utils.JSONParser;

import static com.fitlife.feedme.R.id.imageView;

public class IngredientsActivity extends AppCompatActivity {

    public final static String SER_KEY = "fatsecret.platform.reciperepository";

    EditText firstIngredient;
    EditText secondIngredient;
    EditText thirdIngredient;
    EditText fourthIngredient;
    EditText fifthIngredient;
    FloatingActionButton fab;
    private ProgressDialog progressDialog = null;
    List<String> ingredientsList = new ArrayList<>();
    RecipeRepository recipeRepository;
    String ingredientsArray[];

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (firstIngredient != null) {
                firstIngredient.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ingredientsList.add(firstIngredient.getText().toString());
                ingredientsList.add(secondIngredient.getText().toString());
                ingredientsList.add(thirdIngredient.getText().toString());
                ingredientsList.add(fourthIngredient.getText().toString());
                ingredientsList.add(fifthIngredient.getText().toString());

                ingredientsArray = new String[ingredientsList.size()];
                for (int j = 0; j < ingredientsList.size(); j++) {
                    ingredientsArray[j] = ingredientsList.get(j);
                }
                new loadData().execute();
                next(recipeRepository);
            }
        });

    }

    private void next(RecipeRepository recipeRepository) {

        fab.setEnabled(false);

        Intent intent = new Intent(this, RecipeComplexityActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(SER_KEY, recipeRepository);
        intent.putExtras(mBundle);

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void onSucces() {
        fab.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();

    }

    private class loadData extends AsyncTask<String, String, JSONObject> {
        JSONObject jsonobj = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(IngredientsActivity.this, "Computing recipes ...", "Please wait ...");
        }

        @Override
        protected JSONObject doInBackground(String... params) {


            jsonobj = recipeRepository.setIngredients(ingredientsArray);

            Log.d("doInBackground", "");

            return jsonobj;

        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), jsonobj.toString().substring(1, jsonobj.toString().length() - 1),
                    Toast.LENGTH_LONG).show();
          //  Log.d("onPostExecute", result.toString());
        }
    }


}
