package com.daylon.simonsays;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.daylon.simonsays.R;
import com.firebase.client.Firebase;

public class SubmitScore extends AppCompatActivity {

    ImageButton submit;
    EditText name;
    EditText scoretext;
    Firebase ref;
    TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_score);

        ref = new Firebase("https://daylonsimonsays.firebaseio.com/");

        submit = (ImageButton) findViewById(R.id.submit);
        name = (EditText) findViewById(R.id.name);
        scoretext = (EditText) findViewById(R.id.scoretext);
        error = (TextView) findViewById(R.id.error);
        scoretext.setText("Score: " + getIntent().getIntExtra("score", 0));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length()<14) {
                    UserScore u = new UserScore(name.getText().toString(), (int) getIntent().getExtras().get("score"));
                    ref.push().setValue(u);
                    finish();
                }
                else{
                    error.setText("Name too long! Shorten and try again!");
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_submit_score, menu);
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
}
