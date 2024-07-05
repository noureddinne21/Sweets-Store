package com.nouroeddinne.sweetsstore;

import static com.nouroeddinne.sweetsstore.HomeActivity.textNotif;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import Controlar.Adapter;
import Database.DataBaseAccess;
import Model.Model;

public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    static ArrayList<Model> dessertList ;

    public HomeFragment() {}

    public static HomeFragment newInstance(String param1, ArrayList<Model> List) {

        dessertList = List;

        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

//    public static HomeFragment newInstance(ArrayList<Model> dessertList) {
//        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        args.putParcelableArrayList("DESSERT_LIST", (ArrayList<? extends Parcelable>) dessertList);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    public static HomeFragment newInstance(ArrayList<Model> dessertList) {
//        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        // Convert ArrayList<Model> to ArrayList<? extends Parcelable>
//        //ArrayList<? extends Parcelable> parcelableList = new ArrayList<>(dessertList);
//        args.putParcelableArrayList("DESSERT_LIST",dessertList);
//        fragment.setArguments(args);
//        return fragment;
//    }


//    public static void newInstance(ArrayList<Model> List) {
//        dessertList = List;
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

//        adapter = new Adapter(getActivity(), dessertList);
//        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onChanged() {
//                super.onChanged();
//                HomeActivity.textNotif();
//            }
//        });
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

    }

    @Override
    public void onStart() {
        super.onStart();

//        Log.d("TAG", "onStart List : "+dessertList.size());
//        for (Model m: dessertList){
//            Log.d("TAG", "dessert List : "+m.getId()+" getName "+m.getName()+" getPrice "+m.getPrice()+" getType "
//                    +m.getType()+" getFavorite "+m.getFavorite()+" getCart "+m.getCart());
//        }

        adapter = new Adapter(getActivity(), dessertList);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                HomeActivity.textNotif();
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}























