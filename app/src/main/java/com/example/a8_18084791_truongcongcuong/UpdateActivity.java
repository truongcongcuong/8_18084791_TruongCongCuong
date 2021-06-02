package com.example.a8_18084791_truongcongcuong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.toolbox.StringRequest;

public class UpdateActivity extends AppCompatActivity {
    Button btn_update_save, btn_update_back;
    EditText edt_update_type, edt_update_price,edt_update_country;


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
        edt_update_price.setText(wood.getCountry());

        btn_update_save.setOnClickListener(v->{
            StringRequest stringRequest = new StringRequest();
        });


    }
}