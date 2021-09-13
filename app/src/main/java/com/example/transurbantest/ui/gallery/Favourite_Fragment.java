package com.example.transurbantest.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transurbantest.MainActivity;
import com.example.transurbantest.R;
import com.example.transurbantest.Utils.Fav_Adapter;
import com.example.transurbantest.ui.home.HomeFragment;

import java.util.List;

import database.ShipsList;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class Favourite_Fragment extends Fragment {
    public static Fav_Adapter mAdapter;
    List<ShipsList> favoriteLists;
    private RecyclerView mainresview;
    private ShipsList shipsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_gallery, container, false);

        mainresview = (RecyclerView) v.findViewById(R.id.mainresview);

        // use a linear layout manager
        mainresview.setLayoutManager(new LinearLayoutManager(container.getContext()));
        favoriteLists = MainActivity.favoriteDatabase.favoriteDao().getAll();

        if (favoriteLists.size() <= 0) {
            Toast.makeText(getContext(), R.string.Toast_Emptry_fav, Toast.LENGTH_SHORT).show();

        } else {
            mAdapter = new Fav_Adapter(favoriteLists, getContext());
            mainresview.setAdapter(mAdapter);
            OverScrollDecoratorHelper.setUpOverScroll(mainresview, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
            mAdapter.setOnItemClickListener(new Fav_Adapter.ShipsList_OnItemClickListener() {
                @Override
                public void onItemClick_ship(ShipsList postion) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(HomeFragment.KEY, postion);
                    Navigation.findNavController(getView()).navigate(R.id.action_nav_gallery_to_fav_details_fragment, bundle);

                }

            });
            mAdapter.setOnItemClickListene_fav(new Fav_Adapter.ShipsList_OnItemClickListener_fav() {
                @Override
                public void onDeleteClick(ShipsList shipsobj, int position) {


                    shipsList = new ShipsList();


                    shipsList.setName(shipsobj.getName());
                    shipsList.setModel(shipsobj.getModel());
                    shipsList.setManufacturer(shipsobj.getManufacturer());
                    shipsList.setConstin(shipsobj.getConstin());
                    shipsList.setLength(shipsobj.getLength());
                    shipsList.setMax_atmosphering(shipsobj.getMax_atmosphering());
                    shipsList.setCrew(shipsobj.getCrew());
                    shipsList.setPassengers(shipsobj.getPassengers());
                    shipsList.setCargoo_Capacity(shipsobj.getCargoo_Capacity());
                    shipsList.setConumables(shipsobj.getConumables());
                    shipsList.setHyperdrive_rating(shipsobj.getHyperdrive_rating());
                    shipsList.setMGLT(shipsobj.getMGLT());
                    shipsList.setStarshp_Class(shipsobj.getStarshp_Class());


                    MainActivity.favoriteDatabase.favoriteDao().delete(shipsList);

                    removeItem(position);
                    mAdapter.notifyDataSetChanged();


                }
            });
        }


        return v;

    }

    public void removeItem(int position) {
        favoriteLists.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // binding = null;
    }


}