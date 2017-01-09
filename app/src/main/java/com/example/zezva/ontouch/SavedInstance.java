package com.example.zezva.ontouch;

import android.graphics.Point;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zezva on 31.12.2016.
 */

public class SavedInstance {

    private  ArrayList<ImageView> image_array ;
    private  ArrayList<Point>  point_array = new ArrayList<>();
    private int numberOfBalls;
    private ArrayList<ImageView> check_cord_image_araay;
    private ImageView myFingerShape ;
    private  Point myFingerShapePoint;
    private  int maxX, maxY;
    private  boolean giftIsAdded ;
    private int gift_second_for_add;
    private int gift_second_for_remove;
    private  int BallAddTimer_second ;
    private  ImageView check_cord_image_for_add ;
    private boolean check_cord_isgiftAdded;
    private boolean chek_cord_ischanged ;
    private ImageView chek_cord_image_for_gift ;
    private  Point image_gift_point ;
    private  boolean iHaveGift ;
    private  ImageView remove_image ;
    private int gift_end_timer;
    private  boolean is_gift_end_on;
    private  int time_counter;
    private TextView time_textView;
    private String time_textView_content ;
    private   ImageView devilCenterImage;
    private  int centerDevilintervall ;
    private  int centerDevilDuration ;


    public void setActuelx(int actuelx) {
        this.actuelx = actuelx;
    }

    public void setActuely(int actuely) {
        this.actuely = actuely;
    }

    public int getActuelx() {

        return actuelx;
    }

    public int getActuely() {
        return actuely;
    }

    /**
     * devilcenteris actualuri kooridnatebi

     */
    private int actuelx, actuely ;

    public void setCenterDevilintervall(int centerDevilintervall) {
        this.centerDevilintervall = centerDevilintervall;
    }

    public void setCenterDevilDuration(int centerDevilDuration) {
        this.centerDevilDuration = centerDevilDuration;
    }

    public int getCenterDevilintervall() {

        return centerDevilintervall;
    }

    public int getCenterDevilDuration() {
        return centerDevilDuration;
    }

    public ImageView getDevilCenterImage() {
        return devilCenterImage;
    }

    public void setDevilCenterImage(ImageView devilCenterImage) {
        this.devilCenterImage = devilCenterImage;
    }

    public String getTime_textView_content() {
        return time_textView_content;
    }



    public void setTime_textView_content(String time_textView_content) {
        this.time_textView_content = time_textView_content;
    }

    public void setTime_textView(TextView time_textView) {
        this.time_textView = time_textView;
    }

    public TextView getTime_textView() {

        return time_textView;
    }

    public void setTime_counter(int time_counter) {
        this.time_counter = time_counter;
    }

    public int getTime_counter() {

        return time_counter;
    }

    public void setIs_gift_end_on(boolean is_gift_end_on) {
        this.is_gift_end_on = is_gift_end_on;
    }

    public boolean is_gift_end_on() {

        return is_gift_end_on;
    }

    public void setGift_end_timer(int gift_end_timer) {
        this.gift_end_timer = gift_end_timer;
    }

    public int getGift_end_timer() {

        return gift_end_timer;
    }

    public void setRemove_image(ImageView remove_image) {
        this.remove_image = remove_image;
    }

    public ImageView getRemove_image() {

        return remove_image;
    }

    public void setiHaveGift(boolean iHaveGift) {
        this.iHaveGift = iHaveGift;
    }

    public boolean isiHaveGift() {

        return iHaveGift;
    }

    public void setImage_gift_point(Point image_gift_point) {
        this.image_gift_point = image_gift_point;
    }

    public Point getImage_gift_point() {

        return image_gift_point;
    }

    private Handler handler_for_animate;
    private Handler handler_for_add_ball;
    private Handler handle_for_add_gift;
    private Handler handle_for_gift_finger_distance;
    private  boolean Timer_for_add_ball_is_on = false;
    private  boolean Timer_for_add_gift_is_on = false ;

    public void setTimer_for_add_ball_is_on(boolean timer_for_add_ball_is_on) {
        Timer_for_add_ball_is_on = timer_for_add_ball_is_on;
    }

    public void setTimer_for_add_gift_is_on(boolean timer_for_add_gift_is_on) {
        Timer_for_add_gift_is_on = timer_for_add_gift_is_on;
    }

