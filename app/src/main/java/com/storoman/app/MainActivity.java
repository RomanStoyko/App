package com.storoman.app;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.storoman.app.packageLayout.LevelLayout;
import com.storoman.app.packageLayout.MansardLevelLayout;
import com.storoman.app.packageLayout.ResultLayout;
import com.storoman.app.util.SystemUiHider;

import java.util.ArrayList;

public class MainActivity extends Activity {


    private static final int DIALOG_NOFILL = 1;
    private static final int DIALOG_FULLFLOAR = 2;
    private static final int DIALOG_FULLMANS = 3;

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 5000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = false;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }



    private static final String TAG = "MainActivity";

    public static Spinner spinerCut;

    public static ArrayList<LevelLayout> levelLayoutArrayList = new ArrayList<>();// LevelLayout


    View focus = null;

    private AdView adView;



    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);
        setContentView(R.layout.activity_main);

        final View contentView = findViewById(R.id.fullscreen_content_main);


        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("4BCE90AEA7D1CE6A2CCC5B7A88DDF50A")
                .build();
        adView.loadAd(adRequest);

//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);


        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mShortAnimTime;


                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {

                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }

                        }
                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });



        addLevel();
        (levelLayoutArrayList.get(0)).setLabelText(String.valueOf(levelLayoutArrayList.size()));
        if( (levelLayoutArrayList.get(0)).getFirstChaildCount() > 1){//?
            (levelLayoutArrayList.get(0)).removeLabelButton();
        }




        spinerCut = (Spinner) findViewById(R.id.spinnerCut);
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.cutError));

        spinerCut.setAdapter(adapter);
        spinerCut.setSelection(5);
        spinerCut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



}


    public void hide(View view) {
        LinearLayout parent =  (LinearLayout)view.getParent();
        ((LinearLayout) parent.getParent()).removeView(parent);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    /** Called before the activity is destroyed. */
    @Override
    public void onDestroy() {
// Destroy the AdView.
        if (adView != null) {
            adView.destroy();
        }
        levelLayoutArrayList.clear();
        super.onDestroy();
    }



    public void finalCalc(View view) {

        LinearLayout parentLay = (LinearLayout) findViewById(R.id.resltList);
        parentLay.removeAllViews();

        Log.d(TAG, "finalCalc press");


        Log.d(TAG, "clear focus view");
        if(focus != null){
//            focus.clearFocus();
            focus = null;
       }
        Log.d(TAG, "Lockig for focus view");
        for(int i = 0; i < levelLayoutArrayList.size(); i++){
            View v = levelLayoutArrayList.get(i).getFill();
            if(null == focus){
                focus = v;
            }
        }
        if(((LinearLayout) findViewById(R.id.mansard)).getChildCount()>0){
            View v =  ((MansardLevelLayout)((LinearLayout) findViewById(R.id.mansard)).getChildAt(0)).getFill();
            if(null == focus){
                focus = v;
            }
        }

        if(focus != null){


            showDialog(DIALOG_NOFILL);

        }
        Log.d(TAG, "focus == null");
        if(focus == null){


            parentLay.removeAllViews();
            for(int i = 0; i < levelLayoutArrayList.size(); i++){
               ArrayList<Double> res = levelLayoutArrayList.get(i).getCalcLevel();
                parentLay.addView(new ResultLayout(this, res, i + 1));
            }
            if(((LinearLayout) findViewById(R.id.mansard)).getChildCount()>0){
                ArrayList<Double> res = ((MansardLevelLayout)((LinearLayout) findViewById(R.id.mansard)).getChildAt(0)).getMansLevel();
                parentLay.addView(new ResultLayout(this,res));
            }
        }


    }



    public void addLevel() {

        Log.d(TAG, "addLevel");
       final LinearLayout parentLay = (LinearLayout) findViewById(R.id.addlevel);

        if(levelLayoutArrayList.size() <= 3 ) {
            Log.d(TAG, "array size  " + String.valueOf(levelLayoutArrayList.size()));
            LevelLayout ll = new LevelLayout(this);
            levelLayoutArrayList.add(ll);
            ll.setLabelText(String.valueOf(levelLayoutArrayList.size()));
            parentLay.addView(ll);
        }else {
            showDialog(DIALOG_FULLFLOAR);
        }


    }

    public void addMans(View view) {
        Log.d(TAG, "addMansLevel");
        if(((LinearLayout) findViewById(R.id.mansard)).getChildCount()<1){
            ((LinearLayout) findViewById(R.id.mansard)).addView(new MansardLevelLayout(this));
        }else{
            showDialog(DIALOG_FULLMANS);
        }
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(0);
    }



    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };



    public void addLevel(View view) {
        addLevel();
    }

    public static void levelRemove() {

        int position = 0;
        for(int i = 0; i < levelLayoutArrayList.size();i++){
            if(levelLayoutArrayList.get(i).getVisibility() == View.GONE){
                position = i;
                Log.d(TAG, "position = " + String.valueOf(position));
            }
        }
        levelLayoutArrayList.remove(position);
        if(levelLayoutArrayList.size()>1) {
            Log.d(TAG, "rename");
            for (int i = 0; i < levelLayoutArrayList.size(); i++) {
                   levelLayoutArrayList.get(i).setLabelText( String.valueOf(i+1));
            }
        }

    }

    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this, R.style.myBackgroundStyle);
        adb.setIcon(android.R.drawable.ic_dialog_info);
        adb.setInverseBackgroundForced(true);
        if (id == DIALOG_NOFILL) {    adb.setTitle("Не все поля заполнены");   return adb.create();      }
        if (id == DIALOG_FULLFLOAR) { adb.setTitle("Максимальное количиство Простых этажей");  return adb.create();          }
        if (id == DIALOG_FULLMANS) {  adb.setTitle("Максимальное количиство Мансардных этажей");  return adb.create();        }


        return super.onCreateDialog(id);
    }

}