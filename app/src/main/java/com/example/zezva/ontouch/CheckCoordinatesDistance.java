package com.example.zezva.ontouch;


import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.util.ArrayList;


/**
 * Created by Zezva on 29.12.2016.
 */

public class CheckCoordinatesDistance  extends    Thread {


    private ImageView myFingerShape;
    public ArrayList<ImageView> image_array = new ArrayList<>();
    private ImageView  image_for_add;
    private  boolean isChanged = false;
    private  boolean isGiftAdded = false;
    private ImageView Image_fot_gift ;
    private boolean iHaveGift = false ;
    private ImageView remove_image ;

    public void setRemove_image(ImageView remove_image) {
        this.remove_image = remove_image;
    }

    public ImageView getRemove_image() {

        return remove_image;
    }

    Handler handle_for_finger_gift_distance;
    public CheckCoordinatesDistance(ImageView myFingerShape, Handler handle_for_finger_gift_distance) {
        this.handle_for_finger_gift_distance = handle_for_finger_gift_distance;
        this.myFingerShape = myFingerShape;

    }

    @Override
    public void run() {
        while(CoordinateThread.go){


            int  myFingerX = (int) myFingerShape.getX() + myFingerShape.getWidth()/2;
            int  myFingerY = (int) myFingerShape.getY() + myFingerShape.getHeight()/2;
                if(isChanged){
                    image_array.add(image_for_add);
                    isChanged= false ;
                }
            if(remove_image != null){
                if(this.image_array.contains(remove_image)){
                    image_array.remove(remove_image);
                    remove_image=null;
                }
            }
            if(isGiftAdded){
                boolean res = checkFingerGiftDistance(myFingerShape, Image_fot_gift);
                if(res){
                    Message msg = Message.obtain();
                    handle_for_finger_gift_distance.sendMessage(msg);
                    isGiftAdded = false ;
                }
            }
                for (ImageView image : image_array) {
                    int imageX = (int) image.getX() + image.getWidth() / 2;
                    int imageY = (int) image.getY() + image.getHeight() / 2;
                    boolean res = checkDistance(myFingerX, myFingerY, imageX, imageY);
                    if (res && !iHaveGift) {
                        Message msg = Message.obtain();
                        msg.arg1 = -1 ;
                        handle_for_finger_gift_distance.sendMessage(msg);
                        CoordinateThread.go = false;
                    }

            }

        }

    }

    private boolean checkFingerGiftDistance(ImageView myFingerShape, ImageView image_fot_gift) {
        int res   = 0 ;

        int fingerX = (int) myFingerShape.getX() + myFingerShape.getWidth()/2;
        int fingerY = (int) myFingerShape.getY() + myFingerShape.getHeight()/2 ;
        int giftX = (int) image_fot_gift.getX() + image_fot_gift.getWidth()/2;
        int giftY = (int) image_fot_gift.getY() + image_fot_gift.getHeight()/2;

        int first = (int) Math.pow((giftX - fingerX), 2);
        int second = (int) Math.pow((giftY - fingerY), 2);
        res = (int) Math.sqrt(first + second);

        return  res <= myFingerShape.getWidth()/2 + image_fot_gift.getHeight()/2-10;
    }


    private boolean checkDistance(int myFingerX, int myFingerY, int imageX, int imageY) {
        int res = 0 ;
        int first =  (int) Math.pow(( imageX - myFingerX), 2);
        int second = (int) Math.pow( (imageY - myFingerY),2);
        res = (int) Math.sqrt(first + second);
        return  res <= myFingerShape.getWidth()/2+ image_array.get(0).getHeight()/2-10;

    }



    public  void setIsChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }

    public void setImage_for_add(ImageView image_for_add) {
        this.image_for_add = image_for_add;
    }

    public   void add(ImageView image) {
        this.image_array.add(image);
    }

    public void setImage_fot_gift(ImageView image_fot_gift) {
        Image_fot_gift = image_fot_gift;
    }

    public void setGiftAdded(boolean giftAdded) {
        isGiftAdded = giftAdded;
    }

    public void setHandle_for_finger_gift_distance(Handler handle_for_finger_gift_distance) {
        this.handle_for_finger_gift_distance = handle_for_finger_gift_distance;
    }

    public ImageView getMyFingerShape() {
        return myFingerShape;
    }

    public Handler getHandle_for_finger_gift_distance() {
        return handle_for_finger_gift_distance;
    }

    public void setMyFingerShape(ImageView myFingerShape) {

        this.myFingerShape = myFingerShape;
    }

    public void setImage_array(ArrayList<ImageView> image_array) {
        this.image_array = image_array;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }

    public ArrayList<ImageView> getImage_array() {

        return image_array;
    }

    public ImageView getImage_for_add() {
        return image_for_add;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public boolean isGiftAdded() {
        return isGiftAdded;
    }

    public ImageView getImage_fot_gift() {
        return Image_fot_gift;
    }

    public void setiHaveGift(boolean iHaveGift) {
        this.iHaveGift = iHaveGift;
    }

    public boolean isiHaveGift() {

        return iHaveGift;
    }
}
