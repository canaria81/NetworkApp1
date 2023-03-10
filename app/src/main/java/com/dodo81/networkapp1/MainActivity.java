package com.dodo81.networkapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class MainActivity extends AppCompatActivity {

    TextView txtUserId;
    TextView txtId;
    TextView txtTitle;
    TextView txtBody;
    final String URL = "https://jsonplaceholder.typicode.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUserId = findViewById(R.id.txtUserId);
        txtId = findViewById(R.id.txtId);
        txtTitle = findViewById(R.id.txtTitle);
        txtBody = findViewById(R.id.txtBody);

        // Volley로 네트워크 통신
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                URL + "/posts/1",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("NETWORK_APP", response.toString());
                        // response에 데이터가 있으니 데이터를 Parsing 한다.
                        try {
                            int userId = response.getInt("userId");
                            int id = response.getInt("id");
                            String title = response.getString("title");
                            String body = response.getString("body");

                            // 화면에 셋팅
                            txtUserId.setText(userId+"");
                            txtId.setText(id+"");
                            txtTitle.setText(title);
                            txtBody.setText(body);

                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "파싱 에러", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
//        JsonArrayRequest request = new JsonArrayRequest(
//                Request.Method.GET,
//                URL + "/posts",
//                null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.i("NETWORK_APP", response.toString());
//
//                        // response에 데이터가 있으니 데이터를 Parsing 한다.
//                        try {
//                            response.get(0).
//
//                            int userId = response.getInt("userId");
//                            int id = response.getInt("id");
//                            String title = response.getString("title");
//                            String body = response.getString("body");
//                            // 화면에 셋팅
//                            txtUserId.setText(userId+"");
//                            txtId.setText(id+"");
//                            txtTitle.setText(title);
//                            txtBody.setText(body);
//
//                        } catch (JSONException e) {
////                            throw new RuntimeException(e);
//                            e.printStackTrace();
//                            Toast.makeText(MainActivity.this, "파싱 에러", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                    }
//                }
//        );
        // 이 코드가 있어야, 네트워크 실행한다.
        queue.add(request);
    }
}

//첫번째 데이터를 화면에 표시
