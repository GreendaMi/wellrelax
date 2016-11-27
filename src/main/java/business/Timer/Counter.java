package business.Timer;

import java.util.HashMap;

/**
 * Created by zhaopy on 2015/11/18.
 */
public class Counter{

    static class CountableState {

        int count;
    }


    //单例模式
    private static Counter counter;
    public static Counter getInstance() {
        if (counter == null) {
            counter = new Counter();
        }
        return counter;
    }


    private HashMap<ICountable, CountableState> states = new HashMap<ICountable, CountableState>();

    //为每一个任务（使用同一个计时器）添加计数器，并启动任务instance.onCountIncreasesToOne();
    //同一个TickTimer，调用多次start()的情况，计数器会增加
    public void increase(ICountable instance) {

        CountableState state = states.get(instance);
        if (state == null) {
            state = new CountableState();
            state.count = 1;
            states.put(instance, state);
            instance.onCountIncreasesToOne();
        }
        else {
            state.count++;
        }
    }

    //结束任务，计数器-1，如果是最后一个线程，就结束instance.onCountDecreasesToZero();;
    public void decrease(ICountable instance) {

        CountableState state = states.get(instance);
        state.count--;
        if (state.count == 0) {
            states.remove(instance);
            instance.onCountDecreasesToZero();
        }
    }

    public int getCount(ICountable instance) {
        CountableState state = states.get(instance);
        return state == null ? 0 : state.count;
    }
}
