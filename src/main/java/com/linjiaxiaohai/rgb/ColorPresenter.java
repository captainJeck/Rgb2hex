package com.linjiaxiaohai.rgb;

import android.graphics.Color;

import java.util.Locale;

/**
 * Created by Meng on 16/4/19.
 */
public class ColorPresenter {

    private ColorView view;

    private int colorA;
    private int colorR;
    private int colorG;
    private int colorB;

    public ColorPresenter(ColorView view) {
        if (view == null) {
            throw new IllegalArgumentException("ColorView is null");
        }
        this.view = view;
    }


    /**
     * 设置透明度
     * @param alpha 0 ～ 100%
     */
    public void alpha(int alpha) {
        if (alpha < 0) alpha = 0;
        if (alpha > 100) alpha = 100;
        colorA = (int)Math.ceil(alpha*2.55);
        calculteColor();
    }

    public void red(int red) {
        colorR = red;
        calculteColor();
    }

    public void green(int green) {
        colorG = green;
        calculteColor();
    }

    public void blue(int blue) {
        colorB = blue;
        calculteColor();
    }

    public void color(ColorType type, int color) {
        switch (type) {
            case ALPHA:
                alpha(color);
                break;
            case RED:
                red(color);
                break;
            case GREEN:
                green(color);
                break;
            case BLUE:
                blue(color);
                break;
        }
    }

    private void calculteColor() {
        String A, R, G, B;
        A = Integer.toHexString(colorA);
        R = Integer.toHexString(colorR);
        G = Integer.toHexString(colorG);
        B = Integer.toHexString(colorB);
        A = A.length() == 1 ? "0" + A : A;
        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;
        int color = Color.argb(colorA, colorR, colorG, colorB);
        String hexColor = String.format(Locale.getDefault(), "#%s%s%s%s", A, R, G, B).toUpperCase(Locale.getDefault());

        view.colorChanged(color, hexColor);
    }
}
