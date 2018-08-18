package com.example.yhz.multipleglidview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.yhz.multipleglidview.view.FontNode;
import com.example.yhz.multipleglidview.view.FourRingsNode;
import com.simple.multipleglid.NodeImp;
import com.example.yhz.multipleglidview.view.SingleRingNode;
import com.simple.multipleglid.MultipleGlidView;
import com.simple.multipleglid.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MultipleGlidView top;
    private MultipleGlidView left;
    private MultipleGlidView middle;
    private MultipleGlidView right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        top = findViewById(R.id.top);
        left = findViewById(R.id.left);
        middle = findViewById(R.id.middle);
        right = findViewById(R.id.right);
        makeTestData();
    }

    private void makeTestData() {
        List<NodeImp> list = new ArrayList<>();
        list.add(new FontNode(0, 0, "龙", Color.RED, Color.BLUE));
        list.add(new FontNode(0, 1, "龙", Color.WHITE, Color.GRAY));
        list.add(new FontNode(1, 2, "龙", Color.GRAY, Color.BLUE));
        list.add(new FontNode(1, 3, "龙", Color.GREEN, Color.WHITE));
        list.add(new FontNode(3, 4, "龙", Color.BLACK, Color.BLUE));
        list.add(new FontNode(3, 5, "龙", Color.BLUE, Color.RED));
        list.add(new FontNode(5, 5, "龙", Color.DKGRAY, Color.BLUE));
        top.setNodeList(list);

        list = new ArrayList<>();
        list.add(new SingleRingNode(2, 1, Utils.dip2px(this, 1), Color.BLUE, true));
        float w = Utils.cutNodeWidth(this, 20, 2, left.getLineWeight());
        Log.e("MultipleGlidView", "Class:MainActivity-----Method:makeTestData\n" + w);
        left.setmNodeDimen(new float[]{w, w});
        left.setNodeList(list);

        list = new ArrayList<>();
        list.add(new SingleRingNode(2, 1, Utils.dip2px(this, 1), Color.BLUE, true));
        middle.setmNodeDimen(new float[]{w, w});
        middle.setNodeList(list);

        list = new ArrayList<>();
        right.setNodeList(list);
        list.add(new FourRingsNode(2,
                                   0,
                                   Color.BLUE,
                                   Color.GRAY,
                                   Utils.dip2px(this, 1),
                                   0,
                                   1));
    }

    public void onclick(View view) {
        List<NodeImp> list = new ArrayList<>();
        list.add(new FontNode(3, 3, "帅", Color.DKGRAY, Color.BLUE));
        top.setNodeList(list);
    }

    public void goExactlyWidthActivity(View view) {
        startActivity(new Intent(this, ExactlyWidthActivity.class));
    }
}
