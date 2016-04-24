package com.linjiaxiaohai.rgb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import com.linjiaxiaohai.rgb.view.ColorSeekBar;
import com.umeng.fb.FeedbackAgent;

public class MainActivity extends AppCompatActivity implements ColorView{

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

        seekBarA.setProgress(100);
        seekBarR.setProgress(70);
        seekBarG.setProgress(80);
        seekBarB.setProgress(90);
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
        }

        return super.onOptionsItemSelected(item);
    }

    private void openFeedback() {
        FeedbackAgent agent = new FeedbackAgent(this);
        agent.setWelcomeInfo("hello");
        agent.startFeedbackActivity();
    }
}
