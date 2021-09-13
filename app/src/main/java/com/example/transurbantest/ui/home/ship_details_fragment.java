package com.example.transurbantest.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transurbantest.MainActivity;
import com.example.transurbantest.R;
import com.example.transurbantest.Utils.MySimpleArrayAdapter;
import com.example.transurbantest.Utils.Ships_model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import database.ShipsList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ship_details_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ship_details_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View view;
    ShipsList shipsList;
    boolean IS_FAVOURITE = false;
    // TODO: Rename and change types of parameters
    private Ships_model mParam1;
    private Ships_model ships_fav_obj;


    public ship_details_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ship_details_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ship_details_fragment newInstance(String param1, String param2) {
        ship_details_fragment fragment = new ship_details_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //  mParam1 = getArguments().getParcelable("MyData");

            mParam1 = (Ships_model) getArguments().getSerializable(HomeFragment.KEY);

            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(mParam1.getName());


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ship_details_fragment, container, false);
        FloatingActionButton btn_fav = (FloatingActionButton) view.findViewById(R.id.floating_action);
        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                addtofavourites(mParam1, btn_fav);
            }
        });

        if (MainActivity.favoriteDatabase.favoriteDao().isFavorite(mParam1.getName()) != 1) {
            IS_FAVOURITE = false;
            btn_fav.setImageResource(R.mipmap.fav_normal);
        } else {
            btn_fav.setImageResource(R.mipmap.fav_added);
            IS_FAVOURITE = true;
        }

        loadlistview(mParam1);
        return view;
    }

    private void loadlistview(Ships_model mParam1) {
        View myLayout = view.findViewById(R.id.parentinclude);


        String[] title_values = new String[]{"Name", "Model", "Mnufacturer",
                "Cost in", "Length", "Max atmosphering", "Crew", "Passengers",
                "Cargoo Capacity", "Conumables", "Hyperdrive rating", "MGLT", "Starshp Class"};

        String[] subtitle_values = new String[]{mParam1.getName(), mParam1.getModel(), mParam1.getManufacturer(),
                mParam1.getCost_in_credits(), mParam1.getLength(), mParam1.getMax_atmosphering_speed(), mParam1.getCrew(), mParam1.getPassengers(),
                mParam1.getCargo_capacity(), mParam1.getConsumables(), mParam1.getHyperdrive_rating(), mParam1.getMGLT(), mParam1.getStarship_class(),
        };

        List<String> stringList_header = new ArrayList<String>(Arrays.asList(title_values));
        List<String> stringList2_subtitle = new ArrayList<String>(Arrays.asList(subtitle_values));


        RecyclerView mainresView = myLayout.findViewById(R.id.details_listview);
        mainresView.setLayoutManager(new LinearLayoutManager(getContext()));
        MySimpleArrayAdapter mAdapter = new MySimpleArrayAdapter(stringList_header, stringList2_subtitle);
        mainresView.setAdapter(mAdapter);
        mainresView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

    }


    public void addtofavourites(Ships_model shipsobj, FloatingActionButton btnfav) {


        shipsList = new ShipsList();
        shipsList.setName(shipsobj.getName());
        shipsList.setModel(shipsobj.getModel());
        shipsList.setManufacturer(shipsobj.getManufacturer());
        shipsList.setConstin(shipsobj.getCost_in_credits());
        shipsList.setLength(shipsobj.getLength());
        shipsList.setMax_atmosphering(shipsobj.getMax_atmosphering_speed());
        shipsList.setCrew(shipsobj.getCrew());
        shipsList.setPassengers(shipsobj.getPassengers());
        shipsList.setCargoo_Capacity(shipsobj.getCargo_capacity());
        shipsList.setConumables(shipsobj.getConsumables());
        shipsList.setHyperdrive_rating(shipsobj.getHyperdrive_rating());
        shipsList.setMGLT(shipsobj.getMGLT());
        shipsList.setStarshp_Class(shipsobj.getStarship_class());

        ships_fav_obj = new Ships_model(shipsobj.getName(), shipsobj.getModel(), shipsobj.getManufacturer(), shipsobj.getCost_in_credits()
                , shipsobj.getLength(), shipsobj.getMax_atmosphering_speed(), shipsobj.getCrew(), shipsobj.getPassengers(), shipsobj.getCargo_capacity(), shipsobj.getConsumables()
                , shipsobj.getHyperdrive_rating(), shipsobj.getMGLT(), shipsobj.getStarship_class());

        if (MainActivity.favoriteDatabase.favoriteDao().isFavorite(shipsobj.getName()) != 1) {

            MainActivity.favoriteDatabase.favoriteDao().addData(shipsList);
            Toast.makeText(getContext(), R.string.Toast_Added_fav, Toast.LENGTH_SHORT).show();

            btnfav.setImageResource(R.mipmap.fav_added);

        } else {
            MainActivity.favoriteDatabase.favoriteDao().delete(shipsList);
            Toast.makeText(getContext(), R.string.Toast_Removed_fav, Toast.LENGTH_SHORT).show();
            btnfav.setImageResource(R.mipmap.fav_normal);


        }

    }

}