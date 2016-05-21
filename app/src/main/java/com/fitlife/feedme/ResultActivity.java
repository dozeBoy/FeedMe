package com.fitlife.feedme;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

import POJOs.RecyclerItem;
import adapters.RecipeRecycleViewAdapter;
import fatsecret.platform.FatSecretAPI;
import utils.JSONParser;

public class ResultActivity extends AppCompatActivity {
    private ProgressDialog progressDialog = null;
    private List<RecyclerItem> feedsList;
    private RecyclerView mRecyclerView;
    private RecipeRecycleViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_result);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);


        new loadData().execute();

    }


    private class loadData extends AsyncTask<String, String, JSONObject> {
        JSONObject jsonobj = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ResultActivity.this, "Fetching from Memox", "Please wait ...");
        }

        @Override
        protected JSONObject doInBackground(String... params) {

            FatSecretAPI fatSecretAPI = new FatSecretAPI("5fd3c9b977b646b3b3020b73be726db4","1a1cad108ee6471597bf41c3dfcd601e");

            JSONParser jParser = new JSONParser();

            try {
                try {
                    jsonobj = fatSecretAPI.getRecipes("ham eggs");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.d("doInBackground", "");

            return jsonobj;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            progressDialog.dismiss();

            Toast.makeText(getApplicationContext(), jsonobj.toString().substring(1, jsonobj.toString().length() - 1),
                    Toast.LENGTH_LONG).show();

            Log.d("onPostExecute", result.toString());
        }
    }


//    private void parseResult(JSONObject response) {
//
//
//        feedsList = new ArrayList<RecyclerItem>();
//
//        for (int i = 0; i < response.names().length(); i++) {
//
//
//            RecyclerItem item = new RecyclerItem();
//            try {
//                String field = response.names().getString(i);
//                item.setText(response.optString(field));
//                item.setThumbnail(R.drawable.info_icon);
//                feedsList.add(item);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//    }


    /*
       Cleanup on every activity restart to avoid duplicate data.
    */
    @Override
    protected void onRestart() {
        super.onRestart();

        adapter.clearRecycler();

        new loadData().execute();
    }
}
