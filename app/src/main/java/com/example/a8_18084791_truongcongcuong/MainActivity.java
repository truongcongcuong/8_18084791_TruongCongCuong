package com.example.a8_18084791_truongcongcuong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText edt_main_email, edt_main_pass;
    Button btn_main_login,btn_main_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();

        edt_main_email = findViewById(R.id.edt_main_email);
        edt_main_pass = findViewById(R.id.edt_main_pass);
        btn_main_login = findViewById(R.id.btn_main_login);
        btn_main_exit = findViewById(R.id.btn_main_exit);

        edt_main_email.setText("18084791_TruongCongCuong@gmail.com");
        edt_main_pass.setText("123456");

        btn_main_login.setOnClickListener(v->{
            String email = edt_main_email.getText().toString();
            if(TextUtils.isEmpty(email)){
                Toast.makeText(MainActivity.this,"Nhap email...",Toast.LENGTH_SHORT).show();
                return;
            }
            String pass = edt_main_pass.getText().toString();
            if(TextUtils.isEmpty(pass)){
                Toast.makeText(MainActivity.this,"Nhap mat khau...",Toast.LENGTH_SHORT).show();
                return;
            }

            auth.signInWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                                Toast.makeText(MainActivity.this,"Dang nhap that bai...",Toast.LENGTH_SHORT).show();
                            else {
                                Toast.makeText(MainActivity.this,"Dang nhap thanh cong...",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, ManagerActivity.class));
                            }
                        }
                    });

        });

        btn_main_exit.setOnClickListener(v->this.finishAffinity());

    }
}