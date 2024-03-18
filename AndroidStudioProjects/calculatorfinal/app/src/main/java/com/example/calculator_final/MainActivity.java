package com.example.calculator_final;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnEqual, btnAdd, btnMultiply, btnMinus, btnDivide, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDecimal;
    private boolean hasDecimal;
    private TextView textResult, textInput;

    public Calculator calculator = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.textResult);
        textInput = findViewById(R.id.textInput);

        btnDivide = findViewById(R.id.btnDivide);
        btnAdd = findViewById(R.id.btnAdd);
        btnMinus = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDecimal = findViewById(R.id.btnDecimal);
        btnEqual = findViewById(R.id.btnEquals);
        btn0 = findViewById(R.id.btnZero);
        btn1 = findViewById(R.id.btnOne);
        btn2 = findViewById(R.id.btnTwo);
        btn3 = findViewById(R.id.btnThree);
        btn4 = findViewById(R.id.btnFour);
        btn5 = findViewById(R.id.btnFive);
        btn6 = findViewById(R.id.btnSix);
        btn7 = findViewById(R.id.btnSeven);
        btn8 = findViewById(R.id.btnEight);
        btn9 = findViewById(R.id.btnNine);

        btnDivide.setOnClickListener(v -> {
            opOverride("/");
        });

        btnMinus.setOnClickListener(v -> {
            opOverride("-");
        });

        btnAdd.setOnClickListener(v -> {
            opOverride("+");
        });

        btnMultiply.setOnClickListener(v -> {
            opOverride("*");
        });

        btnDecimal.setOnClickListener(v -> {
            String temp =  String.valueOf(textInput.getText());
            String end = temp.substring(temp.length() - 1, temp.length());
            if(!hasDecimal) {
                textInput.append(".");
                hasDecimal = true;
            } else if (hasDecimal && end == ".") {
                temp = temp.substring(0, temp.length() - 1);
                textInput.setText(temp);
                hasDecimal = false;
            }
        });

        btn0.setOnClickListener(v -> {
            adder("0");
        });

        btn1.setOnClickListener(v -> {
            adder("1");
        });

        btn2.setOnClickListener(v -> {
            adder("2");
        });

        btn3.setOnClickListener(v -> {
            adder("3");
        });

        btn4.setOnClickListener(v -> {
            adder("4");
        });

        btn5.setOnClickListener(v -> {
            adder("5");
        });

        btn6.setOnClickListener(v -> {
            adder("6");
        });

        btn7.setOnClickListener(v -> {
            adder("7");
        });

        btn8.setOnClickListener(v -> {
            adder("8");
        });

        btn9.setOnClickListener(v -> {
            adder("9");
        });

        btnEqual.setOnClickListener(v -> {
            String eval = String.valueOf(textInput.getText());
            String end = eval.substring(eval.length() - 1, eval.length());

            if (end.equals("/") || end.equals("+") || end.equals("-") || end.equals("*")) {
                textInput.setText("Invalid operation.");
            }
            else {
                hasDecimal = false;
                textResult.setText(calculator.calculate(eval, true));
                textInput.setText("");
            }
        });
    }

    private void adder(String num) {
        String holder = String.valueOf(textInput.getText());
        if(holder.equals("Invalid operation.")){
            textInput.setText("");
            textInput.append(num);
        }
        else textInput.append(num);
    }

    private void opOverride(String op) {
        String holder =  String.valueOf(textInput.getText());
        StringBuilder sb = new StringBuilder();
        String end;

        if(holder.isEmpty()) {
            return;
        }

        end = holder.substring(holder.length() - 1, holder.length());

        if(end.equals("/") || end.equals("+") || end.equals("*") || end.equals("-")) {
            holder = holder.substring(0, holder.length() - 1);
        } else {
            hasDecimal = false;
        }

        sb.append(holder);
        sb.append(op);
        textInput.setText(sb);
        textResult.setText(calculator.calculate(holder, false));
    }
}