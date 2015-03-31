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
import android.widget.LinearLayout;
import android.widget.TextView;


public class TextEditTextLinerLayout extends LinearLayout{

    private View focus = null;
    private int id;
    private TextView textView;
    private EditTextExtended editTextExtended;

    public TextEditTextLinerLayout(Context context) {
        this(context, null);
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, (int)  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics())) );

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.text_editext_layout, this, true);

        textView = (TextView) getChildAt(0);
        String text = "text";
        textView.setText(text);

        InputFilter[] fArray = new InputFilter[1];
        int length = 2;
        fArray[0] = new InputFilter.LengthFilter(length);

        editTextExtended = (EditTextExtended) getChildAt(1);

        String hint = "x.x";
        editTextExtended.setHint(hint);
        editTextExtended.setBackgroundResource(R.drawable.rect_text_edir);
        int inputType = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL;
        editTextExtended.setInputType(inputType);
        editTextExtended.setFilters(fArray);
        editTextExtended.addTextChangedListener(new TextWatcher() {
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


        editTextExtended.setId(MainActivity.idNum);
        MainActivity.idNum++;


    }

    public TextEditTextLinerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);


    }


    public TextEditTextLinerLayout(Context context, String text, String editText,String hint, int inputType, int length) {
        this(context);
        textView.setText(text);
        editTextExtended.setHint(hint);
        editTextExtended.setText(editText);
        editTextExtended.setInputType(inputType);
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(length);
        editTextExtended.setFilters(fArray);

    }



    public TextView getTextView() {
        return textView;
    }


    public EditTextExtended getEditTextExtended() {
        return editTextExtended;
    }

    public View getFocus() {
        return focus;
    }
}
