package com.example.sunmin_project.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sunmin_project.HttpStore;
import com.example.sunmin_project.MyApplication;
import com.example.sunmin_project.R;
import com.example.sunmin_project.httpConnection;

public class Fragment1 extends Fragment {

    private RecyclerView recycler;
    private TextView text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment1, container, false);
        recycler=(RecyclerView) rootView.findViewById(R.id.recycler_view);
        //text = (TextView) rootView.findViewById(R.id.text_view);

        //initView();

        setView();

        return rootView;
    }

    /*private void initView() {
        recycler = (RecyclerView)
    }*/

    public void setView(){
        if(HttpStore.getInstance().getResult() == null){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setView();
                }
            },100);
            return;
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(HttpStore.getInstance().getResult());
            }
        });
    }
}
