package com.example.zezva.ontouch;

import android.os.Handler;
import android.os.Message;

import java.util.TimerTask;

/**
 * Created by Zezva on 07.01.2017.
 */

public class TimeCounterTimerTask extends TimerTask {

    private Handler time_counter_handler;
    Message msg;

    private  int counter = 0 ;
    public TimeCounterTimerTask(Handler time_counter_handler){
        this.time_counter_handler = time_counter_handler;
    }



    @Override
    public void run() {
        msg = Message.obtain();
        counter++ ;
        msg.arg1 = counter;
        time_counter_handler.sendMessage(msg);

    }

    public void setTime_counter_handler(Handler time_counter_handler) {
        this.time_counter_handler = time_counter_handler;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Handler getTime_counter_handler() {

        return time_counter_handler;
    }

    public Message getMsg() {
        return msg;
    }

    public int getCounter() {
        return counter;
    }
}
