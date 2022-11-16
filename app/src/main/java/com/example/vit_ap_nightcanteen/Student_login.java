package com.example.vit_ap_nightcanteen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.vit_ap_nightcanteen.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Student_login extends AppCompatActivity {

    EditText enter_stuid,pass;
    Button submit;
    private RequestQueue requestQueue;
    private RequestQueue requestQueue2;

    public HashMap<String, int[]> menu = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestQueue2 = Volley.newRequestQueue(Student_login.this);
        requestQueue2.getCache().clear();
        String url = "YOUR GOOGLE APP SCRIPT";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                requestQueue2.getCache().clear();
                try {
                    JSONArray jsonArray = response.getJSONArray("item");
                    for(int i = 0; i< jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        String food = item.getString("Food");
                        int quantity = item.getInt("Quantity");
                        int price = item.getInt("Price");
                        menu.put(food, new int[] {quantity, price});
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Student_login.this, "Data Not Received", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue2.add(request);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        enter_stuid = findViewById(R.id.enter_stuid);
        pass = findViewById(R.id.enter_pass);

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.getCache().clear();

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enterId = enter_stuid.getText().toString();
                String enterPassword = pass.getText().toString();

                String url = "YOUR GOOGLE APP SCRIPT";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        boolean flag = false;
                        requestQueue.getCache().clear();
                        try {
                            JSONArray jsonArray = response.getJSONArray("item");
                            for(int i = 0; i< jsonArray.length(); i++) {
                                JSONObject item = jsonArray.getJSONObject(i);

                                String username = item.getString("Username");
                                String password = item.getString("Password");
                                String name = item.getString("Name");

                                if(enterId.equals(username))
                                {
                                    if(enterPassword.equals(password))
                                    {
                                        flag = true;
                                        Toast.makeText(Student_login.this, "Welcome", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Student_login.this,Student_page.class);
                                        intent.putExtra("name",name);
                                        intent.putExtra("map",menu);
                                        startActivity(intent);
                                    }
                                    break;
                                }
                            }
                            if(!flag)
                                Toast.makeText(Student_login.this, "Wrong Login ID or PassWord", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Student_login.this, "Data Not Received", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(request);
            }


        });


    }
}