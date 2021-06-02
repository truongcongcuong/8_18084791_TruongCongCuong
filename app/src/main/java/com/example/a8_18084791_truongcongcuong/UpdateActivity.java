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

public class UpdateActivity extends AppCompatActivity {
    Button btn_update_save, btn_update_back;
    EditText edt_update_type, edt_update_price,edt_update_country;
    String url = "https://60ad9ae180a61f00173313b8.mockapi.io/wood";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edt_update_type = findViewById(R.id.edt_update_type);
        edt_update_price = findViewById(R.id.edt_update_price);
        edt_update_country = findViewById(R.id.edt_update_country);
        btn_update_save = findViewById(R.id.btn_update_save);
        btn_update_back = findViewById(R.id.btn_update_back);

        Bundle bundle = getIntent().getExtras();
        Wood wood = (Wood) bundle.getSerializable("wood");

        edt_update_type.setText(wood.getType());
        edt_update_country.setText(wood.getCountry());
        edt_update_price.setText(wood.getPrice()+"");

        btn_update_save.setOnClickListener(v->{
            StringRequest stringRequest = new StringRequest(Request.Method.PUT, url+"/"+wood.getId(),
                    response -> Toast.makeText(UpdateActivity.this,"Cap nhat thanh cong....",Toast.LENGTH_SHORT).show(),
                    error -> Toast.makeText(UpdateActivity.this,"Cap nhat thanh cong....",Toast.LENGTH_SHORT).show()){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String> map = new HashMap<>();
                    map.put("type",edt_update_type.getText().toString());
                    map.put("price",edt_update_price.getText().toString());
                    map.put("country",edt_update_country.getText().toString());
                    return map;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
            startActivity(new Intent(UpdateActivity.this,InformationActivity.class));
        });
        btn_update_back.setOnClickListener(v->finish());


    }
}