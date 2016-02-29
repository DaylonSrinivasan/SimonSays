package com.daylon.simonsays;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.daylon.simonsays.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageButton topLeft;
    ImageButton topRight;
    ImageButton botLeft;
    ImageButton botRight;

    Button play;
    Button quit;
    Button submit;

    List<Integer> answer = new ArrayList<Integer>();
    List<Integer> mySequence = new ArrayList<Integer>();

    Random random = new Random();

    boolean playable = true;
    int level = 1; // how many in answer
    int lives = 3; // how many errors allowed
    TextView curr_level_text;
    TextView level_complete_text;
    TextView lives_left_text;

    //colors
    int topLeftColorLight = 0xff33b5e5; // light blue
    int topRightColorLight = 0xffff4444; // light red
    int botLeftColorLight = 0xff99cc00; // light green
    int botRightColorLight = 0xffffbb33; // light orange

    int topLeftColorDark = 0xff0099cc; // dark blue
    int topRightColorDark = 0xffcc0000; // dark red
    int botLeftColorDark = 0xff669900; // dark green
    int botRightColorDark = 0xffff8800; // dark orange

    SoundPool soundmanager;

    int [] mysounds = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSounds();
        setButtonListeners();
        setColors();
        setTexts();

        final String s = "GET READY!!!";
        new CountDownTimer(100*s.length(), 100){
            public void onFinish(){
                setNewRound();
            }

            public void onTick(long millis) {
                level_complete_text.setText(s.substring(0, (100*s.length()-(int)millis)/100));
            }
        }.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void setTexts(){
        curr_level_text = (TextView) findViewById(R.id.curr_level_text);
        level_complete_text = (TextView) findViewById(R.id.level_complete);
        lives_left_text = (TextView) findViewById(R.id.lives_left_text);
        level_complete_text.setText("");

    }
    private void refreshLevelText(){
        String s = "Current Level: " + level;
        curr_level_text.setText(s);
    }

    private void refreshLivesText(){
        String s = "Lives Left: " + lives;
        lives_left_text.setText(s);
    }
    private void showAnswerColors() {
        playable = false;
        new CountDownTimer(answer.size()*500, 250) {
            int curr = 0; //current index of answer array
            boolean show = true; //only show every other time
            public void onTick(long millisUntilFinished) {
                topLeft.setBackgroundColor(topLeftColorDark);
                topRight.setBackgroundColor(topRightColorDark);
                botLeft.setBackgroundColor(botLeftColorDark);
                botRight.setBackgroundColor(botRightColorDark);
                if(show) {
                    switch (answer.get(curr)) {
                        case 0:
                            topLeft.setBackgroundColor(topLeftColorLight);
                            soundmanager.play(mysounds[0], 1, 1, 1, 0, 1);
                            break;
                        case 1:
                            topRight.setBackgroundColor(topRightColorLight);
                            soundmanager.play(mysounds[1], 1, 1, 1, 0, 1);
                            break;
                        case 2:
                            botLeft.setBackgroundColor(botLeftColorLight);
                            soundmanager.play(mysounds[2], 1, 1, 1, 0, 1);
                            break;
                        case 3:
                            botRight.setBackgroundColor(botRightColorLight);
                            soundmanager.play(mysounds[3], 1, 1, 1, 0, 1);
                            break;
                        default:
                            System.out.println("Error");
                            break;

                    }
                    curr++;
                    show=false;
                }
                else{
                    show = true;
                }

            }

            public void onFinish() {
                playable=true;
                topLeft.setBackgroundColor(topLeftColorDark);
                topRight.setBackgroundColor(topRightColorDark);
                botLeft.setBackgroundColor(botLeftColorDark);
                botRight.setBackgroundColor(botRightColorDark);
            }
        }.start();

    }

    private void setSounds(){
        soundmanager = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        mysounds[0]=soundmanager.load(this, R.raw.c_note, 1);
        mysounds[1]=soundmanager.load(this, R.raw.d_note, 1);
        mysounds[2]=soundmanager.load(this, R.raw.e_note, 1);
        mysounds[3]=soundmanager.load(this, R.raw.f_note, 1);
        soundmanager.play(mysounds[3], 1, 1, 1, 0, 1);
    }
    private void setColors(){
        topLeft.setBackgroundColor(topLeftColorDark);
        topRight.setBackgroundColor(topRightColorDark);
        botLeft.setBackgroundColor(botLeftColorDark);
        botRight.setBackgroundColor(botRightColorDark);
    }
    private void roundComplete(boolean eq){ // eq is whether level complete or failed
        final String s;
        if(eq) {
            s = "Level complete!!!";
            level++;
        }
        else {
            s = "Level failed!!!";
            lives--;
            refreshLivesText();
        }

        new CountDownTimer(100*s.length(), 100){
            public void onFinish(){
                level_complete_text.setText("");
                if(lives==0)
                    gameOver();
                else
                    setNewRound();
            }

            public void onTick(long millis) {
                level_complete_text.setText(s.substring(0, (100*s.length()-(int)millis)/100));
            }
        }.start();
    }

    private void gameOver(){
        playable = false;
        final String s = "GAME OVER!!!!!";
        new CountDownTimer(100*s.length(), 100){
            public void onFinish(){
                play.setVisibility(View.VISIBLE);
                quit.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
            }

            public void onTick(long millis) {
                level_complete_text.setText(s.substring(0, (100*s.length()-(int)millis)/100));
            }
        }.start();
    }
    private void setNewRound(){
        answer.clear();
        mySequence.clear();
        generateAnswer();
        refreshLevelText();
    }
    private void testEqual(){
        if (mySequence.size() == answer.size()) {
            boolean eq = mySequence.equals(answer);
            roundComplete(eq);
        }
    }

    private void generateAnswer(){
        for(int i = 0; i < level; i++){
            int a = random.nextInt(4); //random int from 0 to 4
            answer.add(a);
        }
        showAnswerColors();
    }


    public void setButtonListeners(){

        topLeft = (ImageButton) findViewById(R.id.top_left);
        topRight = (ImageButton) findViewById(R.id.top_right);
        botLeft = (ImageButton) findViewById(R.id.bot_left);
        botRight = (ImageButton) findViewById(R.id.bot_right);

        play = (Button) findViewById(R.id.play_again);
        quit = (Button) findViewById(R.id.quit);
        submit = (Button) findViewById(R.id.submit_score);

        topLeft.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(playable) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        mySequence.add(0);
                        //change color
                        topLeft.setBackgroundColor(topLeftColorLight);
                        //play sound
                        soundmanager.play(mysounds[0], 1, 1, 1, 0, 1);
                        testEqual();
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        topLeft.setBackgroundColor(topLeftColorDark);
                    }

                }
                return true;
            }
        });

        topRight.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(playable) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        mySequence.add(1);
                        // change color
                        topRight.setBackgroundColor(topRightColorLight);
                        //play sound
                        soundmanager.play(mysounds[1], 1, 1, 1, 0, 1);
                        testEqual();
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        topRight.setBackgroundColor(topRightColorDark);
                    }
                }
                return true;
            }
        });

        botLeft.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(playable) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        mySequence.add(2);
                        // change color
                        botLeft.setBackgroundColor(botLeftColorLight);
                        //play sound
                        soundmanager.play(mysounds[2], 1, 1, 1, 0, 1);
                        testEqual();
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        botLeft.setBackgroundColor(botLeftColorDark);
                    }

                }
                return true;
            }
        });

        botRight.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(playable) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        mySequence.add(3);
                        // change color
                        botRight.setBackgroundColor(botRightColorLight);
                        //play sound
                        soundmanager.play(mysounds[3], 1, 1, 1, 0, 1);
                        testEqual();
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        botRight.setBackgroundColor(botRightColorDark);
                    }
                }
                return true;
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lives = 3;
                level = 1;
                play.setVisibility(View.INVISIBLE);
                quit.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.INVISIBLE);
                refreshLevelText();
                refreshLivesText();
                level_complete_text.setText("");
                setNewRound();
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SubmitScore.class);
                i.putExtra("score", level);
                startActivity(i);
                finish();
            }
        });

    }
}
