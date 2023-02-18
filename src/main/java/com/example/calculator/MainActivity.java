package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView txtSolution, txtResult;
    MaterialButton btnDEL, btnAC, btnDot, imgButton;
    MaterialButton btnPlus, btnSub, btnMul, btnDivision, btnResult;
    MaterialButton btn0, btn1, btn2, btn3 , btn4, btn5, btn6, btn7, btn8, btn9;
    String historyCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSolution = findViewById(R.id.textviewHistory);
        txtResult = findViewById(R.id.textviewResult);
        assignId(btnAC,R.id.btnAC);
        assignId(btnDEL,R.id.btnDEL);
        assignId(btnDot,R.id.btnCham);
        assignId(btnPlus,R.id.btnCong);
        assignId(btnSub,R.id.btnTru);
        assignId(btnMul,R.id.btnNhan);
        assignId(btnDivision,R.id.btnChia);
        assignId(btnResult,R.id.btnBang);
        assignId(btn0,R.id.nb0);
        assignId(btn1,R.id.nb1);
        assignId(btn2,R.id.nb2);
        assignId(btn3,R.id.nb3);
        assignId(btn4,R.id.nb4);
        assignId(btn5,R.id.nb5);
        assignId(btn6,R.id.nb6);
        assignId(btn7,R.id.nb7);
        assignId(btn8,R.id.nb8);
        assignId(btn9,R.id.nb9);
        assignId(imgButton,R.id.btn_history);

    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCalculate = txtSolution.getText().toString();
        if(buttonText.equals("AC")) {
            txtSolution.setText("");
            txtResult.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            txtSolution.setText("");
            historyCalc += dataCalculate.toString()+" = "+txtResult.getText().toString()+"/";
            return;
        }
        if(buttonText.equals("HISTORY")){
            txtSolution.setText("");
            Intent intent = new Intent(getApplicationContext(),Historyactivity.class);
            intent.putExtra("HISTORY",historyCalc);
            startActivity(intent);
            return;
        }
        if(buttonText.equals("DEL")){
            dataCalculate = dataCalculate.substring(0, dataCalculate.length() - 1);
        }else{
            dataCalculate = dataCalculate+buttonText;
        }

        txtSolution.setText(dataCalculate);
        String finalResult = getResult(dataCalculate);
        if(!finalResult.equals("Err")){
            txtResult.setText(finalResult);
        }
    }
    String getResult(String data)
    {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0"))
            {
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e)
        {  return "Err";}

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        txtResult = findViewById(R.id.textviewResult);
        txtSolution = findViewById(R.id.textviewHistory);
        outState.putString("re", txtResult.getText().toString());
        outState.putString("so", txtSolution.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        txtResult = findViewById(R.id.textviewResult);
        txtSolution = findViewById(R.id.textviewHistory);
        txtResult.setText(savedInstanceState.getString("re"));
        txtSolution.setText(savedInstanceState.getString("so"));
    }}
