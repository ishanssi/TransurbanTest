package com.example.transurbantest.ui.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.transurbantest.MainActivity;
import com.example.transurbantest.R;
import com.example.transurbantest.Utils.Ships_Adapter;
import com.example.transurbantest.Utils.Ships_model;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import database.ShipsList;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class HomeFragment extends Fragment {
    public static String KEY = "Ship_obj";
    private static ArrayList<Ships_model> shipdetails_List;
    RequestQueue queue;
    View view;
    Bundle bundle;
    JsonObjectRequest request;
    String DATA_URL = "https://swapi.dev/api/starships";
    AlertDialog.Builder builder;
    String url;
    Ships_model ships_fav_obj;
    private Ships_Adapter mAdapter;
    private ShimmerFrameLayout shimerlayout;
    private RecyclerView mainresView;
    private Ships_model ships_obj;
    private ShipsList shipsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.fragment_home, container, false);

        queue = Volley.newRequestQueue(getContext());
        shipdetails_List = new ArrayList<>();

        url = DATA_URL;
        bundle = new Bundle();
        mainresView = (RecyclerView) view.findViewById(R.id.popularresview);
        mainresView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        OverScrollDecoratorHelper.setUpOverScroll(mainresView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);


        if (isOnline()) {
            jsonParse();
        } else {

            showDialog(getContext(), "! warning ", "check your internet connection");
        }


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //  view.setBackgroundColor(ContextCompat.getColor(getContext(), mColor));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // binding = null;
    }

    private void jsonParse() {

        ProgressDialog pdialog = new ProgressDialog(getContext());
        pdialog.setMessage("Loading...");
        pdialog.setCancelable(false);
        pdialog.show();

        request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);

                                String name = employee.getString("name");
                                String model = employee.getString("model");
                                String manufacturer = employee.getString("manufacturer");

                                String cost_in_credits = employee.getString("cost_in_credits");
                                String length = employee.getString("length");
                                String max_atmosphering_speed = employee.getString("max_atmosphering_speed");
                                String crew = employee.getString("crew");
                                String passengers = employee.getString("passengers");
                                String cargo_capacity = employee.getString("cargo_capacity");
                                String consumables = employee.getString("consumables");
                                String hyperdrive_rating = employee.getString("hyperdrive_rating");
                                String MGLT = employee.getString("MGLT");
                                String starship_class = employee.getString("starship_class");

                                ships_obj = new Ships_model(name, model, manufacturer, cost_in_credits, length, max_atmosphering_speed, crew, passengers
                                        , cargo_capacity, consumables, hyperdrive_rating, MGLT, starship_class);
                                shipdetails_List.add(ships_obj);


                            }
                            pdialog.cancel();
                            mAdapter = new Ships_Adapter(shipdetails_List, getContext());
                            //  mAdapter = new Ships_Adapter(MainActivity.DataList, getContext());
                            mainresView.setAdapter(mAdapter);

                            mAdapter.setOnItemClickListener(new Ships_Adapter.ShipsList_OnItemClickListener() {
                                @Override
                                public void onItemClick_ship(Ships_model postion) {

                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable(KEY, postion);
                                    Navigation.findNavController(getView()).navigate(R.id.action_nav_home_to_ship_details_fragment, bundle);

                                }

                            });
                            mAdapter.setOnItemClickListene_fav(new Ships_Adapter.ShipsList_OnItemClickListener_fav() {
                                @Override
                                public void onDeleteClick(Ships_model shipsobj) {

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


                                    if (MainActivity.favoriteDatabase.favoriteDao().isFavorite(shipsobj.getName()) != 1) {


                                        MainActivity.favoriteDatabase.favoriteDao().addData(shipsList);


                                        ships_fav_obj = new Ships_model(shipsobj.getName(), shipsobj.getModel(), shipsobj.getManufacturer(), shipsobj.getCost_in_credits()
                                                , shipsobj.getLength(), shipsobj.getMax_atmosphering_speed(), shipsobj.getCrew(), shipsobj.getPassengers(), shipsobj.getCargo_capacity(), shipsobj.getConsumables()
                                                , shipsobj.getHyperdrive_rating(), shipsobj.getMGLT(), shipsobj.getStarship_class());

                                        mAdapter.notifyDataSetChanged();

                                    } else {


                                        MainActivity.favoriteDatabase.favoriteDao().delete(shipsList);


                                        mAdapter.notifyDataSetChanged();
                                    }


                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

                if (error instanceof NetworkError) {
                    Toast.makeText(getContext(),
                            "Network Error...",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {


                    Toast.makeText(getContext(),
                            "Server Error..",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getContext(),
                            "Authentication Faliure...",
                            Toast.LENGTH_LONG).show();

                } else if (error instanceof ParseError) {
                } else if (error instanceof NoConnectionError) {

                    Toast.makeText(getContext(),
                            "oonnection Error..",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getContext(),
                            "Server Time out. Trying Again... please wait",
                            Toast.LENGTH_LONG).show();
                    request.setRetryPolicy(new DefaultRetryPolicy(
                            6000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    queue.add(request);
                }
            }
        });

        queue.add(request);
    }


    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(getContext().CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public void showDialog(Context ctx, String title, CharSequence message) {
        builder = new AlertDialog.Builder(ctx);

        if (title != null) builder.setTitle(title);

        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                getActivity().finish();
            }
        });

        builder.show();
    }
}