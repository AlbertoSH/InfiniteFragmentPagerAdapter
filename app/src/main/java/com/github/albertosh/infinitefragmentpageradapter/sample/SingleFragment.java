package com.github.albertosh.infinitefragmentpageradapter.sample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.albertosh.infinitefragmentpageradapter.InfiniteFragmentPagerFragment;

public class SingleFragment extends Fragment implements InfiniteFragmentPagerFragment{

    private final static String ARG_ID = "id";

    private int id;
    private int calculus;

    private TextView text;

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
        text = (TextView) root.findViewById(R.id.text);

        load();

        return root;
    }

     void load() {
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                // Simulate asynchronous load
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                calculus = (int) (Math.pow(id,2) * Math.signum(id));

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                text.setText("calculus: " + calculus);
            }
        }.execute();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("calculus",calculus);
    }

    @Override
    public int getGlobalPosition() {
        return id;
    }
}
