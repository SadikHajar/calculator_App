package com.example.mycalculator;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<String> results=new ArrayList<String>();
    TextView resultTv,solutionTv ;
    MaterialButton buttonc,buttonbrackOpen,buttonbrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonSus,buttonPlus,buttonEquals;
    MaterialButton buttonZero, buttonone,buttontwo,buttontree,buttonfour,buttonfive,buttonsix,buttonseven,buttonheight,buttonnine;
    MaterialButton buttonac,buttonDot,buttonresults,buttonAns;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_tv);
        solutionTv=findViewById(R.id.solution_tv);

        assignId(buttonAns,R.id.button_Ans);
        assignId(buttonc,R.id.button_c);
        assignId(buttonbrackOpen,R.id.button_open_bracket);
        assignId(buttonbrackClose,R.id.button_close_bracket);
        assignId(buttonDivide,R.id.button_divide);
        assignId( buttonMultiply,R.id.button_multiply);
        assignId( buttonSus,R.id.button_sus);
        assignId( buttonPlus,R.id.button_plus);
        assignId( buttonEquals,R.id.button_equal);
        assignId( buttonac,R.id.button_clear);
        assignId( buttonDot,R.id.button_dote);
        assignId( buttonone,R.id.button_one);
        assignId( buttontwo,R.id.button_two);
        assignId( buttontree,R.id.button_tree);
        assignId( buttonfour,R.id.button_four);
        assignId( buttonfive,R.id.button_five);
        assignId( buttonsix,R.id.button_six);
        assignId( buttonseven,R.id.button_seven);
        assignId( buttonZero,R.id.button_zero);
        assignId( buttonheight,R.id.button_height);
        assignId( buttonnine,R.id.button_nine);
        assignId( buttonresults,R.id.button_tenResult);


    }
    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);


    }
    String getResult(String data){
         try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initSafeStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
             return String.format("%.2f", parseDouble(finalResult));


         }catch (Exception e){
             return "err";
         }


    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText=button.getText().toString();
        String dataTocalculate=solutionTv.getText().toString();
        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if(buttonText.equals("Last Ten Results")){
            oppenResultslist();
            return;

        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            results.add(0,solutionTv.getText().toString());



            return;
        }
        if(buttonText.equals("C")){

            if(dataTocalculate.length()<=1){dataTocalculate="0";}
            else{
           dataTocalculate=dataTocalculate.substring(0,dataTocalculate.length()-1);}


        }
        else if(buttonText.equals("Ans")){
            if(results.size()>0){
            dataTocalculate=dataTocalculate+results.get(0);
            }


        }
        else{
            dataTocalculate=dataTocalculate+buttonText;

        }
        if(dataTocalculate.length()>0){
        solutionTv.setText(dataTocalculate);
        String finalResult=getResult(dataTocalculate);

        if(!finalResult.equals("err")){
            resultTv.setText(finalResult);

        }}




    }

    void oppenResultslist(){
        Intent intent = new Intent(this,listeResults.class);
        intent.putExtra("myResults",results);
        startActivity(intent);

    }
}