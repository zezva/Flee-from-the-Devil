package com.example.zezva.ontouch;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.Random;

/**
 * Created by Zezva on 28.12.2016.
 */

public class CoordinateThread extends Thread {


    private Handler handler_for_animate;
    private  int numberOfBalls;
    public  static boolean go = true;
    private  int duration = 1500;
    private int maxX,maxY;

    public  CoordinateThread(Handler handler, int numberOfBalls, int maxX, int maxY){

        this.handler_for_animate = handler;
        this.numberOfBalls = numberOfBalls;
        this.maxX=maxX;
        this.maxY = maxY;
    }

    Random random  = new Random();
    Coordinate coordinate = new Coordinate();
    Bundle bundle = new Bundle();
    int min = 0;
    int x1,y1;
    Point p1;

    @Override
    public void run() {
        while(go){
            for(int  i = 0 ; i< numberOfBalls;  i++){
                x1 = random.nextInt(maxX - min + 1) + min;
                y1 = random.nextInt(maxY - min + 1) + min;
                p1 = new Point();
                p1.set(x1,y1);
                coordinate.add(p1);
            }

            Message msg = Message.obtain();
            bundle.putSerializable("cord", coordinate);
            msg.setData(bundle);
            msg.arg1 = duration;
            handler_for_animate.sendMessage(msg);
            try {
                sleep(1600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }


    public void setHandler_for_animate(Handler handler_for_animate) {
        this.handler_for_animate = handler_for_animate;
    }

    public void setGo(boolean go) {
        this.go = go;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }



    public Handler getHandler_for_animate() {
        return handler_for_animate;
    }

    public int getNumberOfBalls() {
        return numberOfBalls;
    }

    public boolean isGo() {
        return go;
    }

    public int getDuration() {
        return duration;
    }

    public Random getRandom() {
        return random;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public int getMin() {
        return min;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public Point getP1() {
        return p1;
    }



    public void setNumberOfBalls(int numberOfBalls) {
        this.numberOfBalls = numberOfBalls;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
