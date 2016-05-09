package com.linjiaxiaohai.rgb;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import com.umeng.analytics.MobclickAgent;

/**
 * activity基类
 * Created by Meng on 16/4/24.
 */
public class BaseActivity extends AppCompatActivity {

    private InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputMethodManager = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MobclickAgent.onPause(this);
    }

    protected void hideInputMethod() {
        if (getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
