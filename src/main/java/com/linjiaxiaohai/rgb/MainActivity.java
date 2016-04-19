package com.linjiaxiaohai.rgb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import com.linjiaxiaohai.rgb.view.ColorSeekBar;
import com.linjiaxiaohai.rgb2hex.R;

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

        seekBarA.setOnSeekBarChangeListener(new ColorSeekBarChangeListener(0));
        seekBarR.setOnSeekBarChangeListener(new ColorSeekBarChangeListener(1));
        seekBarG.setOnSeekBarChangeListener(new ColorSeekBarChangeListener(2));
        seekBarB.setOnSeekBarChangeListener(new ColorSeekBarChangeListener(3));

        seekBarA.setProgress(100);
        seekBarR.setProgress(70);
        seekBarG.setProgress(80);
        seekBarB.setProgress(90);
    }


    class ColorSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        private int color;

        public ColorSeekBarChangeListener(int color) {
            this.color = color;
        }

        @Override
        public void onProgressChanged(SeekBar bar, int i, boolean b) {
            switch (color) {
                case 0:
                    colorPresenter.alpha(i);
                    break;
                case 1:
                    colorPresenter.red(i);
                    break;
                case 2:
                    colorPresenter.green(i);
                    break;
                case 3:
                    colorPresenter.blue(i);
                    break;
            }
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_feedback) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
