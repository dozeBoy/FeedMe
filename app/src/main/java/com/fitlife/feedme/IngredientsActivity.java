package com.fitlife.feedme;

import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IngredientsActivity extends AppCompatActivity {
    EditText firstIngredient;
    EditText secondIngredient;
    EditText thirdIngredient;
    EditText fourthIngredient;
    EditText fifthIngredient;
    FloatingActionButton fab;


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



        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
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
                onSucces();
                progressDialog.dismiss();
            }
        }, 3000);

    }

    private void onSucces() {
        fab.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();

    }
}
