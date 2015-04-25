package com.storoman.app;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class LevelLayout extends LinearLayout{

    private static final String TAG = "LevelLayout";

    ArrayList<int[]> openingFirstEdittextId = new ArrayList<>();
    ArrayList<int[]> openingSecondEdittextId = new ArrayList<>();
    ArrayList<int[]> openingTherdEdittextId = new ArrayList<>();

    public LevelLayout(Context context) {
        super(context);


        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER_VERTICAL);

        addView(new TextViewButtonLinerLayout(getContext(), "Тип Этажа " + MainActivity.levelIndex, "X"));
        MainActivity.levelIndex++;

        ((TextViewButtonLinerLayout) getChildAt(0)).getTextView().setGravity(Gravity.CENTER);
        ((TextViewButtonLinerLayout) getChildAt(0)).getButton().setLayoutParams(
                new LinearLayout.LayoutParams(
                (int)  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75,
                                                 getResources().getDisplayMetrics()), ViewGroup.LayoutParams.WRAP_CONTENT));

        ((TextViewButtonLinerLayout) getChildAt(0)).getButton().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LinearLayout) getChildAt(0).getParent()).setVisibility(GONE);
                MainActivity.levelIndex--;
            }
        });


        addView(new TextViewEditTextLinerLayout(getContext(), "Количество этажей данного типа", "1", "x", InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, 1));
        addView(new TextViewEditTextLinerLayout(getContext(), "Высота от пола до потолка, м", null, "xx.x", InputType.TYPE_CLASS_NUMBER, 4));
        addView(new TextViewEditTextLinerLayout(getContext(), "Длина наружных несущих стен, м", null, "xxxx.x", InputType.TYPE_CLASS_NUMBER, 6));
        addView(new TextViewSpinnerLinerLayout(getContext(), "Толщина наружной несущей стены, мм", R.layout.ss, getResources().getStringArray(R.array.wallLength)));
        addView(new TextViewEditTextLinerLayout(getContext(), "Длина внутренних несущих стен, м", null, "xxxx.x", InputType.TYPE_CLASS_NUMBER, 6));
        addView(new TextViewSpinnerLinerLayout(getContext(), "Толщина внутренней несущей стены, мм", R.layout.ss, getResources().getStringArray(R.array.wallLength)));
        addView(new TextViewEditTextLinerLayout(getContext(), "Длина перегородок, м", null, "xxxx.x", InputType.TYPE_CLASS_NUMBER, 6));
        addView(new TextViewSpinnerLinerLayout(getContext(), "Толщина перегородки, мм", R.layout.ss,  getResources().getStringArray(R.array.bulkLength)));
        addView(new TextViewSpinnerLinerLayout(getContext(), "U-блок для армопояса", R.layout.ss, getResources().getStringArray(R.array.noYes)));
        ((TextViewSpinnerLinerLayout)getChildAt(9)).getSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, String.valueOf(position));
                if(position == 0){
                    ((TextViewSpinnerLinerLayout) getChildAt(4)).setAdapter(getResources().getStringArray(R.array.wallLength));
                    ((TextViewSpinnerLinerLayout) getChildAt(6)).setAdapter(getResources().getStringArray(R.array.wallLength));
                }
                if(position == 1){
                    ((TextViewSpinnerLinerLayout) getChildAt(4)).setAdapter(getResources().getStringArray(R.array.shortWallLength));
                    ((TextViewSpinnerLinerLayout) getChildAt(6)).setAdapter(getResources().getStringArray(R.array.shortWallLength));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//
        //10
        addView(new TextViewSpinnerLinerLayout(context, "Наличие перекрытия", R.layout.ss, getResources().getStringArray(R.array.noYes)));
        addView(new TextViewSpinnerLinerLayout(context, "Толщина блоков в уровне перекрытия", R.layout.ss, getResources().getStringArray(R.array.blockLength)));
        ((TextViewSpinnerLinerLayout)getChildAt(10)).getSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) {
                getChildAt(11).setVisibility(View.GONE);

            }
            if (position == 1) {
                getChildAt(11).setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });

        //12
        addView(new TextViewButtonLinerLayout(context, "Проёмы в наружных стенах","Добавить проем"));
        addView(new LinearLayout(context));
        ((TextViewButtonLinerLayout)getChildAt(12)).getButton().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeningLinerLayout oLL = new OpeningLinerLayout(getContext());
                ((LinearLayout)getChildAt(13)).addView(oLL);
                openingFirstEdittextId.add(oLL.getIdEdit());
            }
        });

        //14
        addView(new TextViewButtonLinerLayout(getContext(), "Проёмы во внутренних несущих стенах", "Добавить проем"));
        addView(new LinearLayout(getContext()));
        ((TextViewButtonLinerLayout)getChildAt(14)).getButton().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeningLinerLayout oLL = new OpeningLinerLayout(getContext());
                ((LinearLayout)getChildAt(15)).addView(oLL);
                openingSecondEdittextId.add(oLL.getIdEdit());
            }
        });

         // 16
        addView(new TextViewButtonLinerLayout(getContext(), "Проёмы в перегородках", "Добавить проем"));
        addView(new LinearLayout(getContext()));
        ((TextViewButtonLinerLayout)getChildAt(16)).getButton().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeningLinerLayout oLL = new OpeningLinerLayout(getContext());
                ((LinearLayout)getChildAt(17)).addView(oLL);
                openingTherdEdittextId.add(oLL.getIdEdit());
            }
        });

        //18
        addView(new LinearLayout(getContext()));
        getChildAt(18).setLayoutParams( new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                (int)  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3,  getResources().getDisplayMetrics()) ));
        getChildAt(18).setBackgroundColor(0xffcf0009);



    }




}
