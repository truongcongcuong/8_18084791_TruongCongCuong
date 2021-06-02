package com.example.a8_18084791_truongcongcuong;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddProductActivity extends AppCompatActivity {
    Button btn_add_create, btn_add_back;
    EditText edt_add_type,edt_add_price,edt_add_country;
    String url = "https://60ad9ae180a61f00173313b8.mockapi.io/wood";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        getSupportActionBar().hide();

        btn_add_back = findViewById(R.id.btn_add_back);
        btn_add_create = findViewById(R.id.btn_add_create);
        edt_add_type = findViewById(R.id.edt_add_type);
        edt_add_price = findViewById(R.id.edt_add_price);
        edt_add_country = findViewById(R.id.edt_add_country);

        edt_add_country.setHint("Country");
        edt_add_price.setHint("Price");
        edt_add_type.setHint("Type");

        btn_add_back.setOnClickListener(v->finish());

        btn_add_create.setOnClickListener(v->{
            StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                    response -> {
                        Toast.makeText(AddProductActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    },
                    error -> Toast.makeText(AddProductActivity.this,"Thêm thất bại",Toast.LENGTH_SHORT).show()
            ){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String> map = new HashMap<>();
                    map.put("type",edt_add_type.getText().toString());
                    map.put("price",edt_add_price.getText().toString());
                    map.put("country",edt_add_country.getText().toString());

                    return map;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
            startActivity(new Intent(AddProductActivity.this,InformationActivity.class));
        });
    }
}