    public boolean isTimer_for_add_ball_is_on() {

        return Timer_for_add_ball_is_on;
    }

    public boolean isTimer_for_add_gift_is_on() {
        return Timer_for_add_gift_is_on;
    }

    public int getNumberOfBalls() {
        return numberOfBalls;
    }

    public Point getMyFingerShapePoint() {
        return myFingerShapePoint;
    }

    public ArrayList<ImageView> getImage_array() {
        return image_array;
    }

    public void setPoint_array(ArrayList<Point> point_array) {

        this.point_array = point_array;
    }

    public void setMyFingerShapePoint(Point myFingerShapePoint) {
        this.myFingerShapePoint = myFingerShapePoint;
    }

    public ArrayList<ImageView> getCheck_cord_image_araay() {
        return check_cord_image_araay;
    }

    public ImageView getMyFingerShape() {
        return myFingerShape;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public boolean isGiftIsAdded() {
        return giftIsAdded;
    }

    public int getGift_second_for_add() {
        return gift_second_for_add;
    }

    public int getGift_second_for_remove() {
        return gift_second_for_remove;
    }

    public int getBallAddTimer_second() {
        return BallAddTimer_second;
    }

    public ImageView getCheck_cord_image_for_add() {
        return check_cord_image_for_add;
    }

    public boolean isCheck_cord_isgiftAdded() {
        return check_cord_isgiftAdded;
    }

    public boolean isChek_cord_ischanged() {
        return chek_cord_ischanged;
    }

    public ImageView getChek_cord_image_for_gift() {
        return chek_cord_image_for_gift;
    }

    public void setImage_array(ArrayList<ImageView> image_array) {

        this.image_array = image_array;
    }

    public void setNumberOfBalls(int numberOfBalls) {
        this.numberOfBalls = numberOfBalls;
    }

    public void setCheck_cord_image_araay(ArrayList<ImageView> check_cord_image_araay) {
        this.check_cord_image_araay = check_cord_image_araay;
    }

    public void setMyFingerShape(ImageView myFingerShape) {
        this.myFingerShape = myFingerShape;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public void setGiftIsAdded(boolean giftIsAdded) {
        this.giftIsAdded = giftIsAdded;
    }

    public void setGift_second_for_add(int gift_second_for_add) {
        this.gift_second_for_add = gift_second_for_add;
    }

    public void setGift_second_for_remove(int gift_second_for_remove) {
        this.gift_second_for_remove = gift_second_for_remove;
    }

    public void setBallAddTimer_second(int ballAddTimer_second) {
        BallAddTimer_second = ballAddTimer_second;
    }

    public void setCheck_cord_image_for_add(ImageView check_cord_image_for_add) {
        this.check_cord_image_for_add = check_cord_image_for_add;
    }

    public void setCheck_cord_isgiftAdded(boolean check_cord_isgiftAdded) {
        this.check_cord_isgiftAdded = check_cord_isgiftAdded;
    }

    public void setChek_cord_ischanged(boolean chek_cord_ischanged) {
        this.chek_cord_ischanged = chek_cord_ischanged;
    }

    public void setChek_cord_image_for_gift(ImageView chek_cord_image_for_gift) {
        this.chek_cord_image_for_gift = chek_cord_image_for_gift;
    }
    public ArrayList<Point> getPoint_array() {
        return point_array;
    }

    public void setHandler_for_animate(Handler handler_for_animate) {
        this.handler_for_animate = handler_for_animate;
    }

    public void setHandler_for_add_ball(Handler handler_for_add_ball) {
        this.handler_for_add_ball = handler_for_add_ball;
    }

    public void setHandle_for_add_gift(Handler handle_for_add_gift) {
        this.handle_for_add_gift = handle_for_add_gift;
    }

    public void setHandle_for_gift_finger_distance(Handler handle_for_gift_finger_distance) {
        this.handle_for_gift_finger_distance = handle_for_gift_finger_distance;
    }

    public Handler getHandler_for_animate() {

        return handler_for_animate;
    }

    public Handler getHandler_for_add_ball() {
        return handler_for_add_ball;
    }

    public Handler getHandle_for_add_gift() {
        return handle_for_add_gift;
    }

    public Handler getHandle_for_gift_finger_distance() {
        return handle_for_gift_finger_distance;
    }
}
