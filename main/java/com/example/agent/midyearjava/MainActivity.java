package com.example.agent.midyearjava;

    import android.content.Context;
    import android.content.Intent;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;

    import java.util.Arrays;
    import java.util.Scanner;
    import java.util.ArrayList;
    import java.io.*;
    import java.io.Serializable;


    import java.io.IOException;


    public class MainActivity extends AppCompatActivity implements Serializable {
        ArrayList<String> bills;
        ArrayList<ArrayList<ArrayList<ArrayList<ArrayList<String>>>>> billy;
        int count = 0;
        private Context context;
        private TextView prop;
        public static ArrayList<ArrayList<String>> printNumbers(ArrayList<String> texty, int l, int txtL){// finds location of topics list with the years
            ArrayList<String> runner = new ArrayList<String>();
            ArrayList<String> year = new ArrayList<String>();
            ArrayList<ArrayList<String>> alloThem= new ArrayList<ArrayList<String>>();
            for(int z =l; z<txtL;z++){
                if (texty.get(z).matches(".*[a-zA-Z]+.*")){
                    break;
                }
                runner.add(texty.get(z)+" ");

            }
            for(int pos =0; pos<runner.size();pos++){

                if(runner.get(pos).contains(":")){
                    year.add(runner.get(pos).substring(0,runner.get(pos).length()-2));
                    ArrayList<String> newYear = new ArrayList<String>();
                    for(int newPos =pos+1; newPos<runner.size();newPos++){
                        if(runner.get(newPos).contains(":"))
                            break;
                        String bp=runner.get(newPos).substring(0,runner.get(newPos).length()-1);
                        if (bp.charAt(bp.length()-1)==',')
                            bp=bp.substring(0,bp.length()-1)+".";
                        else
                            bp=bp+".";
                        newYear.add(bp);
                        if (bp.length()>3)
                            newYear.remove(newYear.size() - 1);

                    }
                    alloThem.add(newYear);
                }

            }
            alloThem.add(0,year);
            alloThem.get(0).add("end");
            return alloThem;
        }

        public static int getStart(ArrayList<String> textq, String whatYear){//Find the point where each year's test starts
            int locate=5;

            for(int beg=0;beg<textq.size()-5;beg++){
                if (textq.get(beg).contains(whatYear)&&textq.get(beg+1).contains("1.")){
                    return beg;}
            }
            if(whatYear.equals("end"))
                return textq.size()-1;


            return 7;
        }


        public static ArrayList<ArrayList<ArrayList<String>>> printNumbersQuestions(ArrayList<ArrayList<String>> index, ArrayList<String> texter){// gets all the questions


            ArrayList<ArrayList<ArrayList<String>>> AllQuestions= new ArrayList<ArrayList<ArrayList<String>>>();
            for (int yearly = 0; yearly<index.get(0).size()-1;yearly++){
                //System.out.println(index.get(0).get(yearly));
                ArrayList<ArrayList<String>> AllyearQuestions= new ArrayList<ArrayList<String>>();
                //System.out.println();
                //System.out.println(getStart(texter,index.get(0).get(yearly)));
                //System.out.println(getStart(texter,index.get(0).get(yearly+1)));
                //System.out.println();
                for (int quest = 0; quest<index.get(yearly+1).size();quest++){//searches each year
                    //System.out.println(quest);
                    for(int qView=getStart(texter,index.get(0).get(yearly));qView<getStart(texter,index.get(0).get(yearly+1))-6;qView++){//searches test of the year
                        if(texter.get(qView).equals(index.get(yearly+1).get(quest))||texter.get(qView).equals(index.get(yearly+1).get(quest)+"AB")){//gets the question
                            String QN = "";
                            QN+=texter.get(qView);
                            qView++;
                            String ChoiceA = "";
                            String ChoiceB = "";
                            String ChoiceC = "";
                            String ChoiceD = "";
                            String ChoiceE = "";
                            String QuestionPart = "";
                            int count =1;
                            while (!texter.get(qView).equals("(A)")){
                                QuestionPart=QuestionPart+" "+texter.get(qView);
                                if(texter.get(qView).contains("{")){
                                    for(int p=0;p<count;p++)
                                        QuestionPart+="\t\n";
                                    count++;}
                                if(texter.get(qView).charAt(texter.get(qView).length()-1)=='}'){
                                    for(int p=0;p<count;p++)
                                        QuestionPart+="\t";
                                    count--;}

                                if(texter.get(qView).contains("?")||texter.get(qView).contains(":")||texter.get(qView).contains("}")){
                                    QuestionPart+="\n";}
                                if (texter.get(qView).contains(";")){
                                    for(int ai =1; ai<=3; ai++){
                                        String ahead = texter.get(qView+ai);
                                        String behind = texter.get(qView-ai);
                                        boolean fine = true;
                                        if(ahead.contains(";")||behind.contains(";")||behind.contains("for")){
                                            fine = false;
                                            break;}
                                        if (fine==true){
                                            QuestionPart+="\n";}
                                    }

                                }
                                qView++;
                            }



                            while (!texter.get(qView).equals("(B)")){
                                ChoiceA+=(texter.get(qView))+" ";
                                qView++;}
                            while (!texter.get(qView).equals("(C)")){
                                ChoiceB+=(texter.get(qView))+" ";
                                qView++;}
                            while (!texter.get(qView).equals("(D)")){
                                ChoiceC+=(texter.get(qView))+" ";
                                qView++;}
                            while (!texter.get(qView).equals("(E)")){
                                ChoiceD+=(texter.get(qView))+" ";
                                qView++;}
                            while ((!texter.get(qView).equals("Questions")||!texter.get(qView).equals("1.")||!texter.get(qView).equals("2.")||!texter.get(qView).equals("3.")||!texter.get(qView).equals("4.")||!texter.get(qView).equals("5.")||!texter.get(qView).equals("6.")||!texter.get(qView).equals("7.")||!texter.get(qView).equals("8.")||!texter.get(qView).equals("9.")||!texter.get(qView).equals("10.")||!texter.get(qView).equals("11.")||!texter.get(qView).equals("12.")||!texter.get(qView).equals("13.")||!texter.get(qView).equals("14.")||!texter.get(qView).equals("15.")||!texter.get(qView).equals("16.")||!texter.get(qView).equals("17.")||!texter.get(qView).equals("18.")||!texter.get(qView).equals("19.")||!texter.get(qView).equals("20.")||!texter.get(qView).equals("21.")||!texter.get(qView).equals("22.")||!texter.get(qView).equals("23.")||!texter.get(qView).equals("24.")||!texter.get(qView).equals("25."))){
                                if (texter.get(qView).equals("2000")||texter.get(qView).equals("1999")||texter.get(qView).equals("2001")||texter.get(qView).equals("2002")||texter.get(qView).equals("2003")||texter.get(qView).equals("2004")||texter.get(qView).equals("2005")||texter.get(qView).equals("2006")||texter.get(qView).equals("2007")||texter.get(qView).equals("2008"))
                                    break;

                                ChoiceE+=(texter.get(qView))+" ";
                                qView++;}
                            String freeE="";
                            if(QN.length()<=3)
                                freeE = (Integer.parseInt(QN.substring(0,QN.length()-1))+1)+".";
                            else
                                freeE = (Integer.parseInt(QN.substring(0,QN.length()-3))+1)+".";
                            if(ChoiceE.length()>20){
                                int where = ChoiceE.indexOf('.');
                                if (where>2&&where<ChoiceE.length()-10)
                                    if (ChoiceE.charAt(where-1)=='0'||ChoiceE.charAt(where-1)=='1'||ChoiceE.charAt(where-1)=='2'||ChoiceE.charAt(where-1)=='3'||ChoiceE.charAt(where-1)=='4'||ChoiceE.charAt(where-1)=='5'||ChoiceE.charAt(where-1)=='6'||ChoiceE.charAt(where-1)=='7'||ChoiceE.charAt(where-1)=='8'||ChoiceE.charAt(where-1)=='9'||(ChoiceE.charAt(where+1)=='A'||ChoiceE.charAt(where+2)=='B'))
                                        ChoiceE=ChoiceE.substring(0,where-1);
                            }



                            ArrayList<String> QuestionWhole = new ArrayList<String>();
                            QuestionWhole.add(QN);
                            QuestionWhole.add(QuestionPart);
                            QuestionWhole.add(ChoiceA.substring(4));
                            QuestionWhole.add(ChoiceB.substring(4));
                            QuestionWhole.add(ChoiceC.substring(4));
                            QuestionWhole.add(ChoiceD.substring(4));
                            QuestionWhole.add(ChoiceE.substring(4));
                            //adds component of each question()missing answer choice E)

                            AllyearQuestions.add(QuestionWhole);//adds each question to the year's questions

                            break;
                        }
                    }
                }
                AllQuestions.add(AllyearQuestions);//adds all the years questions to the topic
            }




            return AllQuestions;
        }



        public static ArrayList<ArrayList<ArrayList<String>>> printNumbersAnswers(ArrayList<ArrayList<String>> index, ArrayList<String> texter){// gets all the questions


            ArrayList<ArrayList<ArrayList<String>>> AllAnswers = new ArrayList<ArrayList<ArrayList<String>>>();
            for (int yearlA = 0; yearlA<index.get(0).size()-1;yearlA++){
                ArrayList<ArrayList<String>> qandA = new ArrayList<ArrayList<String>>();
                //System.out.println();
                //System.out.println(getStartN(texter,index.get(0).get(yearlA)));
                //System.out.println(getStartN(texter,index.get(0).get(yearlA+1)));
                //System.out.println();
                for (int questA = 0; questA<index.get(yearlA+1).size();questA++){//searches each year

                    for(int qView=getStartN(texter,index.get(0).get(yearlA));qView<getStartN(texter,index.get(0).get(yearlA+1));qView++){//searches test of the year
                        if(texter.get(qView).equals(index.get(yearlA+1).get(questA))){//gets the question
                            String QN = "";
                            QN+=texter.get(qView);
                            qView++;
                            String Answer= "";
                            Answer+=texter.get(qView);
                            qView++;
                            String Explanation= "";
                            if(texter.get(qView).equals(index.get(yearlA+1).get(questA))&&texter.get(qView+1).length()>1){//NotWorking
                                while (!texter.get(qView).contains(".")&!texter.get(qView+1).contains(".")){//NotWorking
                                    Explanation+=(texter.get(qView))+" ";//NotWorking
                                    qView++;}//NotWorking
                            }//NotWorking
                            ArrayList<String> AnswerWhole = new ArrayList<String>();
                            AnswerWhole.add(QN);
                            AnswerWhole.add(Answer);
                            AnswerWhole.add(Explanation);

                            qandA.add(AnswerWhole);//adds each question to the year's questions
                            break;
                        }
                    }
                }
                AllAnswers.add(qandA);//adds all the years questions to the topic
            }




            return AllAnswers;
        }

        public static int getStartN(ArrayList<String> textq, String whatYear){//Find the point where each year's test starts
            for(int beg=textq.size()-5;beg>textq.size()/2;beg--){
                if (textq.get(beg).contains(whatYear)&&textq.get(beg+1).contains("Answers"))
                    return beg;
            }
            if(whatYear.equals("end"))
                return textq.size()-1;
            return 7;
        }


        public static ArrayList<ArrayList<ArrayList<ArrayList<String>>>> whatTopic(ArrayList<String> text, String topic){
            ArrayList<ArrayList<ArrayList<ArrayList<String>>>> TopicQandA = new ArrayList<ArrayList<ArrayList<ArrayList<String>>>>();
            String finder="Arithmetic";
            if (topic.equals("Arithmetic"))
                finder="expressions";
            if (topic.equals("Classes"))
                finder="Classes,";
            if (topic.equals("Loops"))
                finder="Loops";
            if (topic.equals("Arrays"))
                finder="Arrays";
            if (topic.equals("ArrayList"))
                finder="ArrayList";
            if (topic.equals("Strings"))
                finder="Strings";
            if (topic.equals("Fields"))
                finder="Fields,";
            if (topic.equals("Recursion"))
                finder="Recursion";
            if (topic.equals("Algorithms"))
                finder="Algorithms";
            if (topic.equals("Searching"))
                finder="Binary";
            if (topic.equals("Sorting"))
                finder="Sorting";
            for(int i =text.size()-(text.size()/10); i<text.size();i++)
                if (text.get(i).equals("A-level")&&text.get(i+1).equals("questions:")){
                    for(int g =i; g<text.size();g++)
                        if(text.get(g).equals(finder)){

                            if(topic.equals("Algorithms")||topic.equals("Arithmetic")||topic.equals("Classes")||topic.equals("Searching"))
                                g+=4;

                            else if(topic.equals("Fields"))
                                g+=5;

                            else if(topic.equals("ArrayList"))
                                g+=3;

                            else if(topic.equals("Searching"))
                                g+=2;

                            else
                                g+=1;


                            //System.out.print(printNumbers(text,g,text.size()));

                            //System.out.println();

                            ArrayList<ArrayList<String>> runnerup =printNumbers(text,g,text.size());


                            ArrayList<ArrayList<ArrayList<String>>> dawaeq = printNumbersQuestions(runnerup,text);
       /*for(int qw =0;qw<dawaeq.size();qw++)
         for(int aw =0;aw<dawaeq.get(qw).size();aw++)
         for(int zw =0;zw<dawaeq.get(qw).get(aw).size();zw++)
         System.out.println(dawaeq.get(qw).get(aw).get(zw));// prints out all the questions
       */
                            ArrayList<ArrayList<ArrayList<String>>> dawae = printNumbersAnswers(runnerup,text);
                            //for(int qw =0;qw<dawae.size();qw++)
                            // for(int aw =0;aw<dawae.get(qw).size();aw++)
                            //for(int zw =0;zw<dawae.get(qw).get(aw).size();zw++)
                            //System.out.println(dawae.get(qw).get(aw).get(zw));// prints out all the Answers

                            TopicQandA.add(dawaeq);
                            TopicQandA.add(dawae);



                        }}
            return TopicQandA;}

        public static ArrayList<ArrayList<ArrayList<ArrayList<ArrayList<String>>>>> AllofThem(ArrayList<String> text){
            ArrayList<ArrayList<ArrayList<ArrayList<ArrayList<String>>>>> allofTem = new ArrayList<ArrayList<ArrayList<ArrayList<ArrayList<String>>>>>();
            String[] toppings = new String[11];
            toppings[0] ="Arithmetic";
            toppings[1] ="Classes";
            toppings[2] ="Loops";
            toppings[3] ="Arrays";
            toppings[4] ="ArrayList";
            toppings[5] ="Strings";
            toppings[6] ="Fields";
            toppings[7] ="Recursion";
            toppings[8] ="Algorithms";
            toppings[9] ="Searching";
            toppings[10] ="Sorting";
            for(int i=0; i<toppings.length;i++)
                allofTem.add(whatTopic(text, toppings[i]));

            for(int a=0;a<allofTem.size();a++)
                for(int b=0;b<allofTem.get(a).size();b++)
                    for(int c=0;c<allofTem.get(a).get(1).size();c++)
                        for(int d=0;d<allofTem.get(a).get(1).get(c).size();d++){
                            for(int e=0;e<allofTem.get(a).get(1).get(c).get(d).size();e++){
                                allofTem.get(a).get(0).get(c).get(d).add(allofTem.get(a).get(1).get(c).get(d).get(1));
                                if (allofTem.get(a).get(0).get(c).get(d).get(7).equals("A"))
                                    allofTem.get(a).get(0).get(c).get(d).add(allofTem.get(a).get(0).get(c).get(d).get(2));
                                else if (allofTem.get(a).get(0).get(c).get(d).get(7).equals("B"))
                                    allofTem.get(a).get(0).get(c).get(d).add(allofTem.get(a).get(0).get(c).get(d).get(3));
                                else if (allofTem.get(a).get(0).get(c).get(d).get(7).equals("C"))
                                    allofTem.get(a).get(0).get(c).get(d).add(allofTem.get(a).get(0).get(c).get(d).get(4));
                                else if (allofTem.get(a).get(0).get(c).get(d).get(7).equals("D"))
                                    allofTem.get(a).get(0).get(c).get(d).add(allofTem.get(a).get(0).get(c).get(d).get(5));
                                else if (allofTem.get(a).get(0).get(c).get(d).get(7).equals("E"))
                                    allofTem.get(a).get(0).get(c).get(d).add(allofTem.get(a).get(0).get(c).get(d).get(6));

                            }
                            if(a==0){
                                allofTem.get(a).get(0).get(c).get(d).add(9,"Arithmetic");}
                            if(a==1){
                                allofTem.get(a).get(0).get(c).get(d).add(9,"Classes");}
                            if(a==2){
                                allofTem.get(a).get(0).get(c).get(d).add(9,"Loops");}
                            if(a==3){
                                allofTem.get(a).get(0).get(c).get(d).add(9,"Arrays");}
                            if(a==4){
                                allofTem.get(a).get(0).get(c).get(d).add(9,"ArrayList");}
                            if(a==5){
                                allofTem.get(a).get(0).get(c).get(d).add(9,"Strings");}
                            if(a==6){
                                allofTem.get(a).get(0).get(c).get(d).add(9,"Fields");}
                            if(a==7){
                                allofTem.get(a).get(0).get(c).get(d).add(9,"Recursion");}
                            if(a==8){
                                allofTem.get(a).get(0).get(c).get(d).add(9,"Algorithms");}
                            if(a==9){
                                allofTem.get(a).get(0).get(c).get(d).add(9,"Searching");}
                            if(a==10){
                                allofTem.get(a).get(0).get(c).get(d).add(9,"Sorting");}


                        }

            return allofTem;
        }







        public ArrayList<String> someMethod() throws IOException {
            String text = "";
            try {


                InputStream is = getAssets().open("op.txt");
                Scanner s = new Scanner(is).useDelimiter("\\A");
                text = s.hasNext() ? s.next() : "";





            } catch (Exception e) {

                throw new RuntimeException(e);
            }
            String[] items = text.split(" ");
            ArrayList<String> itemList = new ArrayList<String>(Arrays.asList(items));
            return itemList;

        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("huh", "find t tho?");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int length=0;

        billy = new ArrayList<ArrayList<ArrayList<ArrayList<ArrayList<String>>>>>();
        prop = (TextView) findViewById(R.id.thisisIt) ;
        Button btn1 = (Button)findViewById(R.id.button);
        Button btn2 = (Button)findViewById(R.id.button2);
        Button btn3 = (Button)findViewById(R.id.button3);
        Button btn4 = (Button)findViewById(R.id.button4);
        Button btn5 = (Button)findViewById(R.id.button5);
        Button btn6 = (Button)findViewById(R.id.button6);
        Button btn7 = (Button)findViewById(R.id.button7);
        Button btn8 = (Button)findViewById(R.id.button8);
        Button btn9 = (Button)findViewById(R.id.button9);


        final ArrayList<ArrayList<ArrayList<ArrayList<ArrayList<String>>>>> finalBilly=new ArrayList<ArrayList<ArrayList<ArrayList<ArrayList<String>>>>>();




        Log.d("huh", "find 1t tho?");
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent Arithmetic = new Intent(MainActivity.this, QuizActivity.class);
                Arithmetic.putExtra("topic", "Arithmetic");
                Arithmetic.putExtra("qData",(Serializable) finalBilly);
                startActivity(Arithmetic);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent Loops = new Intent(MainActivity.this, QuizActivity.class);
                Loops.putExtra("topic", "Classes");
                Loops.putExtra("qdata",(Serializable) finalBilly);
                startActivity(Loops);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent Arrays = new Intent(MainActivity.this, QuizActivity.class);
                Arrays.putExtra("topic", "Arrays");
                Arrays.putExtra("qdata", (Serializable) finalBilly);
                startActivity(Arrays);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent ArrayList = new Intent(MainActivity.this, QuizActivity.class);
                ArrayList.putExtra("topic", "ArrayList");
                ArrayList.putExtra("qdata", (Serializable) finalBilly);
                startActivity(ArrayList);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent Strings = new Intent(MainActivity.this, QuizActivity.class);
                Strings.putExtra("topic", "Strings");
                Strings.putExtra("qdata", (Serializable) finalBilly);
                startActivity(Strings);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent Classes = new Intent(MainActivity.this, QuizActivity.class);
                Classes.putExtra("topic", "Classes");
                Classes.putExtra("qdata", (Serializable) finalBilly);
                startActivity(Classes);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent Fields = new Intent(MainActivity.this, QuizActivity.class);
                Fields.putExtra("topic", "Fields");
                Fields.putExtra("qdata", (Serializable) finalBilly);
                startActivity(Fields);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent Recursion = new Intent(MainActivity.this, QuizActivity.class);
                Recursion.putExtra("topic", "Recursion");
                Recursion.putExtra("qdata", (Serializable) finalBilly);
                startActivity(Recursion);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent Algorithms = new Intent(MainActivity.this, QuizActivity.class);
                Algorithms.putExtra("topic", "Algorithms");
                Algorithms.putExtra("qdata", (Serializable) finalBilly);
                startActivity(Algorithms);
            }
        });




    }






}
