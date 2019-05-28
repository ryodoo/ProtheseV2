package com.example.android.prothese;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.prothese.Model.Commande;
import com.example.android.prothese.Model.Dent;
import com.example.android.prothese.Model.DentAdapter;
import com.example.android.prothese.conf.Variable_general;

import java.util.List;


/**
 * Fragment class for each nav menu item
 */
public class DentFragment extends Fragment {
     static String ARG_TEXT = null;

    private View mContent;
    private TextView mTextView;
    public String search;
    View view;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private DentAdapter adapter;
    private List<Commande> data_list;

    public static Fragment newInstance(String text, int color) {
        Fragment frag = new DentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        ARG_TEXT=text;
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_dents, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        data_list = Variable_general.commandes;

        gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new DentAdapter(getContext(), data_list);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContent = view.findViewById(R.id.fragment_dents);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARG_TEXT, "walo");
        super.onSaveInstanceState(outState);
    }


}
