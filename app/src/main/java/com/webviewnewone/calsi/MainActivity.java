package com.webviewnewone.calsi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button[] numericButtons = new Button[10];
    Button btnDot, btnEqual, btnAdd, btnSub, btnMul, btnDiv;
    TextView ans;
    double var1, var2;
    boolean add, sub, mul, div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeButtons();
        setNumericButtonClickListeners();
        setOperationButtonClickListeners();
    }

    private void initializeButtons() {
        ans = findViewById(R.id.Answer);

        for (int i = 0; i < 10; i++) {
            String buttonID = "btn" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            numericButtons[i] = findViewById(resID);
        }

        btnDot = findViewById(R.id.btnpnt);
        btnEqual = findViewById(R.id.btnequal);
        btnAdd = findViewById(R.id.btnadd);
        btnSub = findViewById(R.id.btnsub);
        btnMul = findViewById(R.id.btnmul);
        btnDiv = findViewById(R.id.btndiv);
    }

    private void setNumericButtonClickListeners() {
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            numericButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ans.append(String.valueOf(finalI));
                }
            });
        }

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ans.getText().toString().contains(".")) {
                    ans.append(".");
                }
            }
        });
    }

    private void setOperationButtonClickListeners() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("+");
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("-");
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("*");
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation("/");
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    private void performOperation(String operator) {
        var1 = Double.parseDouble(ans.getText().toString());
        clearTextView();
        switch (operator) {
            case "+":
                add = true;
                break;
            case "-":
                sub = true;
                break;
            case "*":
                mul = true;
                break;
            case "/":
                div = true;
                break;
        }
    }

    private void calculateResult() {
        var2 = Double.parseDouble(ans.getText().toString());
        if (add) {
            ans.setText(String.valueOf(var1 + var2));
            add = false;
        } else if (sub) {
            ans.setText(String.valueOf(var1 - var2));
            sub = false;
        } else if (mul) {
            ans.setText(String.valueOf(var1 * var2));
            mul = false;
        } else if (div) {
            if (var2 != 0) {
                ans.setText(String.valueOf(var1 / var2));
            } else {
                ans.setText("Error");
            }
            div = false;
        }
    }

    private void clearTextView() {
        ans.setText("");
    }
}
