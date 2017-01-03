package com.komugi.textviewtypeface;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by iapp on 21/12/16.
 */
public class CentuaryGothicBoldItalicTextView extends TextView {

    public CentuaryGothicBoldItalicTextView(Context context) {
        super(context);
        init();
    }

    public CentuaryGothicBoldItalicTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CentuaryGothicBoldItalicTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "century_gothic_bold_italic.ttf");
        setTypeface(tf);
    }
}
