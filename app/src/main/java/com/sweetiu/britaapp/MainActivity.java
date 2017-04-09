package com.sweetiu.britaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.sweetiu.britaappsdk.SDK;
import com.sweetiu.britaappsdk.btml.BtmlParser;
import com.sweetiu.britaappsdk.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);



        SDK.init(this);

        try {
            List<View> views = BtmlParser.parseFromAssets(this, "index.btml");
            views.get(0).render(relativeLayout);
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
