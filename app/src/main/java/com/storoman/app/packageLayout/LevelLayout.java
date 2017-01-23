package com.storoman.app.packageLayout;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import com.storoman.app.MainActivity;
import com.storoman.app.R;
import com.storoman.app.textViewPackage.TextViewButtonLinerLayout;
import com.storoman.app.textViewPackage.TextViewEditTextLinerLayout;
import com.storoman.app.textViewPackage.TextViewSpinnerLinerLayout;

import java.util.ArrayList;


public class LevelLayout extends LinearLayoutChild {

    private View focus = null;
    private int position8, position9;
    private static final String TAG = "LevelLayout";

    ArrayList<? super LinearLayoutChild> child = new ArrayList<>();





    public LevelLayout(Context context) {
        super(context);


        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER_VERTICAL);
//0
        addView(new TextViewButtonLinerLayout(getContext(), "Тип Этажа " , "X"));

        ((TextViewButtonLinerLayout) getChildAt(0)).getTextView().setGravity(Gravity.CENTER);
        ((TextViewButtonLinerLayout) getChildAt(0)).getButton().setLayoutParams(
                new LinearLayout.LayoutParams(
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75,
                                getResources().getDisplayMetrics()), ViewGroup.LayoutParams.WRAP_CONTENT));

        ((TextViewButtonLinerLayout) getChildAt(0)).getButton().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               ((LinearLayout) getChildAt(0).getParent()).setVisibility(GONE);
                MainActivity.levelRemove();
            }
        });

//1
        addView(new TextViewEditTextLinerLayout(getContext(), "Количество этажей данного типа", "1", "x", InputType.TYPE_CLASS_NUMBER , 1));
        //2
        addView(new TextViewEditTextLinerLayout(getContext(), "Высота от пола до потолка, м", null, "xx.x", InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, 4));
//3
        addView(new TextViewEditTextLinerLayout(getContext(), "Длина наружных несущих стен, м", null, "xxxx.x", InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, 6));
