package com.komugi.textviewtypeface;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by iapp on 19/12/16.
 */
public class CenturyGothicItalicTextView extends TextView {
    public CenturyGothicItalicTextView(Context context) {
        super(context);
        init();
    }

    public CenturyGothicItalicTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CenturyGothicItalicTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "century_gothic_italic.ttf");
        setTypeface(tf);
    }
}
