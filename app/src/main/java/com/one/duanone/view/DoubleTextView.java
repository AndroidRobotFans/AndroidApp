package com.one.duanone.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.one.duanone.R;

/**
 * Created by jj on 2016/8/2.
 */
public class DoubleTextView extends View{
    TextView tv1,tv2;
    public DoubleTextView(Context context) {
        this(context,null);
    }

    public DoubleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view=View.inflate(context, R.layout.double_textview,null);
        tv1= (TextView) view.findViewById(R.id.tv1);
        tv2= (TextView) view.findViewById(R.id.tv2);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.DoubleTextView);
        String s1 =a.getString(R.styleable.DoubleTextView_text1);
        String s2 = a.getString(R.styleable.DoubleTextView_text2);
        tv1.setText(s1);
        tv2.setText(s2);
        a.recycle();
    }

    public void setTv2Text(String content) {
        tv2.setText(content);
    }
}
