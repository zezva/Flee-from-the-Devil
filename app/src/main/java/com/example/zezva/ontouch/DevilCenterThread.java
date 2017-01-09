package com.example.zezva.ontouch;

import android.os.Handler;
import android.os.Message;
import android.os.NetworkOnMainThreadException;

import java.util.Random;
import java.util.TimerTask;

/**
 * Created by Zezva on 09.01.2017.
 */

public class DevilCenterThread extends Thread {

    private  int intervall = 10 ;
    private  int duration = 10 ;
    private Handler devilCenterHandler ;
    private int maxX,  maxY,  x,y ;
    private boolean intervallIsOver = false;
    Random random = new Random();

    private  int min = 0 ;

    public DevilCenterThread(Handler devilCenterHandler, int maxX, int maxY, int x, int y){
        this.devilCenterHandler  =devilCenterHandler;
        this.maxX = maxX;
        this.maxY = maxY;
        this.x= x;
        this.y=y;
    }

    @Override
    public void run(){

        while(CoordinateThread.go){


           if(intervall==0){
               intervallIsOver = true;
           }
            else{
               try {
                   sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               intervall--;
           }
            if(intervallIsOver){
                if(duration == 0){
                    Message message = Message.obtain();
                    message.arg1 = -1;
                    devilCenterHandler.sendMessage(message);
                    intervall = 10 ;
                    intervallIsOver = false;
                    duration =10 ;
                }
              else  if(duration==2){
                    Message message = Message.obtain();
                    message.arg1 = x;
                    message.arg2 = y;
                    devilCenterHandler.sendMessage(message);
                    try {
                        sleep(1600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else if(duration > 2){
                    int x1 = random.nextInt(maxX - min + 1) + min;
                    int y1 = random.nextInt(maxY - min + 1) + min;
                    Message message = Message.obtain();
                    message.arg1 =x1;
                    message.arg2 =y1;
                    devilCenterHandler.sendMessage(message);
                    try {
                        sleep(1600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                duration--;


            }

        }

    }



    public void setIntervall(int intervall) {
        this.intervall = intervall;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDevilCenterHandler(Handler devilCenterHandler) {
        this.devilCenterHandler = devilCenterHandler;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setIntervallIsOver(boolean intervallIsOver) {
        this.intervallIsOver = intervallIsOver;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMin() {

        return min;
    }

    public int getIntervall() {
        return intervall;
    }

    public int getDuration() {
        return duration;
    }

    public Handler getDevilCenterHandler() {
        return devilCenterHandler;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isIntervallIsOver() {
        return intervallIsOver;
    }

    public Random getRandom() {
        return random;
    }
}
