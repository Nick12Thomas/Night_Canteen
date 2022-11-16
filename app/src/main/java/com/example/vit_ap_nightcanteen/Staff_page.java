
package com.example.vit_ap_nightcanteen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Staff_page extends AppCompatActivity {
    EditText c1,c2,c3,c4,c5;
    int n1=0,n2=0,n3=0,n4=0,n5=0;
    TextView greeting, items;
    Button add,submit;
    EditText a1,a2,a3,a4,a5;
    int x1=0,x2=0,x3=0,x4=0,x5=0;
    int total_item = 0;
    int total_amt=0;
    Button update1,update2,update3,update4,update5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_page);


        String name = getIntent().getStringExtra("name");
        greeting = findViewById(R.id.textView);
        greeting.setText("Hello, "+name+"!");

        c1= findViewById(R.id.veg_nood);
        c2= findViewById(R.id.chick_nood);
        c3= findViewById(R.id.veg_sand);
        c4= findViewById(R.id.chick_sand);
        c5= findViewById(R.id.watermelon);
        a1=findViewById(R.id.veg_nood_amt);
        a2=findViewById(R.id.chick_nood_amt);
        a3=findViewById(R.id.veg_sand_amt);
        a4=findViewById(R.id.chick_sand_amt);
        a5=findViewById(R.id.watermelon_amt);
        add=findViewById(R.id.add);
        submit=findViewById(R.id.submit);
        items=findViewById(R.id.items);

        update1 = findViewById(R.id.update1);
        update1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateItem("Veg Noodles", c1.getText().toString(), a1.getText().toString());
            }
        });
        update2 = findViewById(R.id.update2);
        update2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateItem("Chicken Noodles", c2.getText().toString(), a2.getText().toString());
            }
        });
        update3 = findViewById(R.id.update3);
        update3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateItem("Veg Sandwich", c3.getText().toString(), a3.getText().toString());
            }
        });
        update4 = findViewById(R.id.update4);
        update4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateItem("Chicken Sandwich", c4.getText().toString(), a4.getText().toString());
            }
        });
        update5 = findViewById(R.id.update5);
        update5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateItem("Watermelon Juice", c5.getText().toString(), a5.getText().toString());
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n1 = Integer.parseInt(c1.getText().toString());
                n2 = Integer.parseInt(c2.getText().toString());
                n3 = Integer.parseInt(c3.getText().toString());
                n4 = Integer.parseInt(c4.getText().toString());
                n5 = Integer.parseInt(c5.getText().toString());
                x1 = Integer.parseInt(a1.getText().toString());
                x2 = Integer.parseInt(a2.getText().toString());
                x3 = Integer.parseInt(a3.getText().toString());
                x4 = Integer.parseInt(a4.getText().toString());
                x5 = Integer.parseInt(a5.getText().toString());

                total_item = n1+n2+n3+n4+n5;
                total_amt=n1*x1+n2*x2+n3*x3+n4*x4+n5*x5;
                if(total_item==0){
                    Toast.makeText(Staff_page.this, "Enter more item", Toast.LENGTH_SHORT).show();
                                    }
                if (total_amt==0){
                    Toast.makeText(Staff_page.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(MainActivity.this, String.valueOf(total_item), Toast.LENGTH_SHORT).show();
                items.setText("Total Items: "+String.valueOf(total_item)+"\nTotal Price: "+String.valueOf(total_amt));

            }
        });





    }

    public void updateItem(String item, String quantity, String price) {
        String url = "YOUR GOOGLE APP SCRIPT";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Staff_page.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Staff_page.this, "Did not connect", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Food", item);
                params.put("Quantity", quantity);
                params.put("Price", price);
                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(Staff_page.this);
        queue.add(stringRequest);
    }
}