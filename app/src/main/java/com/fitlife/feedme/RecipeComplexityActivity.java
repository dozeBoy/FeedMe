package com.fitlife.feedme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.fitlife.feedme.R.id.textView;

public class RecipeComplexityActivity extends AppCompatActivity {

    Button quickButton;
    Button mediumButton;
    Button slowButton;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_complexity);

        quickButton = (Button) findViewById(R.id.button_quick);
        mediumButton = (Button) findViewById(R.id.button_medium);
        slowButton = (Button) findViewById(R.id.button_slow);
        imageView = (ImageView) findViewById(R.id.recipe_complexity_title);

    }
}
