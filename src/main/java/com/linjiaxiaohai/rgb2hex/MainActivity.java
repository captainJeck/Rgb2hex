package com.linjiaxiaohai.rgb2hex;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.linjiaxiaohai.rgb2hex.view.ColorSeekBar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private LinearLayout background;
    private ColorSeekBar seekBarR;
    private ColorSeekBar seekBarG;
    private ColorSeekBar seekBarB;
    private TextView colorView;
    private TextView colorTextView;

    private int colorR;
    private int colorG;
    private int colorB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background = (LinearLayout) findViewById(R.id.background);
        colorTextView = (TextView) findViewById(R.id.text);
        colorView = (TextView) findViewById(R.id.color);
        seekBarR = (ColorSeekBar) findViewById(R.id.color_r);
        seekBarG = (ColorSeekBar) findViewById(R.id.color_g);
        seekBarB = (ColorSeekBar) findViewById(R.id.color_b);

        seekBarR.setHint("R");
        seekBarG.setHint("G");
        seekBarB.setHint("B");
        seekBarR.setOnSeekBarChangeListener(new ColorSeekBarChangeListener(0));
        seekBarG.setOnSeekBarChangeListener(new ColorSeekBarChangeListener(1));
        seekBarB.setOnSeekBarChangeListener(new ColorSeekBarChangeListener(2));

        seekBarR.setProgress(10);
        seekBarG.setProgress(20);
        seekBarB.setProgress(30);
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
        String R, G, B;
        R = Integer.toHexString(colorR);
        G = Integer.toHexString(colorG);
        B = Integer.toHexString(colorB);
        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;
        colorTextView.setText(String.format(Locale.getDefault(), "#%s%s%s", R, G, B).toUpperCase(Locale.getDefault()));
        colorView.setBackgroundColor(Color.rgb(colorR, colorG, colorB));
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
