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


public class TextButtonLinerLayout extends LinearLayout{

    private View focus = null;
    private int id;
    private TextView textView;
    private Button button;

    public TextButtonLinerLayout(Context context) {
        this(context, null);
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.text_button_layout, this, true);

        textView = (TextView) getChildAt(0);
        String text = "text";
        textView.setText(text);

        button = (Button) getChildAt(1);

    }

    public TextButtonLinerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);


    }

    public TextButtonLinerLayout(Context context, String text, String ButtonText) {
        this(context);
        textView.setText(text);

    }



    public View getFocus() {
        return focus;
    }
}
