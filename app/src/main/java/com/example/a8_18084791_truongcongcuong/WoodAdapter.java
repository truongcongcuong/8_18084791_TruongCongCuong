package com.example.a8_18084791_truongcongcuong;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WoodAdapter extends RecyclerView.Adapter<WoodAdapter.NameViewHolder> {
    LayoutInflater inflater;
    List<Wood> woods;
    Context context;
    String url = "https://60ad9ae180a61f00173313b8.mockapi.io/wood";


    public WoodAdapter(List<Wood> woods, Context context) {
        this.woods = woods;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.line,parent,false);
        return new NameViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder holder, int position) {
        Wood wood = woods.get(position);
        holder.txt_line_stt.setText(position+1+"");
        holder.txt_line_type.setText("Type : "+wood.getType());
        holder.txt_line_price.setText("Price : "+wood.getPrice()+"$");
        holder.txt_line_country.setText("Country : " + wood.getCountry());
        holder.btn_line_delete.setOnClickListener(v->{
            AlertDialog alertDialog = new AlertDialog.Builder(context)
                    .setTitle("SURE DELETE PRODUCT")
                    .setPositiveButton("YES",(dialog, which) -> {
                        StringRequest stringRequest = new StringRequest(Request.Method.DELETE,url+"/"+wood.getId(),
                                response -> Toast.makeText(context,"Xoa thanh cong...",Toast.LENGTH_SHORT).show()
                                ,error -> Toast.makeText(context,"Xoa that bai...",Toast.LENGTH_SHORT).show());
                        RequestQueue requestQueue = Volley.newRequestQueue(context);
                        requestQueue.add(stringRequest);
                        woods.remove(wood);
                        notifyDataSetChanged();
                    })
                    .setNegativeButton("Cancel",null)
                    .create();
            alertDialog.show();
        });

        holder.btn_line_update.setOnClickListener(v->{
            Bundle bundle = new Bundle();
            bundle.putSerializable("wood",wood);
            Intent intent = new Intent(context,UpdateActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });



    }


    @Override
    public int getItemCount() {
        return woods.size();
    }

    public static class NameViewHolder extends RecyclerView.ViewHolder {
        WoodAdapter woodAdapter;
        TextView txt_line_stt,txt_line_type,txt_line_price,txt_line_country;
        Button btn_line_update,btn_line_delete;
        public NameViewHolder(@NonNull View itemView, WoodAdapter woodAdapter) {
            super(itemView);
            txt_line_stt = itemView.findViewById(R.id.txt_line_stt);
            txt_line_type = itemView.findViewById(R.id.txt_line_type);
            txt_line_price = itemView.findViewById(R.id.txt_line_price);
            txt_line_country = itemView.findViewById(R.id.txt_line_country);
            btn_line_update = itemView.findViewById(R.id.btn_line_update);
            btn_line_delete = itemView.findViewById(R.id.btn_line_delete);
            this.woodAdapter = woodAdapter;
        }
    }
}
