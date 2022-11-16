package com.example.vit_ap_nightcanteen;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.razorpay.Checkout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student_page extends AppCompatActivity {
    TextView search_bar, c1,c2,c3,c4,c5,amount;
    TextView veg_nood_avail, chik_nood_avail, veg_sand_avail, chik_sand_avail, juice_avail;
    Button p1,p2,p3,p4,p5,m1,m2,m3,m4,m5,add,submit;
    int sum1=0,sum2=0,sum3=0,sum4=0,sum5=0;
    TextView show_veg_nood, show_chik_nood, show_veg_sand, show_chik_sand, show_juice;
    int total_sum;
    final int UPI_PAYMENT = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);

        Intent intent = getIntent();
        HashMap<String, int[]> menu = (HashMap<String, int[]>) intent.getSerializableExtra("map");
        String name = intent.getStringExtra("name");

        search_bar = findViewById(R.id.search_bar);
        search_bar.setText("Hello, "+name+"!");

        veg_nood_avail = findViewById(R.id.veg_noodles_price);
        chik_nood_avail = findViewById(R.id.chicken_noodles_price);
        veg_sand_avail = findViewById(R.id.veg_sandwich_price);
        chik_sand_avail = findViewById(R.id.chicken_sandwich_price);
        juice_avail = findViewById(R.id.watermelon_price);

        show_veg_nood = findViewById(R.id.veg_noodles_price);
        show_veg_nood.setText("Rs."+Integer.toString(menu.get("Veg Noodles")[1]));

        show_chik_nood = findViewById(R.id.chicken_noodles_price);
        show_chik_nood.setText("Rs."+Integer.toString(menu.get("Chicken Noodles")[1]));

        show_veg_sand = findViewById(R.id.veg_sandwich_price);
        show_veg_sand.setText("Rs."+Integer.toString(menu.get("Veg Sandwich")[1]));

        show_chik_sand = findViewById(R.id.chicken_sandwich_price);
        show_chik_sand.setText("Rs."+Integer.toString(menu.get("Chicken Sandwich")[1]));

        show_juice = findViewById(R.id.watermelon_price);
        show_juice.setText("Rs."+Integer.toString(menu.get("Watermelon Juice")[1]));

        c1= findViewById(R.id.itemcount1);
        c2= findViewById(R.id.itemcount2);
        c3= findViewById(R.id.itemcount3);
        c4= findViewById(R.id.itemcount4);
        c5= findViewById(R.id.itemcount5);
        p1 = findViewById(R.id.plus1);
        p2 = findViewById(R.id.plus2);
        p3 = findViewById(R.id.plus3);
        p4 = findViewById(R.id.plus4);
        p5 = findViewById(R.id.plus5);
        m1 = findViewById(R.id.minus1);
        m2 = findViewById(R.id.minus2);
        m3 = findViewById(R.id.minus3);
        m4 = findViewById(R.id.minus4);
        m5 = findViewById(R.id.minus5);
        add= findViewById(R.id.add);
        amount = findViewById(R.id.amount);
        submit = findViewById(R.id.submit);
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum1++;
                if(sum1>menu.get("Veg Noodles")[0]){
                    Toast.makeText(Student_page.this, "The quantity is over! ", Toast.LENGTH_SHORT).show();
                    sum1 = menu.get("Veg Noodles")[0];
                }
                c1.setText(String.valueOf(sum1));
            }
        });


        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum1--;
                if (sum1<0){
                    sum1 = 0;
                }
                c1.setText(String.valueOf(sum1));
            }
        });
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum2++;
                c2.setText(String.valueOf(sum2));
                if(sum2>menu.get("Chicken Noodles")[0]){
                    Toast.makeText(Student_page.this, "The quantity is over! ", Toast.LENGTH_SHORT).show();
                    sum2 = menu.get("Chicken Noodles")[0];
                }
                c2.setText(String.valueOf(sum2));

            }
        });
        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum2--;
                if (sum2<0){
                    sum2 = 0;
                }
                c2.setText(String.valueOf(sum2));

            }
        });
        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum3++;
                if(sum3>menu.get("Veg Sandwich")[0]){
                    Toast.makeText(Student_page.this, "The quantity is over! ", Toast.LENGTH_SHORT).show();
                    sum3 = menu.get("Veg Sandwich")[0];
                }
                c3.setText(String.valueOf(sum3));
            }
        });
        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum3--;
                if (sum3<0){
                    sum3 = 0;
                }
                c3.setText(String.valueOf(sum3));

            }
        });
        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum4++;
                if(sum4>menu.get("Chicken Sandwich")[0]){
                    Toast.makeText(Student_page.this, "The quantity is over! ", Toast.LENGTH_SHORT).show();
                    sum4 = menu.get("Chicken Sandwich")[0];
                }
                c4.setText(String.valueOf(sum4));
            }
        });
        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum4--;
                if (sum4<0){
                    sum4 = 0;
                }
                c4.setText(String.valueOf(sum4));

            }
        });
        p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum5++;
                if(sum5>menu.get("Watermelon Juice")[0]){
                    Toast.makeText(Student_page.this, "The quantity is over! ", Toast.LENGTH_SHORT).show();
                    sum5 = menu.get("Watermelon Juice")[0];
                }
                c5.setText(String.valueOf(sum5));
            }
        });
        m5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum5--;
                if (sum5<0){
                    sum5 = 0;
                }
                c5.setText(String.valueOf(sum5));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_sum = menu.get("Veg Noodles")[1]*sum1
                        +menu.get("Chicken Noodles")[1]*sum2
                        +menu.get("Veg Sandwich")[1]*sum3
                        +menu.get("Chicken Sandwich")[1]*sum4
                        +menu.get("Watermelon Juice")[1]*sum5;
                amount.setText("Amount payable: "+ String.valueOf(total_sum));

                if (total_sum <= 0){
                    Toast.makeText(Student_page.this, " Please choose items of your choice! ", Toast.LENGTH_SHORT).show();

                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(total_sum!=0) {
                    menu.get("Veg Noodles")[0] -= sum1;
                    menu.get("Chicken Noodles")[0] -= sum2;
                    menu.get("Veg Sandwich")[0] -= sum3;
                    menu.get("Chicken Sandwich")[0] -= sum4;
                    menu.get("Watermelon Juice")[0] -= sum5;

                    updateItem("Veg Noodles", Integer.toString(menu.get("Veg Noodles")[0]), Integer.toString(menu.get("Veg Noodles")[1]));
                    updateItem("Chicken Noodles", Integer.toString(menu.get("Chicken Noodles")[0]), Integer.toString(menu.get("Chicken Noodles")[1]));
                    updateItem("Veg Sandwich", Integer.toString(menu.get("Veg Sandwich")[0]), Integer.toString(menu.get("Veg Sandwich")[1]));
                    updateItem("Chicken Sandwich", Integer.toString(menu.get("Chicken Sandwich")[0]), Integer.toString(menu.get("Chicken Sandwich")[1]));
                    updateItem("Watermelon Juice", Integer.toString(menu.get("Watermelon Juice")[0]), Integer.toString(menu.get("Watermelon Juice")[1]));

                    String amount=String.valueOf(total_sum);
                    String note = "Night Canteen Order";
                    String name = "NAME TO RECIEVE UPI APP";
                    String upiId ="UPI ID TO RECIEVE UPI PAYMENT";
                    payUsingUpi(amount, upiId, name, note);
                }
                else{
                    Toast.makeText(Student_page.this, "Select the Items ", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }


    void payUsingUpi(String amount, String upiId, String name, String note) {

        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();


        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);

        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");

        // check if intent resolves
        if(null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(Student_page.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.d("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    Log.d("UPI", "onActivityResult: " + "Return data is null");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }

    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(getApplicationContext())) {
            String str = data.get(0);
            Log.d("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }

            if (status.equals("success")) {
                Toast.makeText(Student_page.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                Log.d("UPI", "responseStr: "+approvalRefNo);
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(Student_page.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(Student_page.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(Student_page.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public void updateItem(String item, String quantity, String price) {
        String url = "YOUR GOOGLE APP SCRIPT";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Student_page.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Student_page.this, "Did not connect", Toast.LENGTH_LONG).show();
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
        RequestQueue queue = Volley.newRequestQueue(Student_page.this);
        queue.add(stringRequest);
    }
}