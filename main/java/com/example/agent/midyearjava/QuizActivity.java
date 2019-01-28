package com.example.agent.midyearjava;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements Serializable {
    MyDataBaseHelper db;
    String topic;
    Button Backward, Forward, Submit, ChoiceA,ChoiceB,ChoiceC,ChoiceD,ChoiceE;
    TextView Question,Position;
    List<Question> qList;
    List<Question> nqList;
    int position;

    public void showQ() {
        Backward.setVisibility(View.VISIBLE);
        Forward.setVisibility(View.VISIBLE);
        Log.d("huh", "1 tho?");
        if (position <= 0)
            Backward.setVisibility(View.INVISIBLE);
        if (position >= 4)
            Forward.setVisibility(View.INVISIBLE);
        Question q = qList.get(position);
        Question.setText(q.getQuestion());
        ChoiceA.setText(q.getChoice(0));
        ChoiceB.setText(q.getChoice(1));
        ChoiceC.setText(q.getChoice(2));
        ChoiceD.setText(q.getChoice(3));
        ChoiceE.setText(q.getChoice(4));
        int fully = (position + 1);
        String val = fully + "/5";
        Position.setText(val);
        String fer = q.Correct() + "";

        ChoiceA.setBackgroundColor(Color.WHITE);
        ChoiceB.setBackgroundColor(Color.WHITE);
        ChoiceC.setBackgroundColor(Color.WHITE);
        ChoiceD.setBackgroundColor(Color.WHITE);
        ChoiceE.setBackgroundColor(Color.WHITE);

        if (q.getChoice(0).equals(q.getSelected())) {
            ChoiceA.setBackgroundColor(Color.GRAY);

        }
        if (q.getChoice(1).equals(q.getSelected())) {
            ChoiceB.setBackgroundColor(Color.GRAY);

        }
        if(q.getChoice(2).equals(q.getSelected())){
            ChoiceC.setBackgroundColor(Color.GRAY);
}
        if(q.getChoice(3).equals(q.getSelected())){
            ChoiceD.setBackgroundColor(Color.GRAY);
}

        if(q.getChoice(4).equals(q.getSelected())){
            ChoiceE.setBackgroundColor(Color.GRAY);
        }


    }
    public void showQandA(){
        Backward.setVisibility(View.VISIBLE);
        Forward.setVisibility(View.VISIBLE);
        Log.d("huh", "review tho?");
        if(position<=0)
            Backward.setVisibility(View.INVISIBLE);
        if(position>=4)
            Forward.setVisibility(View.INVISIBLE);
        Question q = qList.get(position);
        Question.setText(q.getQuestion());
        ChoiceA.setText(q.getChoice(0));
        ChoiceB.setText(q.getChoice(1));
        ChoiceC.setText(q.getChoice(2));
        ChoiceD.setText(q.getChoice(3));
        ChoiceE.setText(q.getChoice(4));
        int fully = (position + 1);
        String val = fully + "/5";
        Position.setText(val);
        ChoiceA.setBackgroundColor(Color.WHITE);
        ChoiceB.setBackgroundColor(Color.WHITE);
        ChoiceC.setBackgroundColor(Color.WHITE);
        ChoiceD.setBackgroundColor(Color.WHITE);
        ChoiceE.setBackgroundColor(Color.WHITE);
        Log.d("huh", "showQandA:1");
        if(q.getChoice(0).equals(q.getAnswer())){
            ChoiceA.setBackgroundColor(Color.GREEN); }

        if(q.getChoice(1).equals(q.getAnswer())){
            ChoiceB.setBackgroundColor(Color.GREEN);

        }
        if(q.getChoice(2).equals(q.getAnswer())){
            ChoiceC.setBackgroundColor(Color.GREEN);

        }
        if(q.getChoice(3).equals(q.getAnswer())){
            ChoiceD.setBackgroundColor(Color.GREEN);

        }
        if(q.getChoice(4).equals(q.getAnswer())){
        ChoiceE.setBackgroundColor(Color.GREEN);

        }
        Log.d("huh", "showQandA:5");

    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();
        topic=intent.getStringExtra("topic");
        db = new MyDataBaseHelper(this);
        try {
            db.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("CRUPS", "1");
        Backward = findViewById(R.id.Backward);
        Forward = findViewById(R.id.Forward);
        Submit = findViewById(R.id.Submit);
        ChoiceA = findViewById(R.id.ChoiceA);
        ChoiceB = findViewById(R.id.ChoiceB);
        ChoiceC = findViewById(R.id.ChoiceC);
        ChoiceD = findViewById(R.id.ChoiceD);
        ChoiceE = findViewById(R.id.ChoiceE);
        Question = findViewById(R.id.Question);
        Position=findViewById(R.id.position);
        Log.d("CRUPS", "2");




        if(!(topic.equals("Replay")||topic.equals("review"))){
            Log.d("huh", "find t tho?");
            qList=db.getQuestions(topic);
            position=0;
            Collections.shuffle(qList);
            qList.subList(5, qList.size()).clear();
            for (Question q: qList){
                q.randomizeChoices();
            }
            showQ();


        ChoiceA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Question thisO = qList.get(position);
                thisO.setSelection(thisO.getChoice(0));
                showQ();

            }
        });
        ChoiceB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Question thisO = qList.get(position);
                thisO.setSelection(thisO.getChoice(1));
                showQ();

            }
        });
        ChoiceC.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Question thisO = qList.get(position);
                thisO.setSelection(thisO.getChoice(2));
                showQ();

            }
        });
        ChoiceD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Question thisO = qList.get(position);
                thisO.setSelection(thisO.getChoice(3));
                showQ();

            }
        });
        ChoiceE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question thisO = qList.get(position);
                thisO.setSelection(thisO.getChoice(4));

                showQ();
            }
        });

        Forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                showQ();
            }
        });
        Backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                showQ();
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            int scoring = 0;
            @Override
            public void onClick(View v) {
                for(Question q: qList){
                    scoring+=q.Correct();}
                String hi=scoring+"";
                Intent End = new Intent(QuizActivity.this, End.class);
                End.putExtra("questions", (Serializable) qList);
                End.putExtra("score", hi);
                startActivity(End);
            }
        });}

        if(topic.equals("review")) {
            Log.d("huh", "review tho?");
            position=0;
            Submit.setText("New Test");
            qList = (List<Question>) intent.getSerializableExtra("questions");
            showQandA();


            Forward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position++;
                    showQandA();
                }
            });
            Backward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position--;
                    showQandA();
                }
            });
            Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent Testing = new Intent(QuizActivity.this, MainActivity.class);
                    startActivity(Testing);
                }
            });
        }

        if(topic.equals("Replay")) {
            Log.d("huh", "REplay");



            qList= (List<Question>) intent.getSerializableExtra("questions");
            Collections.shuffle(qList);
            for(int i =0; i<qList.size();i++){
                qList.get(i).randomizeChoices();}
            position=0;
            showQ();
            Forward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position++;
                    showQ();
                }
            });
            Backward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position--;
                    showQ();
                }
            });
            ChoiceA.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Question thisO = qList.get(position);
                    thisO.setSelection(thisO.getChoice(0));
                    showQ();

                }
            });
            ChoiceB.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Question thisO = qList.get(position);
                    thisO.setSelection(thisO.getChoice(1));
                    showQ();

                }
            });
            ChoiceC.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Question thisO = qList.get(position);
                    thisO.setSelection(thisO.getChoice(2));
                    showQ();

                }
            });
            ChoiceD.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Question thisO = qList.get(position);
                    thisO.setSelection(thisO.getChoice(3));
                    showQ();

                }
            });
            ChoiceE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Question thisO = qList.get(position);
                    thisO.setSelection(thisO.getChoice(4));
                    showQ();
                }
            });

            Submit.setOnClickListener(new View.OnClickListener() {
                int scoring = 0;
                @Override
                public void onClick(View v) {
                    for(Question q: qList){
                        scoring+=q.Correct();}
                    String hi=scoring+"";
                    Intent End = new Intent(QuizActivity.this, End.class);
                    End.putExtra("questions", (Serializable) qList);
                    End.putExtra("score", hi);
                    startActivity(End);
                }
            });
        }


        }



}
