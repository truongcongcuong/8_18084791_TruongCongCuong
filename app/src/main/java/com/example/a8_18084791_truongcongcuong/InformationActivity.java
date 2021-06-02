package com.example.a8_18084791_truongcongcuong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends AppCompatActivity {
    RecyclerView rcv_infor_woods;
    Button btn_infor_back;
    WoodAdapter adapter;
    List<Wood> woods = new ArrayList<>();
    String url = "https://60ad9ae180a61f00173313b8.mockapi.io/wood";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        getSupportActionBar().hide();

        rcv_infor_woods = findViewById(R.id.rcv_infor_woods);
        btn_infor_back = findViewById(R.id.btn_infor_back);

        getDataFromMockAPI();
//        adapter = new WoodAdapter(woods,this);
//        rcv_infor_woods.setAdapter(adapter);
        rcv_infor_woods.setLayoutManager(new GridLayoutManager(this,1));

        btn_infor_back.setOnClickListener(v->finish());


    }

    private void getDataFromMockAPI() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                response -> {
                    woods = new ArrayList<>();
                    for(int i=0; i<response.length(); i++){
                        try {
                            JSONObject object = (JSONObject) response.get(i);
                            Wood wood = new Wood(
                                    object.getInt("id"),
                                    object.getString("type"),
                                    object.getDouble("price"),
                                    object.getString("country")
                            );
                            woods.add(wood);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter = new WoodAdapter(woods,InformationActivity.this);
                    rcv_infor_woods.setAdapter(adapter);
                },
                error -> Toast.makeText(InformationActivity.this, "Error by get Json Array!", Toast.LENGTH_SHORT).show());
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}