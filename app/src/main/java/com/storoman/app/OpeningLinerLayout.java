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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Map;

public class OpeningLinerLayout extends LinearLayoutChild{


    private View focus = null;
    private EditTextExtended eTE1, eTE2;
    private EditTextExtended[] eTEArray = new EditTextExtended[2];
    private int[] idEdit = new int[2];

    public OpeningLinerLayout(Context context) {
        this(context, null);
    }

    public OpeningLinerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);



        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.opening_layout, this, true);
        eTE1 = (EditTextExtended)getChildAt(1);
        eTE1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                focus = getChildAt(1);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getChildAt(1).setBackgroundResource(R.drawable.rect_text_edir);
                focus = getChildAt(1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        eTE2 = (EditTextExtended) getChildAt(3);
        eTE2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                focus = getChildAt(3);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getChildAt(3).setBackgroundResource(R.drawable.rect_text_edir);
                focus = getChildAt(3);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        eTE1.setId(MainActivity.idNum);MainActivity.idNum++;
        eTE2.setId(MainActivity.idNum);MainActivity.idNum++;

        idEdit[0] =  eTE1.getId();
        idEdit[1] =  eTE2.getId();

        eTEArray[0] = eTE1;
        eTEArray[1] = eTE2;

    }


    public int[] getIdEdit() {
        return idEdit;
    }

    @Override
    public View getFill() {
        for(EditTextExtended ete : eTEArray ){
            if(null == focus){
                focus = ete;
            }
        }
        return focus;
    }

    @Override
    public double getCalclation() {
        double d1 = Double.parseDouble(eTE1.getText().toString());
        double d2 = Double.parseDouble(eTE2.getText().toString());
        return d1 *d2 ;
    }
}
