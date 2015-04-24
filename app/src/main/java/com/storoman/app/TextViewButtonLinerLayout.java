package com.storoman.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class TextViewButtonLinerLayout extends LinearLayoutChild{

    private View focus = null;
    private int id;
    private TextView textView;
    private Button button;

    public TextViewButtonLinerLayout(Context context) {
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

    public TextViewButtonLinerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);


    }

    public TextViewButtonLinerLayout(Context context, String text, String buttonText) {
        this(context);
        textView.setText(text);
        button.setText(buttonText);
    }




    public View getFocus() {
        return focus;
    }

    public TextView getTextView() {
        return textView;
    }

    public Button getButton() {
        return button;
    }

//    @Override
//    public View getFill() {
//        if(textView.getText().toString().equals("")){
//            textView.setBackgroundResource(R.drawable.rect_text_edir_error);
//            return textView;
//        }else{
//            return null;
//        }
//
//    }


    @Override
    public View getFill() {
        return null;
    }

    @Override
    public double getCalclation() {
        return 1.0;
    }
}
