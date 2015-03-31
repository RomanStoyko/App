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

public class OpeningLinerLayout extends LinearLayout{


    private View focus;
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
        ((EditTextExtended) getChildAt(1)).addTextChangedListener(new TextWatcher() {
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

        ((EditTextExtended) getChildAt(3)).addTextChangedListener(new TextWatcher() {
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

        getChildAt(1).setId(MainActivity.idNum);MainActivity.idNum++;

        getChildAt(3).setId(MainActivity.idNum);MainActivity.idNum++;

        idEdit[0] =  getChildAt(1).getId();
        idEdit[1] =  getChildAt(3).getId();
    }


    public int[] getIdEdit() {
        return idEdit;
    }
}
