package com.daylon.simonsays;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.daylon.simonsays.R;

public class MenuScreen extends AppCompatActivity {

    ImageButton start;
    ImageButton scoreboard;
    ImageView animation;
    int [] images;
    MediaPlayer mp;

    @Override
    protected void onResume(){
        super.onResume();
        mp.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        mp = MediaPlayer.create(this, R.raw.incidental_music);
        mp.setLooping(true);
        mp.start();
        setButtonListeners();
    }

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
                mp.pause();
                startActivity(i);
            }
        });
        scoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuScreen.this, leaderboard.class);
                startActivity(i);
            }
        });
    }
}
