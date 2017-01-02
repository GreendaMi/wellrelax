package com.greendami.wellrelax;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.RecyAdapter;
import Module.ACache;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by GreendaMi on 17/1/1.
 */

public class ChooseSaved extends Activity {

    @Bind(R.id.recycle)
    RecyclerView recycle;
    RecyclerView.Adapter mAdapter;

    ArrayList<HashMap> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosesave);
        ButterKnife.bind(this);
        ACache mCache = ACache.get(this);
        if((List<Map>)mCache.getAsObject("List") != null){
            mList = (ArrayList<HashMap>)mCache.getAsObject("List");
        }
        initView();
    }

    private void initView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycle.setLayoutManager(mLayoutManager);
        recycle.setHasFixedSize(true);
        mAdapter = new RecyAdapter(this,mList);
        recycle.setAdapter(mAdapter);
    }
}
