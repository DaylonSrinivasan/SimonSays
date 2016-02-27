package com.example.daylon.game2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.firebase.client.Firebase;

public class MenuScreen extends AppCompatActivity {

    ImageButton start;
    ImageButton scoreboard;
    ImageView animation;
    int [] images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        animation = (ImageView)findViewById(R.id.animation);
        setButtonListeners();
        images = new int[30];
        images[0]=R.drawable.p1;
        images[1]=R.drawable.p2;
        images[2]=R.drawable.p3;
        images[3]=R.drawable.p4;
        images[4]=R.drawable.p5;

        Firebase.setAndroidContext(this);

        //Firebase myFirebaseRef = new


    }

    /*private void animate(){
        new CountDownTimer(3000, 1000){

            int i = 0;
            @Override
            public void onTick(long millis){
                animation.setBackground(images[0]);
            }
            @Override
            public void onFinish(){}
        }.start();
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setButtonListeners(){
        start = (ImageButton)findViewById(R.id.start);
        scoreboard = (ImageButton)findViewById(R.id.scoreboard);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuScreen.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
