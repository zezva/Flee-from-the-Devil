package com.example.zezva.ontouch;

import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {


    private ViewGroup root;
    private float dX, dY;
    private Button start;
    private ImageView image_view, image_view2, myFingerShape;
    private int numberOfBalls = 2;
    private Coordinate coords;
    private ArrayList<ImageView> image_array;
    private Timer ballAddTimer;
    private BallAddTimer ballAdd_timer_task;
    private int maxX;
    private int maxY;
    private CoordinateThread cordThread;
    private CheckCoordinatesDistance check;
    private Random random = new Random();
    private ImageView image_for_gift;
    boolean isPaused = false;
    private Gift gift_timer_task;
    private Timer timer_for_gift;
    private SavedInstance savedInstance;
    private boolean isGameInPlay = false;
    private boolean Timer_for_add_ball_is_on = false;
    private boolean Timer_for_add_gift_is_on = false;
    private boolean onBackClicked = false;
    private Random giftRandom;
    private int kind;
    private int duration = 1500;
    private IhaveGiftEnd gift_end_timer_task;
    private Timer gift_end_timer;
    private boolean Timer_for_gift_end = false;
    private TextView time_Counter ;
    private Timer timer_time_counter ;
    private TimeCounterTimerTask timer_time_timertask ;

    private  String time_textView_content ;
    private  AudioManager audioManager ;
    private MediaPlayer mp  ;





    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager  = (AudioManager) getSystemService(AUDIO_SERVICE);



        time_Counter = (TextView) findViewById(R.id.time_counter);
        image_for_gift = new ImageView(this);

        root = (ViewGroup) findViewById(R.id.root);
        image_array = new ArrayList<>();

        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(this);

        image_view = new ImageView(this);
        image_view.setImageResource(R.drawable.devilcircle);
        image_array.add(image_view);

        image_view2 = new ImageView(this);
        image_view2.setImageResource(R.drawable.devilcircle);
        image_array.add(image_view2);

        myFingerShape = new ImageView(this);
        myFingerShape.setImageResource(R.drawable.myfingercircle);
        myFingerShape.setOnTouchListener(this);
        root.addView(image_view);
        root.addView(image_view2);

        check = new CheckCoordinatesDistance(myFingerShape, handle_for_gift_finger_distance);
        check.add(image_view);
        check.add(image_view2);


    }

    public void addMyFingerShape() {

        myFingerShape.setX(root.getRight() / 2 - myFingerShape.getWidth());
        myFingerShape.setY(root.getBottom() / 2);
        root.addView(myFingerShape);

    }

    private void getMaxXY() {
        maxX = root.getRight() - image_view.getWidth();
        maxY = root.getBottom() - image_view.getHeight();
    }

    private void scheduleAddBallTimer() {
        Timer_for_add_ball_is_on = true;
        ballAddTimer = new Timer();
        ballAdd_timer_task = new BallAddTimer(handler_for_add_ball);
        ballAddTimer.schedule(ballAdd_timer_task, 0, 1000);

    }

    private void animateImageView(ImageView imageView, int x, int y, int duration) {
        imageView.animate().x(x).y(y).setDuration(duration).start();
    }


    /*
    idzaxebs  methods romelic axdens ballebis animacias
     */
    Handler handler_for_animate = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            coords = (Coordinate) msg.getData().getSerializable("cord");
            for (int i = 0; i < image_array.size(); i++) {
                ImageView image = image_array.get(i);
                Point p = coords.getPoint(i);
                animateImageView(image, p.x, p.y, duration);
            }
            coords.clearArrayList();

        }
    };

    /*
    idzaxebs methods romelic root-shi amatebs Ball-s
     */
    Handler handler_for_add_ball = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            initialiseNewBall();

        }
    };

    /*
    tu 5 wami gavida users artmevs sachukars tu arada
    tick sound midis
     */
    Handler handler_gift_end = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.arg1 == 0){
                Timer_for_gift_end = false;
                duration = 1500;
                check.setiHaveGift(false);
                check.setRemove_image(null);
                mp.release();

            }
            else{
                mp = MediaPlayer.create(getApplicationContext(), R.raw.tick);
                mp.start();
            }

        }
    };

    /**
     * axdens axali Ball-is initialisebas da
     * amatebs root-shi .  tu raodenoba 3is tolia
     * rtavs sachukrebis taimers
     */
    private void initialiseNewBall() {
        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setImageResource(R.drawable.devilcircle);
        image_array.add(imageView);
        if (image_array.size() == 3) {
            scheduleGiftTimer();
        }
        check.setImage_for_add(imageView);
        check.setIsChanged(true);
        root.addView(imageView);
        cordThread.setNumberOfBalls(cordThread.getNumberOfBalls() + 1);

    }

    /*
    sratvs sachukrebis taimers
     */
    private void scheduleGiftTimer() {
        Timer_for_add_gift_is_on = true;
        timer_for_gift = new Timer();
        gift_timer_task = new Gift(handle_for_add_gift);
        timer_for_gift.schedule(gift_timer_task, 0, 1000);
    }

    /*
    sazgvravs romeli sachukari unda daematos root-shi an tu
    sachukris dro gasulia shlis mas root-idan
     */
    Handler handle_for_add_gift = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int arg = msg.arg1;
            if (arg == 1) {
                int x = random.nextInt(maxX - 0 + 1) + 0;
                int y = random.nextInt(maxY - 0 + 1) + 0;
                int gift_kind = random.nextInt(3);
                kind = gift_kind;
                if (gift_kind == 0) {
                    image_for_gift.setImageResource(R.drawable.shield_gift);
                    image_for_gift.setX(x);
                    image_for_gift.setY(y);
                    root.addView(image_for_gift);
                    check.setImage_fot_gift(image_for_gift);
                    check.setGiftAdded(true);

                } else if (gift_kind == 1) {
                    image_for_gift.setImageResource(R.drawable.minus);
                    image_for_gift.setX(x);
                    image_for_gift.setY(y);
                    root.addView(image_for_gift);
                    check.setImage_fot_gift(image_for_gift);
                    check.setGiftAdded(true);
                } else if (gift_kind == 2) {
                    image_for_gift.setImageResource(R.drawable.time_slowdown);
                    image_for_gift.setX(x);
                    image_for_gift.setY(y);
                    root.addView(image_for_gift);
                    check.setImage_fot_gift(image_for_gift);
                    check.setGiftAdded(true);

                }

            } else {
                duration=1500;
                if(image_for_gift != null){

                    root.removeView(image_for_gift);
                }
                check.setGiftAdded(false);
                check.setImage_fot_gift(null);
            }
        }
    };

    /*
    sazgvravs romeli sachukari aigo userma, aketebs sachukris shesabamis actions
    da rtavs shesabamisad sachukris dasrulebis taimers
     */
    Handler handle_for_gift_finger_distance = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (kind == 0) {
                check.setiHaveGift(true);
                scheduleGift_End_Timer();
            } else if (kind == 1) {
                int index = random.nextInt(image_array.size());
                ImageView image = image_array.get(index);
                image_array.remove(image);
                root.removeView(image);
                check.setRemove_image(image);
            } else if (kind == 2) {
                duration = 4000;
                scheduleGift_End_Timer();
            }
            if(image_for_gift != null){

                root.removeView(image_for_gift);
            }

            check.setImage_fot_gift(null);

            Toast.makeText(MainActivity.this, "testest", Toast.LENGTH_SHORT).show();

        }
    };


    /*
    rtavs sachukris dasrulebis taimers
     */
    private void scheduleGift_End_Timer() {
        Timer_for_gift_end = true;
        gift_end_timer_task = new IhaveGiftEnd(handler_gift_end);
        gift_end_timer = new Timer();
        gift_end_timer.schedule(gift_end_timer_task, 0, 1000);
    }

    /*
    svavs wutebsa da wamebs drois textview-ze
     */
    Handler timer_counter_handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int time  = msg.arg1;
            int minute = time/60 ;
            int secunde = time % 60;
            String res = String.valueOf(minute) + "m : " + String.valueOf(secunde) +"sec";
            time_textView_content = res ;

            time_Counter.setText(res);


        }
    };

    /*
    rtavs drois atvlis taimers romelic midis mteli
    tamashis ganmavlobashi
     */
    private  void scheduleTimeCounterTimer(){
        timer_time_timertask  = new TimeCounterTimerTask(timer_counter_handler);
        timer_time_counter  = new Timer();
        timer_time_counter.schedule(timer_time_timertask, 0,1000);
    }


    @Override
    protected void onPause() {
        isPaused = true;
        if (!onBackClicked) {
            Process.killProcess(Process.myPid());
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        onBackClicked = false;
        if (isPaused) {
            if (isGameInPlay) {
                root.removeAllViews();
                ArrayList<ImageView> image_array = savedInstance.getImage_array();
                ArrayList<Point> points = savedInstance.getPoint_array();
                for (int i = 0; i < image_array.size(); i++) {
                    ImageView image = image_array.get(i);
                    Point point = points.get(i);
                    image.setX(point.x);
                    image.setY(point.y);
                    root.addView(image);
                }
                ImageView myFingerShape = savedInstance.getMyFingerShape();
                int x = savedInstance.getMyFingerShapePoint().x;
                int y = savedInstance.getMyFingerShapePoint().y;
                myFingerShape.setX(x);
                myFingerShape.setY(y);
                root.addView(myFingerShape);

                int numberOfBalls = savedInstance.getNumberOfBalls();
                int maxX = savedInstance.getMaxX();
                int maxY = savedInstance.getMaxY();

                cordThread =
                        new CoordinateThread(handler_for_animate, numberOfBalls, maxX, maxY);
                cordThread.setMaxX(maxX);
                cordThread.setMaxY(maxY);
                cordThread.setNumberOfBalls(numberOfBalls);

                check = new CheckCoordinatesDistance(myFingerShape, handle_for_gift_finger_distance);
                ArrayList<ImageView> chek_image_array = savedInstance.getCheck_cord_image_araay();
                ImageView image_for_add = savedInstance.getCheck_cord_image_for_add();
                boolean ischanged = savedInstance.isChek_cord_ischanged();
                boolean isGiftAdded = savedInstance.isGiftIsAdded();

                ImageView image_for_gift = savedInstance.getChek_cord_image_for_gift();
                if (image_for_gift != null) {
                    image_for_gift.setX(savedInstance.getImage_gift_point().x);
                    image_for_gift.setY(savedInstance.getImage_gift_point().y);
                    root.addView(image_for_gift);

                }


                check.setImage_array(chek_image_array);
                check.setImage_for_add(image_for_add);
                check.setIsChanged(ischanged);
                check.setGiftAdded(isGiftAdded);
                check.setImage_fot_gift(image_for_gift);
                check.setHandle_for_finger_gift_distance(handle_for_gift_finger_distance);
                check.setiHaveGift(savedInstance.isiHaveGift());
                check.setRemove_image(savedInstance.getRemove_image());

                boolean Timer_for_add = savedInstance.isTimer_for_add_ball_is_on();
                BallAddTimer ball_add_timer_task = new BallAddTimer(handler_for_add_ball);
                int ballAddTimerSeconde = savedInstance.getBallAddTimer_second();
                ball_add_timer_task.setTimerSecond(ballAddTimerSeconde);

                Gift gift_timer_task = new Gift(handle_for_add_gift);
                int gift_second_for_add = savedInstance.getGift_second_for_add();
                int gift_second_for_remove = savedInstance.getGift_second_for_remove();
                boolean giftisAdded = savedInstance.isGiftIsAdded();
                gift_timer_task.setSecond_for_add(gift_second_for_add);
                gift_timer_task.setSecond_for_remove(gift_second_for_remove);
                gift_timer_task.setAdded(giftisAdded);
                boolean Timer_for_gift = savedInstance.isTimer_for_add_gift_is_on();
                CoordinateThread.go = true;
                cordThread.start();
                check.start();
                if (Timer_for_add) {
                    scheduleBallAddTimer_from_resume(ball_add_timer_task);
                }
                if (Timer_for_gift) {
                    scheduleGiftTimer_from_resume(gift_timer_task);
                }
                boolean is_Timer_for_gift_end = savedInstance.is_gift_end_on();
                if (is_Timer_for_gift_end) {
                    IhaveGiftEnd gift_end_timer_task = new IhaveGiftEnd(handler_gift_end);
                    schedule_gift_end_timer_from_resume(gift_end_timer_task);

                }
                timer_time_timertask = new TimeCounterTimerTask(timer_counter_handler);
                timer_time_timertask.setCounter(savedInstance.getTime_counter());
                schedule_Timer_time_counter_from_resume(timer_time_timertask);

                int time  = savedInstance.getTime_counter() ;
                Toast.makeText(this, String.valueOf(time), Toast.LENGTH_LONG).show();
                int minute = time/60 ;
                int secunde = time % 60;
                String res = String.valueOf(minute) + "m : " + String.valueOf(secunde) +"sec";
               time_Counter.setText(res);
                root.addView(time_Counter);

                isPaused = false;

            }

        }
        super.onResume();
    }

    private void schedule_Timer_time_counter_from_resume(TimeCounterTimerTask timer_time_timertask) {
        timer_time_counter = new Timer();
        timer_time_counter.schedule(timer_time_timertask, 0, 1000);
    }

    private void schedule_gift_end_timer_from_resume(IhaveGiftEnd gift_end_timer_task) {
        gift_end_timer = new Timer();
        gift_end_timer.schedule(gift_end_timer_task, 0, 1000);
    }

    private void scheduleGiftTimer_from_resume(Gift gift_timer_task) {
        timer_for_gift = new Timer();
        timer_for_gift.schedule(gift_timer_task, 0, 1000);

    }

    private void scheduleBallAddTimer_from_resume(BallAddTimer ball_add_timer_task) {
        ballAddTimer = new Timer();
        ballAddTimer.schedule(ball_add_timer_task, 0, 1000);
    }


    @Override
    public void onBackPressed() {
        onBackClicked = true;
        isPaused = true;
        if (!CoordinateThread.go) {
            isPaused = false;
            onBackClicked = false;
            /**
             * es mere unda wavshalo  roca meore aktivitis davamateb  sadac gadava shexebis shemtxvevahsi da
             * daewereba ramdeni  gasuli wamebi
             */
            Process.killProcess(Process.myPid());
        } else {

            if (isGameInPlay) {

                savedInstance = new SavedInstance();

                savedInstance.setTimer_for_add_ball_is_on(Timer_for_add_ball_is_on);
                savedInstance.setTimer_for_add_gift_is_on(Timer_for_add_gift_is_on);

                savedInstance.setTime_textView(time_Counter);





                savedInstance.setImage_array(this.image_array);
                for (ImageView image : image_array) {
                    int x = (int) image.getX();
                    int y = (int) image.getY();
                    Point p = new Point();
                    p.set(x, y);
                    savedInstance.getPoint_array().add(p);
                }

                if (cordThread != null) {
                    savedInstance.setNumberOfBalls(cordThread.getNumberOfBalls());
                }
                savedInstance.setMyFingerShape(myFingerShape);
                Point myFingerShapePoint = new Point();
                myFingerShapePoint.set((int) myFingerShape.getX(), (int) myFingerShape.getY());
                savedInstance.setMyFingerShapePoint(myFingerShapePoint);
                savedInstance.setMaxX(maxX);
                savedInstance.setMaxY(maxY);
                if (check != null) {
                    savedInstance.setGiftIsAdded(check.isGiftAdded());
                    savedInstance.setCheck_cord_image_araay(check.image_array);
                    savedInstance.setCheck_cord_image_for_add(check.getImage_for_add());
                    savedInstance.setChek_cord_ischanged(check.isChanged());
                    savedInstance.setChek_cord_image_for_gift(check.getImage_fot_gift());
                    savedInstance.setiHaveGift(check.isiHaveGift());
                    savedInstance.setRemove_image(check.getRemove_image());
                    savedInstance.setiHaveGift(check.isiHaveGift());
                    Point p = new Point();
                    if (check.getImage_fot_gift() != null) {

                        p.x = (int) check.getImage_fot_gift().getX();
                        p.y = (int) check.getImage_fot_gift().getY();
                        savedInstance.setImage_gift_point(p);
                    }
                }
                if (timer_for_gift != null) {

                    savedInstance.setGift_second_for_add(gift_timer_task.getSecond_for_add());
                    savedInstance.setGift_second_for_remove(gift_timer_task.getSecond_for_remove());
                    timer_for_gift.cancel();
                }
                if (ballAddTimer != null) {
                    savedInstance.setBallAddTimer_second(ballAdd_timer_task.getTimerSecond());
                    ballAddTimer.cancel();

                }
                if (gift_end_timer != null) {
                    savedInstance.setGift_end_timer(gift_end_timer_task.getGift_end_timer());
                    savedInstance.setIs_gift_end_on(Timer_for_gift_end);
                    gift_end_timer.cancel();
                }

                if(timer_time_counter != null){
                    savedInstance.setTime_counter(timer_time_timertask.getCounter());
                    timer_time_counter.cancel();
                }

                CoordinateThread.go = false;
                if (cordThread != null) {

                    cordThread.interrupt();
                }
                if (check != null) {

                    check.interrupt();
                }
            }
        }

        moveTaskToBack(true);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:

                if (!(event.getRawY() + dY < 0) &&
                        !(event.getRawY() + dY > root.getBottom() - myFingerShape.getHeight())
                        && !(event.getRawX() + dX < 0) &&
                        !(event.getRawX() + dX > root.getRight() - myFingerShape.getWidth())) {

                    view.animate()
                            .x(event.getRawX() + dX)
                            .y(event.getRawY() + dY)
                            .setDuration(0)
                            .start();
                }

                break;
            default:
                return false;
        }
        root.invalidate();
        return true;
    }

    @Override
    public void onClick(View view) {
        isGameInPlay = true;
        addMyFingerShape();
        getMaxXY();
        cordThread = new CoordinateThread(handler_for_animate, numberOfBalls, maxX, maxY);
        cordThread.start();
        check.start();
        scheduleAddBallTimer();
        scheduleTimeCounterTimer();
        view.setVisibility(View.INVISIBLE);



    }


}
