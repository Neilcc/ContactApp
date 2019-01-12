package com.zcc.contactapp.contactmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

import com.zcc.contactapp.R;
import com.zcc.contactapp.contactmodel.adapters.AvatarLinearAdapter;
import com.zcc.contactapp.contactmodel.adapters.DetailLinearAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mAvatarRecyclerView;
    private AvatarLinearAdapter mAvatarAdapter;
    private RecyclerView mDetailRecyclerView;
    private DetailLinearAdapter mDetailAdapter;
    private PagerSnapHelper pagerSnapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mAvatarRecyclerView = findViewById(R.id.rv_avatars);
        LinearLayoutManager avatarLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        mAvatarAdapter = new AvatarLinearAdapter();
        mAvatarRecyclerView.setLayoutManager(avatarLayoutManager);
        mAvatarRecyclerView.setAdapter(mAvatarAdapter);
        mDetailRecyclerView = findViewById(R.id.rv_desc);
        LinearLayoutManager detailLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mDetailAdapter = new DetailLinearAdapter();
        mDetailRecyclerView.setLayoutManager(detailLayoutManager);
        mDetailRecyclerView.setAdapter(mAvatarAdapter);
    }
}
