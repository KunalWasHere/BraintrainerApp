package com.kunal.braintrainer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timer,score,finals,result,equation;
    Button playAgain,start;
    GridView grid;
    boolean isactive;
    int t1=0,t2=0;
    MediaPlayer mp;

    public void ran(){
        if(isactive){
        //finals.setVisibility(View.INVISIBLE);
        int a1,a2,c2,c3,c4;
        final int c1;
        Random r=new Random();
        a1=r.nextInt(49);
        a2=r.nextInt(49);
        equation.setText(Integer.toString(a1)+"+"+Integer.toString(a2));
        c1=a1+a2;
        c2=r.nextInt(20)+c1-10;
        c3=r.nextInt(20)+c1-10;
        c4=r.nextInt(20)+c1-10;
        equation.setText(Integer.toString(a1)+"+"+Integer.toString(a2));
        final ArrayList<String> list=new ArrayList<>();
        list.add(Integer.toString(c1));
        list.add(Integer.toString(c2));
        list.add(Integer.toString(c3));
        list.add(Integer.toString(c4));
            Collections.shuffle(list);
        ArrayAdapter<String> arrayadapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        grid.setAdapter(arrayadapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(Integer.parseInt(list.get(position))==c1) {
                    finals.setText(("CORRECT"));
                    if(isactive)
                        t1++;
                }
                else
                    finals.setText(("WRONG"));
                finals.setVisibility(View.VISIBLE);
                if(isactive)
                    t2++;
                score.setText(Integer.toString(t1)+"/"+Integer.toString(t2));
                ran();
            }
        });

    }}

    public void update(int a){
        String as;
        as=Integer.toString(a);
        if(a<=9)
            as="0"+as;
        timer.setText(as);
    }

    public void go(View view){
        mp.pause();
        t1=0;
        t2=0;
        score.setText("0/0");
        isactive=true;
        start.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        finals.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);
        grid.setVisibility(View.VISIBLE);
        equation.setVisibility(View.VISIBLE);
        /*int a1,a2;
        Random r=new Random();
        a1=r.nextInt(49);
        a2=r.nextInt(49);*/
        ran();
        //equation.setText(Integer.toString(a1)+"+"+Integer.toString(a2));
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                update((int)millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timer.setText("00");
                playAgain.setVisibility(View.VISIBLE);
                result.setText(Integer.toString(t1)+"/"+Integer.toString(t2));
                result.setVisibility(View.VISIBLE);
                isactive=false;
                mp.start();
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer=(TextView)findViewById(R.id.timer);
        score=(TextView)findViewById(R.id.score);
        finals=(TextView)findViewById(R.id.finalt);
        result=(TextView)findViewById(R.id.result);
        playAgain=(Button)findViewById(R.id.playagain);
        start=(Button)findViewById(R.id.start);
        grid=(GridView)findViewById(R.id.gridView);
        equation=(TextView)findViewById(R.id.equation);
        mp=MediaPlayer.create(getApplicationContext(),R.raw.air);
    }
}
