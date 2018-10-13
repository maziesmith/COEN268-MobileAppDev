package com.rohan.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    TextView txt;
    TextView out;
    String show = "";
    String output = "";
    String prevOperation = "";
    boolean hasDecimal = false;
    boolean err = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        txt = (TextView) findViewById(R.id.txtDisplay);
        out = (TextView) findViewById(R.id.txtOutput);
        ScreenDisplay();
    }

    public void ScreenDisplay() {
        txt.setText(show);
    }

    public void OutputDisplay() {
        if(err)
            out.setText("Bad Expression");
        out.setText(output);
    }

    public void onClickOperator(View view) {
        hasDecimal = false;
        if(show == "")
            return;
        if(output != "") {
            String temp = output;
            clear();
            show = temp;
        }

        Button button = (Button) view;
        try {
            if (prevOperation != "") {
                if (checkOperator(show.charAt(show.length() - 1))) {
                    show = show.replace(show.charAt(show.length() - 1), button.getText().charAt(0));
                    prevOperation = button.getText().toString();
                    ScreenDisplay();
                    return;
                } else {
                    findOutput();
                    show = output;
                    ScreenDisplay();
                    output = "";
                }
            }
        }
        catch (Exception e) {
            Log.d("Calc", e.getMessage());
        }
        OutputDisplay();
        show += button.getText();
        prevOperation = button.getText().toString();
        ScreenDisplay();
    }

    public boolean checkOperator(char ch) {
        if(ch == '+' || ch == '-' || ch == '×' || ch == '÷')
            return true;
        return false;
    }

    public void onClickNumber(View view) {
        if(output != "") {
            clear();
            ScreenDisplay();
        }
        OutputDisplay();
        Button button = (Button) view;
        show += button.getText();
        ScreenDisplay();
    }

    public void onClickDot (View view) {
        if(hasDecimal)
            return;
        Button button = (Button) view;
        show += button.getText();
        hasDecimal = true;
        ScreenDisplay();
    }

    public void onClickEqual(View view) {
        if(show == "")
            return;

        if (show.charAt(show.length() - 1) == '.') {
            output = "Bad Expression!";
            OutputDisplay();
            clear();
            ScreenDisplay();
        }

        if(!findOutput())
            return;
        else {
            out.setText(output);
            if(output == "Can't divide by 0!")
                clear();
        }
    }

    public void onClickDel(View view) {
        clear();
        ScreenDisplay();
        OutputDisplay();
    }

    public boolean findOutput() {
        String[] equation = show.split("\\+|\\-|\\×|\\÷");
        if(equation.length < 2)
            return false;
        Double tempOutput = (calculate(equation[0], equation[1], prevOperation));
        if(Double.isInfinite(tempOutput)) {
            output = "Can't divide by 0!";
        }
        else if ((tempOutput == Math.floor(tempOutput))) {
            int tempIntValue = (int) Math.round(tempOutput);
            output = Integer.toString(tempIntValue);
        }
        else
            output = Double.toString(tempOutput);
        return true;
    }

    public double calculate(String first, String second, String operator) {
        switch (operator) {
            case "+":
                return Double.parseDouble(first) + Double.parseDouble(second);
            case "-":
                return Double.parseDouble(first) - Double.parseDouble(second);
            case "×":
                return Double.parseDouble(first) * Double.parseDouble(second);
            case "÷":
                try {
                    return Double.parseDouble(first) / Double.parseDouble(second);
                } catch (Exception e) {
                    output = "Bad Expression!";
                    OutputDisplay();
                    clear();
                }
            default:
                return -1;
        }
    }

    public void clear() {
        show = "";
        output = "";
        prevOperation = "";
    }

}
