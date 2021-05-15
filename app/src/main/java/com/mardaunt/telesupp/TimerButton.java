package com.mardaunt.telesupp;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.widget.Button;

public class TimerButton extends CountDownTimer {

    private final Button button;
    private boolean colorFlag = true;

    TimerButton(Button button) {
        super(6000,1000);
        this.button = button;
    }


    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onTick(long millisUntilFinished) {
        if (colorFlag) {button.setBackgroundColor(R.color.off_button);
            button.setClickable(false);
            colorFlag = false;
        }
        button.setText("Пауза " + millisUntilFinished/1000 + " сек");
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onFinish() {
        button.setClickable(true);
        button.setBackgroundColor(R.color.purple_700); // Не работает, почему?
        button.setText(R.string.send);
    }
}

