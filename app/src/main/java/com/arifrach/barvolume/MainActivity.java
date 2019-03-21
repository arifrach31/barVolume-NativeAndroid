package com.arifrach.barvolume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtWidth, edtHeight, edtLength;
    Button btnCalculate;
    TextView result;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.btn_calculate);
        result = findViewById(R.id.result);

        btnCalculate.setOnClickListener(this);

        if(savedInstanceState != null) {
            String results = savedInstanceState.getString(STATE_RESULT);
            result.setText(results);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, result.getText().toString());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_calculate) {
            String inputLength = edtLength.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();
            String inputHeight = edtHeight.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if(TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true;
                edtLength.setError("Fields Length Tidak Boleh Kosong");
            }

            if(TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true;
                edtWidth.setError("Fields Width Tidak Boleh Kosong");
            }

            if(TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true;
                edtHeight.setError("Fields Height Tidak Boleh Kosong");
            }

            Double length = toDouble(inputLength);
            Double width = toDouble(inputWidth);
            Double height = toDouble(inputHeight);

            if(length == null) {
                isInvalidDouble = true;
                edtLength.setError("Filed ini harus berupa nomor yang valid");
            }

            if(width == null) {
                isInvalidDouble = true;
                edtWidth.setError("Filed ini harus berupa nomor yang valid");
            }

            if(height == null) {
                isInvalidDouble = true;
                edtHeight.setError("Filed ini harus berupa nomor yang valid");
            }

            if(!isEmptyFields && !isInvalidDouble) {
                double volume = length * width * height;

                result.setText(String.valueOf(volume));
            }


        }
    }

    Double toDouble(String str) {
        try{
            return Double.valueOf(str);
        } catch(NumberFormatException e) {
            return null;
        }
    }
}
