package com.komugi.edittexttypeface;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by iapp on 21/12/16.
 */
public class CenturyGothicEditText  extends EditText {


    public CenturyGothicEditText(Context context) {
        super(context);
        init();
    }

    public CenturyGothicEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CenturyGothicEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "century_gothic.ttf");
        setTypeface(tf);
    }
}
