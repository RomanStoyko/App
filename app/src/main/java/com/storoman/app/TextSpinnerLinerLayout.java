package com.storoman.app;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


public class TextSpinnerLinerLayout extends LinearLayout{

    private View focus;
    private int id;
    private TextView textView;
    private Spinner spinner;
    private String[] spinnerArray = {
            "200",
            "250",
            "300",
            "350",
            "375",
            "400",
            "500"};


    public TextSpinnerLinerLayout(Context context) {
        this(context, null);
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, (int)  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics())) );

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.text_spinner_layout, this, true);

        textView = (TextView) getChildAt(0);
        String text = "text";
        textView.setText(text);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>();
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                }
        );



        spinner.setId(MainActivity.idNum);
        MainActivity.idNum++;





    }

    public TextSpinnerLinerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);


    }


    public TextSpinnerLinerLayout(Context context, String text, String editText, String hint, int inputType, int length) {
        this(context);
        textView.setText(text);

    }


}
