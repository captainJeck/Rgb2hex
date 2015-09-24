package com.kaolafm.rgb2hex;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private LinearLayout background;
    private SeekBar seekBarR;
    private SeekBar seekBarG;
    private SeekBar seekBarB;
    private TextView colorView;

    private int colorR;
    private int colorG;
    private int colorB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background = (LinearLayout) findViewById(R.id.background);
        colorView = (TextView) findViewById(R.id.text);
        seekBarR = (SeekBar) findViewById(R.id.color_r);
        seekBarG = (SeekBar) findViewById(R.id.color_g);
        seekBarB = (SeekBar) findViewById(R.id.color_b);

        seekBarR.setOnSeekBarChangeListener(new ColorSeekBarChangeListener(0));
        seekBarG.setOnSeekBarChangeListener(new ColorSeekBarChangeListener(1));
        seekBarB.setOnSeekBarChangeListener(new ColorSeekBarChangeListener(2));
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
                    colorR = i;
                    break;
                case 1:
                    colorG = i;
                    break;
                case 2:
                    colorB = i;
                    break;
            }
            notifyBackground();
        }

        @Override
        public void onStartTrackingTouch(SeekBar bar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar bar) {

        }
    }

    private void notifyBackground() {
        String color = String.format(Locale.getDefault(), "%s%s%s", Integer.toHexString(colorR), Integer.toHexString(colorG), Integer.toHexString(colorB));
        colorView.setText(color);
        background.setBackgroundColor(Color.rgb(colorR, colorG, colorB));
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
