package com.example.a8_18084791_truongcongcuong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ManagerActivity extends AppCompatActivity {
    Button btn_manager_show,btn_manager_add,btn_manager_logout;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();

        btn_manager_add = findViewById(R.id.btn_manager_add);
        btn_manager_show = findViewById(R.id.btn_manager_show);
        btn_manager_logout = findViewById(R.id.btn_manager_logout);

        btn_manager_add.setOnClickListener(v->startActivity(new Intent(ManagerActivity.this,AddProductActivity.class)));
        btn_manager_show.setOnClickListener(v->startActivity(new Intent(ManagerActivity.this,InformationActivity.class)));
        btn_manager_logout.setOnClickListener(v->{
            auth.signOut();
            startActivity(new Intent(ManagerActivity.this,MainActivity.class));
        });
    }
}