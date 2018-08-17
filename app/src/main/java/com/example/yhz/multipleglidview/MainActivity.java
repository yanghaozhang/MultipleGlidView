package com.example.yhz.multipleglidview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yhz.multipleglidview.view.FontNode;
import com.example.yhz.multipleglidview.view.FourRingsNode;
import com.example.yhz.multipleglidview.view.NodeImp;
import com.example.yhz.multipleglidview.view.SingleRingNode;
import com.example.yhz.multipleglidview.view.YHZGridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private YHZGridView top;
    private YHZGridView left;
    private YHZGridView right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        top = findViewById(R.id.top);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        makeTestData();
    }

    private void makeTestData() {
        List<NodeImp> list = new ArrayList<>();
        float nodeWidth = YHZGridView.dip2px(this, 20);
        list.add(new FontNode(0, 0, "龙", Color.RED, Color.BLUE));
        list.add(new FontNode(0, 1, "龙", Color.WHITE, Color.GRAY));
        list.add(new FontNode(1, 2, "龙", Color.GRAY, Color.BLUE));
        list.add(new FontNode(1, 3, "龙", Color.GREEN, Color.WHITE));
        list.add(new FontNode(3, 4, "龙", Color.BLACK, Color.BLUE));
        list.add(new FontNode(3, 5, "龙", Color.BLUE, Color.RED));
        list.add(new FontNode(5, 5, "龙", Color.DKGRAY, Color.BLUE));
        top.setNodeList(list);
        top.setLineH(YHZGridView.dip2px(this, 1));
        top.setNoBottom(true);
        top.setColumnCount(6);
        top.setRowCount(6);
        top.setmNodeDimen(new float[]{nodeWidth,nodeWidth});

        list = new ArrayList<>();
        list.add(new SingleRingNode(2, 1, YHZGridView.dip2px(this, 1), Color.YELLOW, true));
        left.setNodeList(list);
        left.setColumnCount(3);
        left.setRowCount(3);
        left.setNoRight(true);
        left.setLineH(YHZGridView.dip2px(this, 1));
        left.setmNodeDimen(new float[]{nodeWidth,nodeWidth});

        list = new ArrayList<>();
        right.setNodeList(list);
        right.setColumnCount(3);
        right.setRowCount(3);
        right.setLineH(YHZGridView.dip2px(this, 1));
        right.setmNodeDimen(new float[]{nodeWidth,nodeWidth});
        list.add(new FourRingsNode(2,
                0,
                Color.YELLOW,
                Color.GRAY,
                YHZGridView.dip2px(this, 1),
                0,
                1));
    }
}
