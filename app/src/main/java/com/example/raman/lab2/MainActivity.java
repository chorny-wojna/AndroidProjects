package com.example.raman.lab2;

import android.content.Context;
import android.net.ParseException;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    SeekBar sbInterval;
    TextView tvInfo;
    EditText etInput;
    TextView tvLengthEdit;
    Button bControl;
    int unknownNumber;
    boolean isEnded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView)findViewById(R.id.textViewInfo);
        tvLengthEdit = (TextView) findViewById(R.id.textView2);
        etInput = (EditText)findViewById(R.id.editText);
        bControl = (Button)findViewById(R.id.button);
        sbInterval = (SeekBar)findViewById(R.id.seekBar);
        sbInterval.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                tvLengthEdit.setText(Integer.toString(progress + 10));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {}

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (sbInterval.isEnabled()) {
                    tvInfo.setText(getResources().getString(R.string.try_to_guess) +
                            (sbInterval.getProgress() + 10) + ")");
                }
                sbInterval.setEnabled(false);
            }
        });

        unknownNumber = (int)(Math.random() * ((sbInterval.getProgress() + 10) - 1)) + 1;
        isEnded = false;
    }

    public void onClick(View v) {
        tvInfo.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorText));
        if (!isEnded) {
            if (etInput.getText().toString().length() == 0) {
                tvInfo.setText(getResources().getString(R.string.parse_error));
                tvInfo.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorError));
            }
            else {
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
                else if (estimatedNumber > unknownNumber &&
                        estimatedNumber <= (sbInterval.getProgress() + 10)) {
                    tvInfo.setText(getResources().getString(R.string.ahead));
                }
                else {
                    tvInfo.setText(getResources().getString(R.string.bounds_error) +
                            (sbInterval.getProgress() + 10));
                    tvInfo.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorError));
                }
            }
        }
        else {
            isEnded = false;
            unknownNumber = (int)(Math.random() * (sbInterval.getProgress() + 10)) + 1;
            etInput.setEnabled(true);
            bControl.setText(getResources().getString(R.string.input_value));
            tvInfo.setText(getResources().getString(R.string.try_to_guess) + (sbInterval.getProgress() + 10) + ")");
            sbInterval.setEnabled(true);
        }
        etInput.setText("");
    }

    public void onExitClick(View v) {
        finish();
        System.exit(0);
    }
}
