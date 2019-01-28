package com.example.agent.midyearjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class End extends AppCompatActivity implements Serializable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Intent i = getIntent();
        Log.d("huh", "work tho?");
        final List<Question> List=(List<Question>)i.getSerializableExtra("questions");
        Button newO = (Button) findViewById(R.id.NewTest);
        TextView scoring = (TextView) findViewById(R.id.Scoring);
        final Button replay = (Button) findViewById(R.id.Iyaz);

        Button reRun = (Button) findViewById(R.id.Review);
        Button leave = (Button) findViewById(R.id.Exit);
        scoring.setText("Your score is: "+i.getStringExtra("score")+"/5");
        leave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

        newO.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent Testing = new Intent(End.this, MainActivity.class);
                startActivity(Testing);
            }
        });
        replay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent Replay = new Intent(End.this, QuizActivity.class);
                Replay.putExtra("questions", (Serializable) List);
                Replay.putExtra("topic", "Replay");

                startActivity(Replay);
            }
        });
        reRun.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent Review = new Intent(End.this, QuizActivity.class);
                Review.putExtra("topic", "review");
                Review.putExtra("questions", (Serializable) List);
                startActivity(Review);
            }
        });
    }
}
