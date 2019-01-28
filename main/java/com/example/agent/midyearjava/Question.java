package com.example.agent.midyearjava;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.Serializable;
@SuppressWarnings("serial")
public class Question implements Serializable {

    private String question;
    private String[] choice = new String[5];
    private String answer;
    private String topic;
    private String selected;
    private String count;


    public Question() {

    }
    public Question(ArrayList<String> choices) {
        this.question = choices.get(1);
        this.choice[0] = choices.get(2);
        this.choice[1] = choices.get(3);
        this.choice[2] = choices.get(4);
        this.choice[3] = choices.get(5);
        this.choice[4] = choices.get(6);
        this.answer = choices.get(8);
        this.topic = choices.get(9);
    }

    public void randomizeChoices(){
        ArrayList<String> bo = new ArrayList<String>(Arrays.asList(choice));
        Collections.shuffle(bo);
        choice = bo.toArray(new String[bo.size()]);
    }

    public String getQuestion() {
        return question;
    }

    public void setSelection(String g){
        if(g.equals(selected))
            selected="";
        else {
            this.selected = g;

        }
    }

    public String getSelected() {
        return selected;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getChoice(int i) {
        return choice[i];
    }

    public String getAnswer() {
        return answer;
    }

    public String getTopic() {
        return topic;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setChoice(int i, String choice) {
        this.choice[i] = choice;
    }

    public int Correct(){
        Log.d("huh", "Correct: ");
        if(this.getAnswer().equals(this.getSelected())){
            return 1;
        }
        else
            return 0;

    }



    public void setQuestion(String question) {
        this.question = question;
    }


}
