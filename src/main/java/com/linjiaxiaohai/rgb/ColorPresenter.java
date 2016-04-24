package com.linjiaxiaohai.rgb;

import android.graphics.Color;

import java.util.Locale;

/**
 * Created by Meng on 16/4/19.
 */
public class ColorPresenter {

    private ColorView view;

    private int alpha;
    private int red;
    private int green;
    private int blue;

    public ColorPresenter(ColorView view) {
        if (view == null) {
            throw new IllegalArgumentException("ColorView is null");
        }
        this.view = view;
    }


    public void alpha(int alpha) {
        this.alpha = (int)Math.ceil(alpha*2.55);
        calculteColor();
    }

    public void red(int red) {
        this.red = red;
        calculteColor();
    }

    public void green(int green) {
        this.green = green;
        calculteColor();
    }

    public void blue(int blue) {
        this.blue = blue;
        calculteColor();
    }

    public int getAlpha() {
        return alpha;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    private void calculteColor() {
        String A, R, G, B;
        A = Integer.toHexString(alpha);
        R = Integer.toHexString(red);
        G = Integer.toHexString(green);
        B = Integer.toHexString(blue);
        A = A.length() == 1 ? "0" + A : A;
        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;
        int color = Color.argb(alpha, red, green, blue);
        String hexColor = String.format(Locale.getDefault(), "#%s%s%s%s", A, R, G, B).toUpperCase(Locale.getDefault());

        view.colorChanged(color, hexColor);
    }
}
