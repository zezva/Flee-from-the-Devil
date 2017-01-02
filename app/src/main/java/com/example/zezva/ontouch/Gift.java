package com.example.zezva.ontouch;

import android.os.Handler;
import android.os.Message;

import java.util.TimerTask;

/**
 * Created by Zezva on 30.12.2016.
 */

public class Gift  extends  TimerTask {

    private  boolean isAdded = false ;
    private int second_for_add  = 0 ;
    private  int second_for_remove = 0 ;
    private  int  numberOfBalls = 0 ;
    private  Message msg;
    Handler handler_for_gift ;

    public  Gift(Handler handler_for_gift){
        this.handler_for_gift = handler_for_gift;
    }

    @Override
    public void run() {
        if(!isAdded){
            if(second_for_add==20){
                 msg = Message.obtain();
                msg.arg1 = 1 ;
                second_for_add = 0 ;
                isAdded = true ;
                handler_for_gift.sendMessage(msg);

            }
            else{
                second_for_add++;
            }
        }
        else{
            if(second_for_remove == 5){
                msg = Message.obtain();
                msg.arg1 = 2 ;
                second_for_remove = 0;
                isAdded = false ;
                handler_for_gift.sendMessage(msg);
            }
            else{
                second_for_remove++;
            }
        }


    }


    public void setAdded(boolean added) {
        isAdded = added;
    }

    public void setSecond_for_add(int second_for_add) {
        this.second_for_add = second_for_add;
    }

    public void setSecond_for_remove(int second_for_remove) {
        this.second_for_remove = second_for_remove;
    }

    public void setNumberOfBalls(int numberOfBalls) {
        this.numberOfBalls = numberOfBalls;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }

    public void setHandler_for_gift(Handler handler_for_gift) {
        this.handler_for_gift = handler_for_gift;
    }

    public boolean isAdded() {

        return isAdded;
    }

    public int getSecond_for_add() {
        return second_for_add;
    }

    public int getSecond_for_remove() {
        return second_for_remove;
    }

    public int getNumberOfBalls() {
        return numberOfBalls;
    }

    public Message getMsg() {
        return msg;
    }

    public Handler getHandler_for_gift() {
        return handler_for_gift;
    }
}
