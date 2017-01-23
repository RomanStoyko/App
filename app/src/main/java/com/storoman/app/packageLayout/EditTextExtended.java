package com.storoman.app.packageLayout;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import com.storoman.app.R;


public class EditTextExtended extends EditText implements TextWatcherExtendedListener
{
    private TextWatcherExtendedListener  mListeners = null;

    public EditTextExtended(Context context)
    {
        super(context);
    }

    public EditTextExtended(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public EditTextExtended(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public void addTextChangedListener(TextWatcherExtendedListener watcher)
    {

            mListeners = watcher;

    }

    public void removeTextChangedListener(TextWatcherExtendedListener watcher)
    {
        if (mListeners != null)
        {
            mListeners = null;
        }
    }

    void  sendBeforeTextChanged(CharSequence text, int start, int before, int after)
    {
        if (mListeners != null)
        {
            mListeners.beforeTextChanged(this, text, start, before, after);
        }
    }

    void  sendOnTextChanged(CharSequence text, int start, int before,int after)
    {
        if (mListeners != null)
        {
            mListeners.onTextChanged(this, text, start, before, after);
        }
    }

    void  sendAfterTextChanged(Editable text)
    {
        if (mListeners != null)
        {
            mListeners.afterTextChanged(this, text);
        }
    }

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
}