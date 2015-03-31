package com.storoman.app;


import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";


    public static int idNum = 1000;
    final Map<View, View> layoutMap = new HashMap<>();
    Map<View, ArrayList<int[]>> arrayListMap  = new HashMap<>();

    ArrayList<int[]> layout1Array = new ArrayList<>();
    ArrayList<int[]> layout2Array = new ArrayList<>();
    ArrayList<int[]> layout3Array = new ArrayList<>();
    ArrayList<int[]> layout4Array = new ArrayList<>();
    ArrayList<int[]> layout5Array = new ArrayList<>();
    ArrayList<int[]> layout6Array = new ArrayList<>();
    ArrayList<int[]> layout7Array = new ArrayList<>();
    ArrayList<int[]> layout8Array = new ArrayList<>();
    ArrayList<int[]> layout9Array = new ArrayList<>();

    ArrayList<View> levelOne = new ArrayList<>();
    ArrayList<View> levelTwo = new ArrayList<>();
    ArrayList<View> levelThree = new ArrayList<>();

    String text1 = "Объем блоков для наружных стен (толщина блоков: ";

    ArrayList<Double> levelOneDouble = new ArrayList<>();
    ArrayList<Double> levelTwoDouble = new ArrayList<>();
    ArrayList<Double> levelThreeDouble = new ArrayList<>();
