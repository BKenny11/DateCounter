package com.brendan_and_eric.datecounter;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

    private GestureDetector mGestureDetector;
    private ClickListener mClickListener;

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener){
        Log.d("RecyclerTouchListener", "constructor invoked");
        this.mClickListener = clickListener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

           @Override
           public boolean onSingleTapUp(MotionEvent e){
               Log.d("RecyclerTouchListener", "onSingleTapUp");
               return true;
           }

            @Override
            public void onLongPress(MotionEvent e){
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(child != null && clickListener != null){
                    clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
                Log.d("RecyclerTouchListener", "onLongPress");
            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if(child != null && mClickListener != null && mGestureDetector.onTouchEvent(e)){
            mClickListener.onClick(child, rv.getChildPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        Log.d("RecyclerTouchListener", "onTouchEvent" + e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public static interface ClickListener{
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }
}
