package com.github.albertosh.infinitefragmentpageradapter.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SingleFragment extends Fragment {

    private final static String ARG_ID = "id";

    private int id;

    public static SingleFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        SingleFragment fragment = new SingleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt(ARG_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_single, container, false);

        ((TextView)root.findViewById(R.id.textId)).setText("ID: " + id);
        return root;
    }

   public int getMyId() {
       return id;
   }
}
