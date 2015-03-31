package com.storoman.app;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
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

    private static final String TAG = "TextSpinnerLinerLayout";

    private View focus;
    private int resourse = R.layout.ss;
    private int id;
    private int position;
    private TextView textView;
    private Spinner spinner;
    private String[] spinnerArray = {
            "200",
            "250",
    };


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

        spinner = (Spinner) getChildAt(1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, resourse, spinnerArray);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        position = spinner.getSelectedItemPosition();
                        Log.d(TAG, String.valueOf(position));
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


    public TextSpinnerLinerLayout(Context context, String text, int resourse, String[] spinnerArray) {
        this(context);
        textView.setText(text);
        this.resourse = resourse;
        setAdapter(spinnerArray);
    }


    public int getPosition() {
        return position;
    }

    public View getFocus() {
        return focus;
    }

    public TextView getTextView() {
        return textView;
    }

    public Spinner getSpinner() {
        return spinner;
    }

    public void setAdapter (String[] spinnerArray){
        Log.d(TAG, "setAdapter");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), resourse, spinnerArray);
        getSpinner().setAdapter(adapter);
    }


}
