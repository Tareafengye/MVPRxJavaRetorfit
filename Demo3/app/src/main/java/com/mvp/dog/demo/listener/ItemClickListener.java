package com.mvp.dog.demo.listener;

import android.view.View;

public interface ItemClickListener {
    void onItemClick(View v, int position);

    boolean onItemLongClick(View v, int position);
}
