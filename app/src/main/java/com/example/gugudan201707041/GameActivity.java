package com.example.gugudan201707041;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private static final int MENU_EXIT=1;

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btncencel,btnenter;
    ProgressBar pb;
    int corcnt=0;
    int result=0;
    TextView txtquestion,txtanswer,correct;
    Random random= new Random();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        btn0=(Button)findViewById(R.id.button0);
        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);
        btn3=(Button)findViewById(R.id.button3);
        btn4=(Button)findViewById(R.id.button4);
        btn5=(Button)findViewById(R.id.button5);
        btn6=(Button)findViewById(R.id.button6);
        btn7=(Button)findViewById(R.id.button7);
        btn8=(Button)findViewById(R.id.button8);
        btn9=(Button)findViewById(R.id.button9);
        btncencel=(Button)findViewById(R.id.buttoncencel);
        btnenter=(Button)findViewById(R.id.buttonenter);
        txtquestion=(TextView)findViewById(R.id.txtquestion);
        txtanswer=(TextView)findViewById(R.id.txtanswer);
        correct=(TextView)findViewById(R.id.txtcorrect);
        pb=(ProgressBar)findViewById(R.id.progressBar2);


        int f=random.nextInt(9)+1;
        int s=random.nextInt(9)+1;
        result=f*s;
        txtquestion.setText(f+" X "+s);


        pb.setMax(60);
        pb.setProgress(0);

        PgThread th= new PgThread();
        th.start();



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,MENU_EXIT,Menu.NONE,"EXIT");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Mainback();
        return super.onOptionsItemSelected(item);
    }

    public void click(View v){
        if(btn0.equals(v)){
            txtanswer.append("0");
        }else if(btn1.equals(v)){
            txtanswer.append("1");
        }else if(btn2.equals(v)){
            txtanswer.append("2");
        }else if(btn3.equals(v)){
            txtanswer.append("3");
        }else if(btn4.equals(v)){
            txtanswer.append("4");
        }else if(btn5.equals(v)){
            txtanswer.append("5");
        }else if(btn6.equals(v)){
            txtanswer.append("6");
        }else if(btn7.equals(v)){
            txtanswer.append("7");
        }else if(btn8.equals(v)){
            txtanswer.append("8");
        }else if(btn9.equals(v)){
            txtanswer.append("9");
        }else if(btncencel.equals(v)){
            txtanswer.setText("");
        }else if(btnenter.equals(v)){
            if(txtanswer.getText()!="") {
                String txtan = txtanswer.getText().toString();
                int txta = Integer.parseInt(txtan);
                if(txta==result) {
                    corcnt++;
                    correct.setText(Integer.toString(corcnt));

                    int first = random.nextInt(9) + 1;
                    int second = random.nextInt(9) + 1;
                    result = first * second;
                    txtquestion.setText(first + " X " + second);
                    txtanswer.setText("");
                }
                else{
                    txtanswer.setText("");
                }
            }
        }
    }

    class PgThread extends Thread{

                @Override
                public void run() {
                    while(true) {
                        if(pb.getProgress()>=60)
                            Mainback();
                        else
                            pb.incrementProgressBy(1);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };



    public void Mainback(){
        Intent back=new Intent();
        String sendback=correct.getText().toString();
        back.putExtra("point",sendback);
        setResult(RESULT_OK,back);
        finish();

    }

}