package com.example.transurbantest.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transurbantest.MainActivity;
import com.example.transurbantest.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import database.ShipsList;

public class Fav_Adapter extends RecyclerView.Adapter<Fav_Adapter.myviewholders> {


    private final List<ShipsList> shiplist;
    private final Context appctx;
    private Shipmodel_clicklitner<Ships_model> Shipitem_click;
    private Fav_Adapter.ShipsList_OnItemClickListener mListener;
    private Fav_Adapter.ShipsList_OnItemClickListener_fav mListener_fav;

    public Fav_Adapter(List<ShipsList> shiplist, Context appctx) {
        this.shiplist = shiplist;
        this.appctx = appctx;


    }

    @NonNull
    @NotNull
    @Override
    public Fav_Adapter.myviewholders onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout, parent, false);
        return new Fav_Adapter.myviewholders(view);


    }

    public void shipitemclicked_adapter(Shipmodel_clicklitner<Ships_model> shipmodei_click) {

        this.Shipitem_click = shipmodei_click;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Fav_Adapter.myviewholders holder, int position) {
        final ShipsList shipobj = shiplist.get(position);

        holder.name.setText(shipobj.getName());
        holder.model.setText(shipobj.getModel());
        holder.manifacturer.setText(shipobj.getManufacturer());
        holder.image.setImageResource((R.mipmap.shippng));


        if (MainActivity.favoriteDatabase.favoriteDao().isFavorite(shipobj.getName()) == 1) {

            holder.fav_image.setImageResource(R.mipmap.fav_added);
        } else {
            holder.fav_image.setImageResource(R.mipmap.fav_normal);
        }


    }

    @Override
    public int getItemCount() {
        return shiplist.size();
    }

    public void setOnItemClickListener(Fav_Adapter.ShipsList_OnItemClickListener listener) {
        mListener = listener;
    }

    public void setOnItemClickListene_fav(Fav_Adapter.ShipsList_OnItemClickListener_fav listener) {
        mListener_fav = listener;
    }

    public interface ShipsList_OnItemClickListener {
        void onItemClick_ship(ShipsList postion);
        //void onItemClick(int position);
        //void onDeleteClick(int position);
    }

    public interface ShipsList_OnItemClickListener_fav {

        //void onItemClick(int position);
        void onDeleteClick(ShipsList postion, int itemposition);
    }

    public class myviewholders extends RecyclerView.ViewHolder implements View.OnClickListener {


        private final TextView name;
        private final TextView model;
        private final TextView manifacturer;
        private final ImageView image;
        private final ImageView fav_image;


        public myviewholders(@NonNull @NotNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.txt_name);
            image = itemView.findViewById(R.id.imageView2);
            fav_image = itemView.findViewById(R.id.img_fav);
            model = itemView.findViewById(R.id.txt_model);
            manifacturer = itemView.findViewById(R.id.txt_manifacture);
            fav_image.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick_ship(shiplist.get(position));
                        }
                    }
                }
            });


            fav_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener_fav != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener_fav.onDeleteClick(shiplist.get(position), position);
                        }
                    }
                }
            });

        }

        public void delete(int position) { //removes the row
            shiplist.remove(position);
            notifyItemRemoved(position);
        }

        @Override
        public void onClick(View view) {

            if (view.equals(fav_image)) {
                delete(getAdapterPosition());
            }
        }
    }

}
