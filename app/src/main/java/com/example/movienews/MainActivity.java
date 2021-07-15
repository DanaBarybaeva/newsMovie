package com.example.movienews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //https://run.mocky.io/v3/71dc3ea1-246c-43fc-8fd6-757cac020a2a
    RecyclerView recyclerView;
    SwipeRefreshLayout swiperefresh;
    ArrayList<Model> newsList;
    RecyclerView.LayoutManager linearLayoutManager;
    Adapter recyclerViewAdapter;




    private static String JSON_LINK = "https://run.mocky.io/v3/71dc3ea1-246c-43fc-8fd6-757cac020a2a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.setHasFixedSize(true);

        swiperefresh = findViewById(R.id.swipeRefresh);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiperefresh.setRefreshing(false);






            }
        });


        newsList = new ArrayList<>();

//
//        newsList.add(new Model("Limitless","fantasy",null));
//        newsList.add(new Model("Knives out","detective",null));
//        newsList.add(new Model("Soul","cartoon",null));
//        newsList.add(new Model("Harry Potter","fantasy",null));
//        newsList.add(new Model("Nerve","phsyhology",null));
//        newsList.add(new Model("five feet apart","drama",null));
//        recyclerViewAdapter = new Adapter(this,newsList);
//        recyclerView.setAdapter(recyclerViewAdapter);



        GetData getData = new GetData();
        getData.execute();


    }

//    @Override
//    public void onClick(View v) {
//
//        switch (v.getId()) {
//
//            case R.id.morebutton:
//
//
//                if((recyclerViewAdapter.num)*10 < newsList.size())
//
//                    recyclerViewAdapter.num = recyclerViewAdapter.num +5;
//        }
//
//
//    }


    public class GetData extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(JSON_LINK);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isreader = new InputStreamReader(is);

                    int data = isreader.read();

                    while(data != -1){
                        current += (char) data;
                        data = isreader.read();

                    }
                    return current;



                }catch (MalformedURLException exception){
                    exception.printStackTrace();
                }catch (IOException exception){
                    exception.printStackTrace();
                }finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i<jsonArray.length(); i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    Model model = new Model();
                    model.setName(jsonObject1.getString("original_title"));
                    model.setData(jsonObject1.getString("release_date"));
                    model.setImage(jsonObject1.getString("poster_path"));
                    model.setBackgroundimage(jsonObject1.getString("backdrop_path"));
                    model.setDescription(jsonObject1.getString("overview"));
                    model.setVote_average(jsonObject1.getString("vote_average"));
                    newsList.add(model);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            PutDataIntoRecyclerView(newsList);
        }
    }
    private void PutDataIntoRecyclerView(List<Model> newsList){

        Adapter adapter = new Adapter(this, newsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

}