package com.example.zezva.ontouch;

import android.os.Handler;
import android.os.Message;

import java.util.TimerTask;

/**
 * Created by Zezva on 28.12.2016.
 */

public class BallAddTimer extends TimerTask {

    Handler handler_for_add;
    private  int timerSecond  = 10 ;

    public BallAddTimer(Handler handler){
        this.handler_for_add = handler;


    }
    @Override
    public void run() {
        if(timerSecond==0){
            Message msg = Message.obtain();
            msg.arg1 = 0 ;
            handler_for_add.sendMessage(msg);
            timerSecond = 10 ;
        }
        else{
            timerSecond--;
        }

    }

    public Handler getHandler_for_add() {
        return handler_for_add;
    }

    public void setTimerSecond(int timerSecond) {
        this.timerSecond = timerSecond;
    }

    public int getTimerSecond() {
        return timerSecond;
    }
}
