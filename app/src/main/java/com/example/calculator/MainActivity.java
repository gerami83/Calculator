package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    String input = "";
    double num1 = 0, num2 = 0;
    String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tvResult);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String text = b.getText().toString();

                if (text.matches("\\d+")) { // مدیریت اعداد
                    input += text;
                    tvResult.setText(input);
                } else if (text.equals("C")) { // دکمه پاک کردن
                    resetCalculator();
                } else if (text.equals("=")) { // دکمه مساوی
                    calculateResult();
                } else { // مدیریت عملگرها
                    if (!input.isEmpty()) {
                        num1 = Double.parseDouble(input);
                        operator = text;
                        input = "";
                    }
                }
            }
        };

        GridLayout grid = findViewById(R.id.gridLayout);
        for (int i = 0; i < grid.getChildCount(); i++) {
            View child = grid.getChildAt(i);
            if (child instanceof Button) {
                child.setOnClickListener(listener);
            }
        }
    }

    private void resetCalculator() {
        input = "";
        num1 = 0;
        num2 = 0;
        operator = "";
        tvResult.setText("0");
    }

    private void calculateResult() {
        if (!input.isEmpty()) {
            try {
                num2 = Double.parseDouble(input);
                double result = 0;

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            tvResult.setText("خطا: تقسیم بر صفر");
                            return;
                        }
                        break;
                    default:
                        tvResult.setText("خطا: عملگر نامعتبر");
                        return;
                }

                tvResult.setText(String.valueOf(result));
                input = "";
                num1 = result;

            } catch (NumberFormatException e) {
                tvResult.setText("خطا: ورودی نامعتبر");
            }
        }
    }
}
