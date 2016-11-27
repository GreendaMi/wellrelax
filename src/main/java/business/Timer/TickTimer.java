package business.Timer;

import android.os.Handler;

/**
 * 计时器
 * Created by zhaopy on 2015/11/18.
 */
public class TickTimer implements ICountable {

    /**
     * 计时器对外接口，每隔一段时间，执行某一任务
     * timer = new TickTimer(TimeSpan.fromSeconds(0.05f), new Runnable() {
     * @Override
     *     public void run() {
     *         //任务
     *        }
     *
     *     });
     *    timer.start();
     * @param duration 时间间隔
     * @param runnable 任务进程
     */
    public TickTimer(TimeSpan duration, Runnable runnable) {
        this.duration = duration;
        this.runnable = runnable;
    }


    private static int nextSessionID;

    private int sessionID;

    private TimeSpan duration;

    private boolean isRunning;
    public boolean isRunning() {
        return isRunning;
    }

    private Runnable runnable;

    private static Handler handler = new Handler();

    private Runnable onTickRunnable;


    //同一个TickTimer对象，对此调用此方法，onCountIncreasesToOne()只会被执行1次（第一次）
    public void start() {
        Counter.getInstance().increase(this);
    }

    public void stop() {
        Counter.getInstance().decrease(this);
    }

    private void onTick() {

        if (runnable != null) {
            runnable.run();
        }
    }

    @Override
    public void onCountIncreasesToOne() {

        sessionID = nextSessionID;
        nextSessionID++;

        onTickRunnable = new Runnable() {

            public int sessionID = TickTimer.this.sessionID;

            public void run() {
                onTick();
                if (isRunning && sessionID == TickTimer.this.sessionID) {
                    //再次执行本线程，使onTick()里面的线程任务达到循环执行的效果
                    handler.postDelayed(onTickRunnable, duration.getMilliseconds());
                }
            }
        };

        //线程开关
        isRunning = true;
        //启动线程
        onTickRunnable.run();
    }

    @Override
    public void onCountDecreasesToZero() {
        isRunning = false;
    }
}
