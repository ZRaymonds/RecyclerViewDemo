package com.example.recyclerviewdemo2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.recyclerviewdemo2.R;
import com.example.recyclerviewdemo2.adapter.RecycleViewGridAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView rcv;
    private GridLayoutManager gridLayoutManager;
    List<String> lists = new ArrayList<String>();

    private ArrayList<String> list_path;
    private ArrayList<String> list_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        rcv = (RecyclerView) findViewById(R.id.rcv);

        list_path = new ArrayList<>();
        list_title = new ArrayList<>();
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        list_title.add("好好学习");
        list_title.add("天天向上");
        list_title.add("热爱劳动");
        list_title.add("不搞对象");

        gridLayoutManager = new GridLayoutManager(this, 3);
        lists.clear();
        for (int i = 0; i < 99; i++) {
            lists.add("123" + i);
        }
        RecycleViewGridAdapter recycleViewAdapter = new RecycleViewGridAdapter(this, lists, list_path, list_title);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.top, null);
        recycleViewAdapter.addHeadView(view);
        View view1 = layoutInflater.inflate(R.layout.foot, null);
        recycleViewAdapter.addFootView(view1);

        //如果添加了头部或者尾部 就需要做相关的SpanSize的修改
        recycleViewAdapter.setChangeGridLayoutManager(new RecycleViewGridAdapter.ChangeGridLayoutManagerSpance() {
            @Override
            public void change(final int size, final boolean isAddHead, final boolean isAddFoot) {
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        int spanSzie = 1;
                        if (isAddHead) {
                            if (position == 0) {
                                spanSzie = gridLayoutManager.getSpanCount();
                            }
                        }

                        if (isAddFoot) {
                            if (position == size) {
                                spanSzie = gridLayoutManager.getSpanCount();
                            }
                        }
                        return spanSzie;
                    }
                });
            }
        });
        rcv.setLayoutManager(gridLayoutManager);
        rcv.setAdapter(recycleViewAdapter);
    }
}
