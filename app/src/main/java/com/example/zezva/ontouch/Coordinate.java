package com.example.zezva.ontouch;

import android.graphics.Point;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Zezva on 28.12.2016.
 */

public class Coordinate  implements Serializable{
    private Point p1, p2 ;
    private ArrayList<Point> array = new ArrayList<>();


    public  Coordinate(){

    }

    public  void add(Point p ){
        this.array.add(p);
    }

    public  void clearArrayList(){
        this.array.clear();
    }

    public Point getPoint(int position){
        if(position>=array.size()){
            return new Point(0,0);
        }
        else{
            return this.array.get(position);
        }

    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }
}
