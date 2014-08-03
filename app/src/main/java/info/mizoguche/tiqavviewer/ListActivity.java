package info.mizoguche.tiqavviewer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;


public class ListActivity extends Activity {

    @InjectView(android.R.id.list)
    ListView listView;

    @InjectView(R.id.activity_list_text_search)
    EditText searchText;

    static public RequestQueue queue;
    private ProgressDialog progressDialog;

    private TiqavAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.inject(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.show();

        String url = "http://api.tiqav.com/search/random.json";
        queue = Volley.newRequestQueue(this);
        queue.add(new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            TiqavImage[] list = gson.fromJson(response, TiqavImage[].class);
                            adapter = new TiqavAdapter(getBaseContext(), list, queue);
                            listView.setAdapter(adapter);
                            progressDialog.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("test", error.toString());
                            progressDialog.dismiss();
                        }
                    }
                ));
    }

    @OnClick(R.id.activity_list_btn_search)
    public void onSearchClick(){
        progressDialog.show();
        String query = URLEncoder.encode(searchText.getText().toString());
        String url = "http://api.tiqav.com/search.json?q=" + query;
        queue = Volley.newRequestQueue(this);
        queue.add(new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        TiqavImage[] list = gson.fromJson(response, TiqavImage[].class);
                        TiqavAdapter adapter = new TiqavAdapter(getBaseContext(), list, queue);
                        listView.setAdapter(adapter);
                        listView.invalidateViews();
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("test", error.toString());
                        progressDialog.dismiss();
                    }
                }
        ));
    }

    @OnItemClick(android.R.id.list)
    public void onItemClick(int position){
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), DetailActivity.class);
        intent.putExtra("url", adapter.getList().get(position).getUrl());
        startActivity(intent);
    }
}
