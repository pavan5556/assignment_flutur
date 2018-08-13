package com.sailstudios.apps.fluturassignment;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView1, recyclerView2, recyclerView3;
    private Button playButton, resetButton;
    private ArrayList<Integer> imageList;
    private int random1, random2, random3;

    public static final int SCROLL_OFFSET = 80;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView1 = findViewById(R.id.recyclerview1);
        recyclerView2 = findViewById(R.id.recyclerview2);
        recyclerView3 = findViewById(R.id.recyclerview3);
        playButton = findViewById(R.id.play_button);
        resetButton = findViewById(R.id.reset_button);
        resetButton.setEnabled(false);

        imageList = new ArrayList<>();
        imageList.add(R.drawable.edison);
        imageList.add(R.drawable.einstein);
        imageList.add(R.drawable.hawking);
        imageList.add(R.drawable.newton);
        imageList.add(R.drawable.tesla);


        final LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(layoutManager1);

        final LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView2.setLayoutManager(layoutManager2);

        final LinearLayoutManager layoutManager3 = new LinearLayoutManager(this);
        layoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView3.setLayoutManager(layoutManager3);

        MyImageAdapter adapter = new MyImageAdapter(this, imageList);

        recyclerView1.setAdapter(adapter);
        recyclerView2.setAdapter(adapter);
        recyclerView3.setAdapter(adapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView1.smoothScrollBy(0, 150);
                recyclerView2.smoothScrollBy(0, 150);
                recyclerView3.smoothScrollBy(0, 150);
            }
        }, 200);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playButton.setEnabled(false);
                resetButton.setEnabled(true);

                random1 = new Random().nextInt(5); //3 random Nos for 3 wheels.
                random2 = new Random().nextInt(5);
                random3 = new Random().nextInt(5);
                layoutManager1.startSmoothScroll(getSmoothScroler(random1));  //Using SmoothScroll to scroll to the image
                layoutManager2.startSmoothScroll(getSmoothScroler(random2));
                layoutManager3.startSmoothScroll(getSmoothScroler(random3));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView1.smoothScrollBy(0, 150);
                        recyclerView2.smoothScrollBy(0, 150);
                        recyclerView3.smoothScrollBy(0, 150);
                    }
                }, 1800);

                Toast.makeText(MainActivity.this, getName(random1) + " " + getName(random2) + " " + getName(random3), Toast.LENGTH_LONG).show();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playButton.setEnabled(true);
                resetButton.setEnabled(false);

                layoutManager1.scrollToPosition(0);
                layoutManager2.scrollToPosition(0);
                layoutManager3.scrollToPosition(0);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView1.smoothScrollBy(0, 150);
                        recyclerView2.smoothScrollBy(0, 150);
                        recyclerView3.smoothScrollBy(0, 150);
                    }
                }, 200);
            }
        });

    }

    public String getName(int position) {  //Get Name Method to return image name.

        switch (position) {
            case 0:
                return "Edison";
            case 1:
                return "Einstein";
            case 2:
                return "Hawking";
            case 3:
                return "Newton";
            case 4:
                return "Tesla";
        }

        return null;
    }

    public RecyclerView.SmoothScroller getSmoothScroler(int position) {

        RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(this) {
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
        smoothScroller.setTargetPosition((position + SCROLL_OFFSET - 1));

        return smoothScroller;
    }
}
