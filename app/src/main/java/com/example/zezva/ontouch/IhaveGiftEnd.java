package com.example.zezva.ontouch;

import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;

import java.util.TimerTask;

/**
 * Created by Zezva on 02.01.2017.
 */

public class IhaveGiftEnd extends TimerTask {


    private  int gift_end_timer = 8;
    private Handler handler_gift_end;

    public IhaveGiftEnd(Handler handler_gift_end){
        this.handler_gift_end = handler_gift_end;

    }

    public void setGift_end_timer(int gift_end_timer) {
        this.gift_end_timer = gift_end_timer;
    }

    public void setHandler_gift_end(Handler handler_gift_end) {
        this.handler_gift_end = handler_gift_end;
    }


    @Override
    public void run() {
        if(gift_end_timer==0){
            Message msg = Message.obtain();
            msg.arg1 = gift_end_timer;
            handler_gift_end.sendMessage(msg);
            gift_end_timer = 8;
            this.cancel();

        }
        else{
            Message msg = Message.obtain();
            msg.arg1 = gift_end_timer;
            handler_gift_end.sendMessage(msg);
            gift_end_timer--;

        }
    }


    public int getGift_end_timer() {

        return gift_end_timer;
    }

    public Handler getHandler_gift_end() {
        return handler_gift_end;
    }

}