//4
        addView(new TextViewSpinnerLinerLayout(getContext(), "Толщина наружной несущей стены, мм", R.layout.ss, getResources().getStringArray(R.array.wallLength)));
        //5
        addView(new TextViewEditTextLinerLayout(getContext(), "Длина внутренних несущих стен, м", null, "xxxx.x", InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, 6));
   //6
        addView(new TextViewSpinnerLinerLayout(getContext(), "Толщина внутренней несущей стены, мм", R.layout.ss, getResources().getStringArray(R.array.wallLength)));
     //7
        addView(new TextViewEditTextLinerLayout(getContext(), "Длина перегородок, м", null, "xxxx.x", InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, 6));
     //8
        addView(new TextViewSpinnerLinerLayout(getContext(), "Толщина перегородки, мм", R.layout.ss, getResources().getStringArray(R.array.bulkLength)));
     //9
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
                position8 = position;
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
                position9 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //12
        addView(new TextViewButtonLinerLayout(context, "Проёмы в наружных стенах","Добавить проем"));
        addView(new CompactLinearLayout(context));
        ((LinearLayout) getChildAt(13)).setOrientation(VERTICAL);
        ((TextViewButtonLinerLayout)getChildAt(12)).getButton().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeningLinerLayout oLL = new OpeningLinerLayout(getContext());
                ((LinearLayout)getChildAt(13)).addView(oLL);

            }
        });

        //14
        addView(new TextViewButtonLinerLayout(getContext(), "Проёмы во внутренних несущих стенах", "Добавить проем"));
        addView(new CompactLinearLayout(getContext()));
        ((LinearLayout) getChildAt(15)).setOrientation(VERTICAL);
        ((TextViewButtonLinerLayout)getChildAt(14)).getButton().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeningLinerLayout oLL = new OpeningLinerLayout(getContext());
                ((LinearLayout)getChildAt(15)).addView(oLL);

            }
        });

         // 16
        addView(new TextViewButtonLinerLayout(getContext(), "Проёмы в перегородках", "Добавить проем"));
        addView(new CompactLinearLayout(getContext()));
        ((LinearLayout) getChildAt(17)).setOrientation(VERTICAL);
        ((TextViewButtonLinerLayout)getChildAt(16)).getButton().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeningLinerLayout oLL = new OpeningLinerLayout(getContext());
                ((LinearLayout)getChildAt(17)).addView(oLL);

            }
        });

        //18
        addView(new LinearLayout(getContext()));
        getChildAt(18).setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics())));
        getChildAt(18).setBackgroundColor(0xffcf0009);


        child.add((TextViewEditTextLinerLayout) getChildAt(1));//0
        child.add((TextViewEditTextLinerLayout) getChildAt(2));
        child.add((TextViewEditTextLinerLayout) getChildAt(3));//2
        child.add((TextViewSpinnerLinerLayout) getChildAt(4));
        child.add((TextViewEditTextLinerLayout) getChildAt(5));//4
        child.add((TextViewSpinnerLinerLayout) getChildAt(6));
        child.add((TextViewEditTextLinerLayout) getChildAt(7));//6
        child.add((TextViewSpinnerLinerLayout) getChildAt(8));
        child.add((TextViewSpinnerLinerLayout) getChildAt(9));//8
        child.add((TextViewSpinnerLinerLayout) getChildAt(10));
        child.add((TextViewSpinnerLinerLayout) getChildAt(11));//10
        child.add((CompactLinearLayout) getChildAt(13));
        child.add((CompactLinearLayout) getChildAt(15));//12
        child.add((CompactLinearLayout) getChildAt(17));


    }

    @Override
    public View getFill() {
        focus = null;
        for(int i = 0; i < child.size(); i++){
            View v = ((LinearLayoutChild) child.get(i)).getFill();
            if(null == focus){
                focus = v;
            }
        }
        return focus;
    }




    @Override
    public double getCalclation() {
        return 0;
    }

    public ArrayList<Double> getCalcLevel(){

        ArrayList<Double> levelValues = new ArrayList<>();

        for(int i =0; i < child.size(); i++){

            if(i == 8){
                double d = (double)position8;
                levelValues.add(d);
                Log.d(TAG, "position8 " + d);
            }else if(i == 9){
                double d = (double)position9;
                levelValues.add(d);
                Log.d(TAG, "position9 " + d);
            }else{
                levelValues.add(((LinearLayoutChild) child.get(i)).getCalclation());
                Log.d(TAG, "position " + i);
            }
        }



        return calc13(levelValues);
    }

    private ArrayList<Double> calc13(ArrayList<Double> levelValues) {

        ArrayList<Double> res = new ArrayList<>();
        double boi = 1 +  ((double)MainActivity.spinerCut.getSelectedItemPosition())/100;

        double qUBlockOnBelt = 0;
        double qUBlockInBelt = 0;

        double kolvoUBlockOutBelt = 0;
        double kolvoUBlockInBelt = 0;
        double qBlockPerekritie = 0;


        double countFloor = levelValues.get(0);
        res.add(countFloor);//0
        double hWall = levelValues.get(1);
        double lOutMainWall = levelValues.get(2);
        double wOutMainWall = levelValues.get(3);
        res.add(wOutMainWall);//1

        double lInMainWall = levelValues.get(4);
        double wInMainWall = levelValues.get(5);
        res.add(wInMainWall);//2

        double lDopWall = levelValues.get(6);
        double wDopWall = levelValues.get(7);
        res.add(wDopWall );//3

        double armopoyasYN =  levelValues.get(8);

        double perekritiyaYN = levelValues.get(9);

        double wBlockPerekritiya = levelValues.get(10);
        res.add(wBlockPerekritiya);//4
        




        //-------------------- объем проемов в наружных стенах  ------------------------------
       double qWindowsOutWals = wOutMainWall *levelValues.get(11);


        //Объем U-блока над проемом = (ширина  проема  + 0,5) умн на толщину стены умн на 0,25
        double qUBlockUnderWindowsOutWals = wOutMainWall *((LinearLayoutChild) child.get(11)).getCalcWidth()*0.25;

        //сумма всех длин проемов (с запасом)
        double wUBlockAllOutWals = ((LinearLayoutChild) child.get(11)).getCalcWidth();

        //-----------------------------------------------объем проемов в внутр стенах
        double qWindowsInsWals = wInMainWall * levelValues.get(12);

        //Объем U-блока над проемом = (ширина  проема  + 0,5) умн на толщину стены умн на 0,25
        double qUBlockUnderWindowsInWals = wInMainWall * ((LinearLayoutChild) child.get(12)).getCalcWidth()*0.25;

        //--------------------------сумма всех длин проемов (с запасом)
        double wUBlockAllInWals = ((LinearLayoutChild) child.get(12)).getCalcWidth();

        //объем проемов в перегородках
        double qWindowsDopWals = wDopWall * levelValues.get(13);

        // Объем стен = толщина стены (умн) на длину стены (умн) навысота от пола до потолка

        double qOutWals = wOutMainWall * lOutMainWall * hWall;//Объем наружных стен
        double qInWals = wInMainWall * lInMainWall * hWall;//Объем внутренних стен
        double qDopWals = wDopWall * lDopWall * hWall;//Объем перегородок //lDopWall * hWall * wDopWall


        // объем всех стен без проемов
        double qRealAllWals = (qOutWals - qWindowsOutWals) + (qInWals - qWindowsInsWals) + (qDopWals - qWindowsDopWals);


        if(armopoyasYN == 1.0){
            // Объем U-блоков на монолитный пояс = длина стен умн на толщ стен умн на 0,25
            qUBlockOnBelt = lOutMainWall * wOutMainWall  * 0.25; //Объем U-блоков на монолитный пояс наружных стен
            qUBlockInBelt = lInMainWall * wInMainWall  * 0.25; //Объем U-блоков на монолитный пояс внутренних стен
        }else{
            qUBlockOnBelt = 0;
            qUBlockInBelt = 0;
        }


        //Объем блока для наружных стен = объем стен – объем  проемов – объем U-блоков над проемами –  объем U-блоков на монолитный пояс
         double qBlockOutWall = (qOutWals - qWindowsOutWals - qUBlockUnderWindowsOutWals - qUBlockOnBelt) * boi;

        res.add(qBlockOutWall*countFloor);//5
        // Объем блока для вн несущ стен = объем стен – объем  проемов – объем U-блоков над проемами –  объем U-блоков на монолитный пояс
        double qBlockInWall = (qInWals - qWindowsInsWals - qUBlockUnderWindowsInWals - qUBlockInBelt) * boi;
        res.add(qBlockInWall*countFloor);//6


        if(armopoyasYN == 1){
            // к-во U-блоков на монолитный пояс наружных стен, шт   =  Длина стены / на 0,5м
            kolvoUBlockOutBelt = Math.ceil((lOutMainWall / 0.5) * boi);
        }else{
            kolvoUBlockOutBelt = 0;
        }

        res.add(kolvoUBlockOutBelt*countFloor);//7
        //  к-во U-блоков на проемы наруж стен = Ширина проема + 0,5м / на 0,5
        double kolvoUBlockProemOutWell = Math.ceil((wUBlockAllOutWals / 0.5) * boi);
        res.add(kolvoUBlockProemOutWell*countFloor);//8


        if(armopoyasYN == 1.0){
            // к-во U-блоков на монол пояс вн несущ стен, шт =  Длина стены / на 0,5м
            kolvoUBlockInBelt  = Math.ceil((lInMainWall / 0.5) * boi);
        }else{
           kolvoUBlockInBelt = 0;
        }
        res.add(kolvoUBlockInBelt*countFloor);//9

        //  к-во U-блоков на проемы вн несущ стен = Ширина проема + 0,5м / на 0,5
         double kolvoUBlockProemInWell = Math.ceil((wUBlockAllInWals / 0.5) * boi);
        res.add(kolvoUBlockProemInWell*countFloor);//10

        if(perekritiyaYN == 1){
            // объем блоков в уровне перекрытий = Ширина блока умн на 0,25 умн на длину стен
            qBlockPerekritie = wBlockPerekritiya * 0.25 * lOutMainWall * boi;
        }else{
            qBlockPerekritie = 0;
        }
        res.add(qBlockPerekritie*countFloor);//11

        // объем блоков на перегородки  = Длина стены умн на высоту от пола до потолка умн на толщину
        double qBlockDopWall = (qDopWals  - qWindowsDopWals)* boi;
        res.add(qBlockDopWall*countFloor);//12

        // упаковок клея  = 1меш Х весь объем м3 газоблока
        double kolvoGlue = Math.round(qRealAllWals * boi);
        res.add(kolvoGlue*countFloor);//13

        return res;
    }

    private ArrayList<Double> calc14(ArrayList<Double> levelValues) {

        return null;
    }

    public void setLabelText(String s){
        ((TextViewButtonLinerLayout) getChildAt(0)).getTextView().setText("Тип Этажа " + s);
    }

    public void removeLabelButton(){
        ((TextViewButtonLinerLayout) getChildAt(0)).removeViewAt(1);
        ((TextViewButtonLinerLayout) getChildAt(0)).setLayoutParams(new TextViewButtonLinerLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics())));
    }


    @Override
    public double getCalcWidth() {
        return 0;
    }


    public int getFirstChaildCount(){
       return  ((LinearLayout)getChildAt(0)).getChildCount();
    }
}
