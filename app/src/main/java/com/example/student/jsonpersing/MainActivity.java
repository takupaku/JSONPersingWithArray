package com.example.student.jsonpersing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private String URL ="https://api.myjson.com/bins/1d9uo0";
    RequestQueue requestQueue;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVariable();
        initView();
    }

    private void initView() {
        tvResult=findViewById(R.id.tvResult);
    }

    private void initVariable() {
        requestQueue= Volley.newRequestQueue(this);

    }

    public void getJSONDATA(View view) {
        final StringBuffer stringBuffer= new StringBuffer();


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("student");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject std = (JSONObject) jsonArray.get(i);

                        stringBuffer.append(std.getString("id")).append(" . ").append(std.getString("name"))
                                .append(" . ").append(std.getString("dept")).append("\n\n");
                    }
                    tvResult.setText(stringBuffer);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
//            @Override//response is holding the json api data
//            public void onResponse(String response) {
//
//                JSONObject jsonObject = response.get
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });//cntrl+spaace
        requestQueue.add(request);
    }
}