//    ArrayList<View> levelfoure = new ArrayList<>();
//    ArrayList<View> levelMans = new ArrayList<>();

    String spiner11;

    Spinner spnr11, spnr12, spnr13, spnr14, spnr15, spnr16, spnrCut;
    Spinner spnr21, spnr22, spnr23, spnr24, spnr25, spnr26;
    Spinner spnr31, spnr32, spnr33, spnr34, spnr35, spnr36;


    View focus = null;

    double  ds1, ds2, ds3;



    String[] cutError = {
        "+0%",
        "+1%",
        "+2%",
        "+3%",
        "+4%",
        "+5%",
        "+6%",
        "+7%",
        "+8%",
        "+9%",
        "+10%"
    };

    int i = 1000;
    String[] wallLength = {
            "200",
            "250",
            "300",
            "350",
            "375",
            "400",
            "500"
    };
    String[] shortWallLength = {

            "300",
            "350",
            "375",
            "400",
            "500"
    };
    String[] bulkLength = {
            "50",
            "75",
            "100",
            "125",
            "150",
            "175",
            "200"
    };
    String[] blockLength = {
            "75",
            "100",
            "150",
            "200",
            "250",
            "300",
            "375",
            "400",
            "500"
    };

    String[] noYes = {"Нет", "Да"};



    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        putInMap();
        putInLevelId();



        ArrayAdapter<String> cutErrorAdapter = new ArrayAdapter<String>(this, R.layout.ss, cutError);
      final  ArrayAdapter<String> wallLenghAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.ss, wallLength);
      final  ArrayAdapter<String> shortWallLenghAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.ss, shortWallLength);
        ArrayAdapter<String> bulkLenghAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.ss, bulkLength);
        ArrayAdapter<String> noYesAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.ss, noYes);


        spnr11 = (Spinner) findViewById(R.id.spinner11);
        spnr11.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        spiner11 = (String)spnr11.getSelectedItem();
                        ds1 = Double.parseDouble(spiner11)/1000;
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                }
        );


        spnr12 = (Spinner) findViewById(R.id.spinner12);
        spnr12.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        ds2 = Double.parseDouble((String)spnr12.getSelectedItem())/1000;
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                }
        );

        spnr13 = (Spinner) findViewById(R.id.spinner13);
        spnr13.setAdapter(bulkLenghAdapter);
        spnr13.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        ds3 = Double.parseDouble((String)spnr13.getSelectedItem())/1000;

                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                }
        );


        spnr14 = (Spinner)findViewById(R.id.spinner14);
        spnr14.setAdapter(noYesAdapter);
        spnr14.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = spnr14.getSelectedItemPosition();
                        if(position == 0) {
                            spnr11.setAdapter(wallLenghAdapter);
                            spnr12.setAdapter(wallLenghAdapter);
                        }
                        if(position == 1) {
                            spnr11.setAdapter(shortWallLenghAdapter);
                            spnr12.setAdapter(shortWallLenghAdapter);
                        } // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                }
        );

        spnr15 = (Spinner)findViewById(R.id.spinner15);
        spnr15.setAdapter(noYesAdapter);
        spnr15.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = spnr15.getSelectedItemPosition();
                        if (position == 1) {
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.ss, blockLength);
                            LinearLayout parent = (LinearLayout) findViewById(R.id.spinner16).getParent();
                            parent.setVisibility(View.VISIBLE);
                            spnr16 = (Spinner) findViewById(R.id.spinner16);
                            spnr16.setAdapter(adapter);
                            spnr16.setOnItemSelectedListener(
                                    new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                                   int arg2, long arg3) {
                                            int position = spnr16.getSelectedItemPosition();

                                            // TODO Auto-generated method stub
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> arg0) {
                                            // TODO Auto-generated method stub
                                        }
                                    }
                            );
                        } else {
                            LinearLayout parent = (LinearLayout) findViewById(R.id.spinner16).getParent();
                            parent.setVisibility(View.GONE);
                        }

                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                }
        );



        spnrCut = (Spinner)findViewById(R.id.spinnerCut);
        spnrCut.setAdapter(cutErrorAdapter);
        spnrCut.setSelection(5);
        spnrCut.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = spnrCut.getSelectedItemPosition();

                        // TODO Auto-generated method stub
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                }
        );



}

    private void putInLevelId() {

        levelOne.add(0, findViewById(R.id.et11));
        levelOne.add(1, findViewById(R.id.et12));
        levelOne.add(2, findViewById(R.id.et13));
        levelOne.add(3, findViewById(R.id.et14));
        levelOne.add(4, findViewById(R.id.et15));


        levelTwo.add(findViewById(R.id.et21));
        levelTwo.add(findViewById(R.id.et22));
        levelTwo.add(findViewById(R.id.et23));
        levelTwo.add(findViewById(R.id.et24));
        levelTwo.add(findViewById(R.id.et25));

        levelThree.add(findViewById(R.id.et31));
        levelThree.add(findViewById(R.id.et32));
        levelThree.add(findViewById(R.id.et33));
        levelThree.add(findViewById(R.id.et34));
        levelThree.add(findViewById(R.id.et35));
    }

    private void putInMap() {
        layoutMap.put(findViewById(R.id.button1), findViewById(R.id.addLayout1));
        arrayListMap.put(findViewById(R.id.button1), layout1Array);

        layoutMap.put(findViewById(R.id.button2), findViewById(R.id.addLayout2));
        arrayListMap.put(findViewById(R.id.button2), layout2Array);

        layoutMap.put(findViewById(R.id.button3), findViewById(R.id.addLayout3));
        arrayListMap.put(findViewById(R.id.button3), layout3Array);

        layoutMap.put(findViewById(R.id.button4), findViewById(R.id.addLayout4));
        arrayListMap.put(findViewById(R.id.button4), layout4Array);

        layoutMap.put(findViewById(R.id.button5), findViewById(R.id.addLayout5));
        arrayListMap.put(findViewById(R.id.button5), layout5Array);

        layoutMap.put(findViewById(R.id.button6), findViewById(R.id.addLayout6));
        arrayListMap.put(findViewById(R.id.button6), layout6Array);

        layoutMap.put(findViewById(R.id.button7), findViewById(R.id.addLayout7));
        arrayListMap.put(findViewById(R.id.button7), layout7Array);

        layoutMap.put(findViewById(R.id.button8), findViewById(R.id.addLayout8));
        arrayListMap.put(findViewById(R.id.button8), layout8Array);

        layoutMap.put(findViewById(R.id.button9), findViewById(R.id.addLayout9));
        arrayListMap.put(findViewById(R.id.button9), layout9Array);
    }


    public void hide(View view) {
        LinearLayout parent =  (LinearLayout)view.getParent();
        parent.setVisibility(View.GONE);
    }

    public void add(View view) {
        LinearLayout parent = (LinearLayout) layoutMap.get(view);
        ArrayList<int[]> list = arrayListMap.get(view);

//
//        LinearLayout linearLayout = new LinearLayout(this);
//        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        linearLayout.setVisibility(View.VISIBLE);
//        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        OpeningLinerLayout oLL = new OpeningLinerLayout(this);
        parent.addView(oLL);
        list.add(oLL.getIdEdit());


//        parent.addView(linearLayout);
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT, 4.0f);
//        TextView tx1 = new TextView(this);
//        int pading = (int)  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
//        tx1.setPadding(pading, pading, pading, pading);
//        tx1.setText("Высота проёма, м");
//        tx1.setLayoutParams(params);
//        tx1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
//        tx1.setGravity(Gravity.TOP);
//
//        TextView tx2 = new TextView(this);
//        tx2.setPadding(pading, pading, pading, pading);
//        tx2.setText("Ширина проёма, м");
//        tx2.setLayoutParams(params);
//        tx2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
//        tx1.setGravity(Gravity.TOP);
//
//        InputFilter[] fArray = new InputFilter[1];
//        fArray[0] = new InputFilter.LengthFilter(4);
//
//        int px = (int)  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 75, getResources().getDisplayMetrics());
//        params = new LinearLayout.LayoutParams(px, LinearLayout.LayoutParams.FILL_PARENT, 1.0f);
//        params.setMargins(5, 5, 5, 5);
//        final EditTextExtended ex1 = new EditTextExtended(this);
//        ex1.setLayoutParams(params);
//        ex1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
//        ex1.setFocusable(true);
//        ex1.setBackgroundResource(R.drawable.rect_text_edir);
//        ex1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
//        ex1.setFilters(fArray);
//        ex1.setHint("xx.x");
//
//        ex1.addTextChangedListener(TWlistner);
//        ex1.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                    focus = ex1;
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    ex1.setBackgroundResource(R.drawable.rect_text_edir);
//                focus = ex1;
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        int ex1id = i;i++;
//        ex1.setId(ex1id);
//
//
//        final EditTextExtended ex2 = new EditTextExtended(this);
//        ex2.setLayoutParams(params);
//        ex2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
//        ex2.setFocusable(true);
//        ex2.setBackgroundResource(R.drawable.rect_text_edir);
//        ex2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
//        ex2.setFilters(fArray);
//
//        ex2.setHint("xx.x");
//        ex2.addTextChangedListener(TWlistner);
//        ex2.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                focus = ex2;
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                ex2.setBackgroundResource(R.drawable.rect_text_edir);
//                focus = ex2;
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        int ex2id = i;i++;
//        ex2.setId(ex2id);
//
//
//
//        list.add(new int[]{ex1.getId(), ex2.getId()});
//
//        Button bt = new Button(this);
//        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        bt.setLayoutParams(params);
//        bt.setText("X");
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                hide(v);
//            }
//        });
//
//
//        linearLayout.addView(tx1);
//        linearLayout.addView(ex1);
//        linearLayout.addView(tx2);
//        linearLayout.addView(ex2);
//        linearLayout.addView(bt);

    }

    public void finalCalc(View view) {

        if(focus != null){
            focus.clearFocus();
            focus = null;
        }

        Log.d(TAG, "Рассчитать нажат");
        LinearLayout resultLayout = (LinearLayout) findViewById(R.id.resltList);


        for(int i =0; i< levelOne.size();i++){
            final EditTextExtended ete = (EditTextExtended)levelOne.get(i);
           ete.addTextChangedListener(TWlistner);
            ete.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    focus = ete;
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    ete.setBackgroundResource(R.drawable.rect_text_edir);
                    focus = ete;
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            if(((EditTextExtended)levelOne.get(i)).getText().toString().trim().equals("")){
                levelOne.get(i).setBackgroundResource(R.drawable.rect_text_edir_error);
                if(focus == null){
                    focus = findViewById(levelOne.get(i).getId());


                    Log.d(TAG, "set focus view");
                }
            }
        }


        Iterator<int[]> iterator;
        if(layout1Array.size()>0) {
            iterator = layout1Array.iterator();
            while (iterator.hasNext()) {
                int[] data = iterator.next();
                EditText ex1 = (EditText) findViewById(data[0]);
                EditText ex2 = (EditText) findViewById(data[1]);
                LinearLayout parent =  (LinearLayout)ex1.getParent();
                if(parent.getVisibility() == View.GONE || ex1.getText().toString().trim().equals("") && ex2.getText().toString().trim().equals("")) {
                    hide(ex1);
                    iterator.remove();
                } else {
                    ex1.setBackgroundResource(R.drawable.rect_text_edir);
                    ex2.setBackgroundResource(R.drawable.rect_text_edir);
                }
                if (ex1.getText().toString().trim().equals("")) {
                    if (focus == null) {
                        focus =  findViewById(data[0]);
                    }
                    ex1.setBackgroundResource(R.drawable.rect_text_edir_error);

                }
                if (ex2.getText().toString().trim().equals("")) {
                    if (focus == null) {
                        focus = findViewById(data[1]);
                    }
                    ex2.setBackgroundResource(R.drawable.rect_text_edir_error);

                }
            }


        }
        if(focus == null) {
            Log.d(TAG, "focus == null");
            resultLayout.setVisibility(View.VISIBLE);
            for(int i = 0; i < levelOne.size(); i++){
                levelOneDouble.add(i,Double.parseDouble(((EditTextExtended)levelOne.get(i)).getText().toString()));
            }
            ((TextView) findViewById(R.id.answer111)).setText(text1 + spiner11 + " мм) м"+ Character.toString((char) 179));

            double res1 = levelOneDouble.get(0) * levelOneDouble.get(1) * levelOneDouble.get(2) * ds1;
            ((TextView)findViewById(R.id.answer112)).setText(String.valueOf(res1));

            getWindow().setSoftInputMode(EditorInfo.IME_ACTION_DONE);


        }else{
            focus.requestFocus();
            focus.clearFocus();
        }
    }




    TextWatcherExtendedListener TWlistner = new TextWatcherExtendedListener() {


        @Override
        public void afterTextChanged(View v, Editable s) {

                v.setBackgroundResource(R.drawable.rect_text_edir);

        }

        @Override
        public void onTextChanged(View v, CharSequence s, int start, int before, int count) {
            v.setBackgroundResource(R.drawable.rect_text_edir);

        }

        @Override
        public void beforeTextChanged(View v, CharSequence s, int start, int count, int after) {

        }
    };

    public void addLevel(View view) {

        Log.d(TAG, "addLevel");
       final LinearLayout parentLay = (LinearLayout) findViewById(R.id.addlevel);

        parentLay.addView(new TextEditTextLinerLayout(this, "Количество этажей данного типа", "1", "x", InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, 1));
        parentLay.addView(new TextEditTextLinerLayout(this, "Высота от пола до потолка, м", null, "xx.x", InputType.TYPE_CLASS_NUMBER, 4));
        parentLay.addView(new TextEditTextLinerLayout(this, "Длина наружных несущих стен, м", null, "xxxx.x", InputType.TYPE_CLASS_NUMBER, 6));
        parentLay.addView(new TextSpinnerLinerLayout(this, "Толщина наружной несущей стены, мм", R.layout.ss, wallLength));
        parentLay.addView(new TextEditTextLinerLayout(this, "Длина внутренних несущих стен, м", null, "xxxx.x", InputType.TYPE_CLASS_NUMBER, 6));
        parentLay.addView(new TextSpinnerLinerLayout(this, "Толщина внутренней несущей стены, мм", R.layout.ss, wallLength));
        parentLay.addView(new TextEditTextLinerLayout(this, "Длина перегородок, м", null, "xxxx.x", InputType.TYPE_CLASS_NUMBER, 6));
        parentLay.addView(new TextSpinnerLinerLayout(this, "Толщина перегородки, мм", R.layout.ss, bulkLength));
        parentLay.addView(new TextSpinnerLinerLayout(this, "U-блок для армопояса", R.layout.ss, noYes));

        parentLay.addView(new TextSpinnerLinerLayout(this, "Наличие перекрытия", R.layout.ss, noYes));
        ((TextSpinnerLinerLayout)parentLay.getChildAt(8)).getSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, String.valueOf(position));
                if(position == 0){
                    ((TextSpinnerLinerLayout) parentLay.getChildAt(3)).setAdapter(wallLength);
                    ((TextSpinnerLinerLayout) parentLay.getChildAt(5)).setAdapter(wallLength);
                }
                if(position == 1){
                    ((TextSpinnerLinerLayout)parentLay.getChildAt(3)).setAdapter(shortWallLength);
                    ((TextSpinnerLinerLayout)parentLay.getChildAt(5)).setAdapter(shortWallLength);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        parentLay.addView(new TextButtonLinerLayout(this, "Проёмы в наружных стенах", null));

//       if(findViewById(R.id.level2).getVisibility() == View.GONE){
//           findViewById(R.id.level2).setVisibility(View.VISIBLE);
//       }else if(findViewById(R.id.level3).getVisibility() == View.GONE){
//           findViewById(R.id.level3).setVisibility(View.VISIBLE);
//       }else{
//
//       }

    }

    public void addMans(View view) {

        LinearLayout ll = (LinearLayout) findViewById(R.id.mansard);
        ll.setVisibility(View.VISIBLE);
    }

    public void removeFlore(View view) {
        LinearLayout parent = (LinearLayout) view.getParent().getParent();
        parent.setVisibility(View.GONE);
    }
}