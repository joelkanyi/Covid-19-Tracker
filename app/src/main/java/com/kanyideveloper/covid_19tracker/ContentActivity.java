package com.kanyideveloper.covid_19tracker;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.Icon;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.kanyideveloper.covid_19tracker.R.menu.menu_items;


public class ContentActivity extends AppCompatActivity {

    private static final String TAG = "good";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<StatisticsList> statisticsList;
    EditText editTextSearch;

    private static final String URL = "https://covid2019-api.herokuapp.com/v2/current";
    //private static final String URL2 = "https://corona.lmao.ninja/countries";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Toolbar toolbar = findViewById(R.id.myToolbar);

        toolbar.setTitle("Covid-19 Tracker");
        setSupportActionBar(toolbar);


        editTextSearch = findViewById(R.id.searchEdittext);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        initId();
        getData();

        }

    private void initId()
    {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        statisticsList = new ArrayList<>();
    }

    public void filter(String e){
        ArrayList<StatisticsList> filteredlist = new ArrayList<>();

        for(StatisticsList item : statisticsList){
            if(item.getCountry().toLowerCase().contains(e.toLowerCase())){
                filteredlist.add(item);
            }
        }
        adapter.filterList(filteredlist);
    }

        private void getData()
    {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.e("CurrentData","Response: "+response);

                try{
                    recyclerView = findViewById(R.id.recyclerView);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for(int i = 0; i<jsonArray.length();i ++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        StatisticsList statisticsList1 = new StatisticsList(jsonObject1.getString("location"),jsonObject1.getString("active"),
                                jsonObject1.getString("recovered"),jsonObject1.getString("deaths"),jsonObject1.getString("confirmed"));
                        statisticsList.add(statisticsList1);
                        }

                    adapter = new RecyclerViewAdapter(statisticsList, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    }

                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ContentActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.about){

            new FancyAlertDialog.Builder(this)
                    .setTitle("About the App")
                    .setNegativeBtnText("")
                    .setNegativeBtnBackground(Color.parseColor("#ffffff"))
                    .setBackgroundColor(Color.parseColor("#303f9f"))
                    .setMessage("Covid-19 Tracker is an android application that helps you to be updated on the current statisitics of the Corona virus spread throughout the different parts of the world. The data is updated everyday from a trusted API.")
                    .setPositiveBtnBackground(Color.parseColor("#ff8041"))
                    .setPositiveBtnText("OK")
                    .setIcon(R.drawable.github, Icon.Visible).build();
            return true;
        }
        else if(id == R.id.developer){

            new FancyAlertDialog.Builder(this)
                    .setTitle("Joel Kanyi")
                    .setNegativeBtnText("")
                    .setNegativeBtnBackground(Color.parseColor("#ffffff"))
                    .setBackgroundColor(Color.parseColor("#303f9f"))
                    .setMessage("Android developer\n"+"Software developer\n"+"Student\n" +
                            "LinkedIn: Joel Kanyi\n" +
                            "Github: github.com/JoelKanyi\n" +
                            "Phone No: +254706003891")
                    .setPositiveBtnBackground(Color.parseColor("#ff8041"))
                    .setPositiveBtnText("OK")
                    .setIcon(R.drawable.github, Icon.Visible).build();
            return  true;
        }
        else if(id == R.id.tollNumbers){

            new FancyAlertDialog.Builder(this)
                    .setTitle("Ministry of Health")
                    .setNegativeBtnText("")
                    .setNegativeBtnBackground(Color.parseColor("#ffffff"))
                    .setBackgroundColor(Color.parseColor("#303f9f"))
                    .setMessage("Coronavirus disease is NOT airborne. It is tansmitted through droplets when an infected person coughs or sneezes." +
                            "\nFor help call 719 or dial *719#")
                    .setPositiveBtnBackground(Color.parseColor("#ff8041"))
                    .setPositiveBtnText("OK")
                    .setIcon(R.drawable.github, Icon.Visible).build();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

