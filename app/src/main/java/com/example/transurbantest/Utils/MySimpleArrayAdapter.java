package com.example.transurbantest.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transurbantest.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MySimpleArrayAdapter extends RecyclerView.Adapter<MySimpleArrayAdapter.myviewholders> {

    private final ArrayList<String> shiplist;
    private final ArrayList<String> namelist;
    private Context appctx;

    public MySimpleArrayAdapter(List<String> shiplist, List<String> namelist) {
        this.shiplist = (ArrayList<String>) shiplist;
        this.namelist = (ArrayList<String>) namelist;


    }

    @NonNull
    @NotNull
    @Override
    public MySimpleArrayAdapter.myviewholders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_row, parent, false);
        return new MySimpleArrayAdapter.myviewholders(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MySimpleArrayAdapter.myviewholders holder, int position) {


        holder.name.setText(shiplist.get(position));
        holder.subtitle.setText(namelist.get(position));

    }

    @Override
    public int getItemCount() {
        return shiplist.size();
    }

    public class myviewholders extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView subtitle;
        private TextView model;


        public myviewholders(@NonNull @NotNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.textView4);
            subtitle = itemView.findViewById(R.id.textView5);


        }

    }


}
