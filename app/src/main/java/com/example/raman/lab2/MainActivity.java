package com.example.raman.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    EditText etInput;
    Button bControl;
    int unknownNumber;
    boolean isEnded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView)findViewById(R.id.textViewInfo);
        etInput = (EditText)findViewById(R.id.editText);
        bControl = (Button)findViewById(R.id.button);
        unknownNumber = (int)(Math.random() * 99) + 1;
        isEnded = false;
    }

    public void onClick(View v) {
        if (!isEnded) {
            int estimatedNumber = Integer.parseInt(etInput.getText().toString());
            if (estimatedNumber == unknownNumber) {
                isEnded = true;
                etInput.setEnabled(false);
                bControl.setText(getResources().getString(R.string.play_more));
                tvInfo.setText(getResources().getString(R.string.hit));
            }
            else if (estimatedNumber < unknownNumber && estimatedNumber > 0) {
                tvInfo.setText(getResources().getString(R.string.behind));
            }
            else if (estimatedNumber > unknownNumber && estimatedNumber <= 100) {
                tvInfo.setText(getResources().getString(R.string.ahead));
            }
            else {
                tvInfo.setText(getResources().getString(R.string.error));
            }
        }
        else {
            isEnded = false;
            unknownNumber = (int)(Math.random() * 99) + 1;
            etInput.setEnabled(true);
            bControl.setText(getResources().getString(R.string.input_value));
        }
    }
}
