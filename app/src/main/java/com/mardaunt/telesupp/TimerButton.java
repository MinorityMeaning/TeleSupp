package com.mardaunt.telesupp;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.widget.Button;

public class TimerButton extends CountDownTimer {

    private final Button button;
    private boolean activeFlag = true;
    private MainActivity mainActivity;

    TimerButton(Button button, MainActivity mainActivity) {
        super(8000,1000);
        this.button = button;
        this.mainActivity = mainActivity;
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onTick(long millisUntilFinished) {
        if (activeFlag) {
            button.setClickable(false);
            activeFlag = false;
        }
        button.setText(mainActivity.getString(R.string.pause) + " " + millisUntilFinished/1000 + " " + mainActivity.getString(R.string.sec));
    }

    @Override
    public void onFinish() {
        button.setClickable(true);
        button.setText(R.string.send);
    }
}

