package com.linjiaxiaohai.rgb;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.linjiaxiaohai.rgb.utils.BitmapUtils;
import com.linjiaxiaohai.rgb.view.ColorSeekBar;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends BaseActivity implements ColorView{

    private ColorSeekBar seekBarA;
    private ColorSeekBar seekBarR;
    private ColorSeekBar seekBarG;
    private ColorSeekBar seekBarB;
    private TextView colorView;
    private TextView colorTextView;

    private ColorPresenter colorPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorPresenter = new ColorPresenter(this);

        initView();

        //default
        seekBarA.setProgress(100);
        seekBarR.setProgress(70);
        seekBarG.setProgress(80);
        seekBarB.setProgress(90);

        MobclickAgent.onResume(this);
    }

    private void initView() {
        colorTextView = (TextView) findViewById(R.id.text);
        colorView = (TextView) findViewById(R.id.color);
        seekBarA = (ColorSeekBar) findViewById(R.id.color_a);
        seekBarR = (ColorSeekBar) findViewById(R.id.color_r);
        seekBarG = (ColorSeekBar) findViewById(R.id.color_g);
        seekBarB = (ColorSeekBar) findViewById(R.id.color_b);

        seekBarA.setHint("A");
        seekBarR.setHint("R");
        seekBarG.setHint("G");
        seekBarB.setHint("B");
        seekBarA.setMax(100);

        seekBarA.setOnSeekBarChangeListener(new ColorSeekBarChangeListener(ColorType.ALPHA));
        seekBarR.setOnSeekBarChangeListener(new ColorSeekBarChangeListener(ColorType.RED));
        seekBarG.setOnSeekBarChangeListener(new ColorSeekBarChangeListener(ColorType.GREEN));
        seekBarB.setOnSeekBarChangeListener(new ColorSeekBarChangeListener(ColorType.BLUE));
    }

    class ColorSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        private ColorType type;

        public ColorSeekBarChangeListener(ColorType type) {
            this.type = type;
        }

        @Override
        public void onProgressChanged(SeekBar bar, int i, boolean b) {
            colorPresenter.color(type, i);
        }

        @Override
        public void onStartTrackingTouch(SeekBar bar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar bar) {

        }
    }

    @Override
    public void colorChanged(int color, String hexColor) {
        colorTextView.setText(hexColor);
        colorView.setBackgroundColor(color);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);
        initView();
        seekBarA.setProgress(colorPresenter.getAlpha());
        seekBarR.setProgress(colorPresenter.getRed());
        seekBarG.setProgress(colorPresenter.getGreen());
        seekBarB.setProgress(colorPresenter.getBlue());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_feedback:
                openFeedback();
                return true;
            case R.id.action_donate:
                showDonateDialog();
                break;
            case R.id.action_about:
                showAboutDialog();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 打开反馈页面
     */
    private void openFeedback() {
        startActivity(new Intent(this, FeedbackActivity.class));
    }

    /**
     * 显示捐赠二维码
     */
    private void showDonateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_donate, null);
        view.findViewById(R.id.qr).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.buildDrawingCache();
                Bitmap bitmap = v.getDrawingCache();
                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/qr.png";
                if (BitmapUtils.saveBitmap(bitmap, path)) {
                    Toast.makeText(MainActivity.this, "图片已保存到"+path, Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
        builder.setView(view);
        builder.setCancelable(true);
        builder.create().show();
    }

    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setMessage(R.string.about);
        builder.setCancelable(true);
        builder.create().show();
    }


}
