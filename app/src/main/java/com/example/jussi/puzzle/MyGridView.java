package com.example.jussi.puzzle;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;

/**
 * Created by jussi on 10/16/17.
 */

public class MyGridView extends GridView {
    public MyGridView(Context context) {
        super(context);
    }


    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
    }


    @Override
    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {


        super.setOnItemClickListener(listener);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }


    @Override
    public OnFocusChangeListener getOnFocusChangeListener() {
        return super.getOnFocusChangeListener();
    }


    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        super.setOnFocusChangeListener(l);
    }


    @Override
    public void setOnContextClickListener(@Nullable OnContextClickListener l) {
        super.setOnContextClickListener(l);
    }
}
