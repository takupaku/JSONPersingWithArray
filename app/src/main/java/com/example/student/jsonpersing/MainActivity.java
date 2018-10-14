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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
  // private String URL = "https://api.myjson.com/bins/1d9uo0";
    private String nURL = "https://api.myjson.com/bins/amti8";
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
        tvResult = findViewById(R.id.tvResult);
    }

    private void initVariable() {
        requestQueue = Volley.newRequestQueue(this);

    }

    public void getJSONDATA(View view) {
        final StringBuffer stringBuffer = new StringBuffer();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, nURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject std = response.getJSONObject(i);


                        stringBuffer.append(std.getString("id")).append(" . ").append(std.getString("name"))
                                .append(" . ").append(std.getString("dept")).append("\n\n");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                tvResult.setText(stringBuffer);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(jsonArrayRequest);


//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONArray jsonArray = response.getJSONArray("student");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject std = (JSONObject) jsonArray.get(i);
//
//                        stringBuffer.append(std.getString("id")).append(" . ").append(std.getString("name"))
//                                .append(" . ").append(std.getString("dept")).append("\n\n");
//                    }
//                    tvResult.setText(stringBuffer);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//cntrl+spaace
        //requestQueue.add(request);
    }
}


/*
in jsonlint


{"student": [

          {
"id" :"32",
"name" : "taku",
"dept" : "cse"
},
{
"id" :"32",
"name" : "taku",
"dept" : "cse"
},{
"id" :"32",
"name" : "taku",
"dept" : "cse"
}
]
}

----->then in myJson, api link is collected


 */
