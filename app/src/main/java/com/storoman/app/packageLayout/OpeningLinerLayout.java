package com.storoman.app.packageLayout;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.storoman.app.R;

public class OpeningLinerLayout extends LinearLayoutChild {



    private View focus = null;
    private EditTextExtended eTE1, eTE2;
    private EditTextExtended[] eTEArray = new EditTextExtended[2];

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
//        eTE1.addTextChangedListener(TWlistner);

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
//        eTE2.addTextChangedListener(TWlistner);

        eTEArray[0] = eTE1;
        eTEArray[1] = eTE2;
    }

    @Override
    public View getFill() {
        focus = null;

        for(EditTextExtended ete : eTEArray ){
            if (ete.getText().toString().equals("")){
                ete.setBackgroundResource(R.drawable.rect_text_edir_error);
                if(null == focus){
                    focus = ete;
                }
            }
        }

        if(eTE1.getText().toString().equals("") &&eTE2.getText().toString().equals("")){
            setVisibility(GONE);
            return null;
        }

        return focus;
    }

    @Override
    public double getCalclation() {
        try {
            double d1 = Double.parseDouble(eTE1.getText().toString());
            double d2 = Double.parseDouble(eTE2.getText().toString());
            return d1 *d2 ;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    @Override
    public double getCalcWidth() {
        try {
            return Double.parseDouble(eTE2.getText().toString())+ 0.5 ;
        } catch (NumberFormatException e) {
            return 0;

        }

    }


//    TextWatcherExtendedListener TWlistner = new TextWatcherExtendedListener() {
//
//
//        @Override
//        public void afterTextChanged(View v, Editable s) {
//
//            v.setBackgroundResource(R.drawable.rect_text_edir);
//
//        }
//
//        @Override
//        public void onTextChanged(View v, CharSequence s, int start, int before, int count) {
//            v.setBackgroundResource(R.drawable.rect_text_edir);
//
//        }
//
//        @Override
//        public void beforeTextChanged(View v, CharSequence s, int start, int count, int after) {
//
//        }
//    };

}
