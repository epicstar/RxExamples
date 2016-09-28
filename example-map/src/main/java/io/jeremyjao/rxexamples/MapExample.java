package io.jeremyjao.rxexamples;

import rx.Observable;
import rx.Observer;

/**
 *
 * Created by epicstar on 8/28/16.
 * @author Jeremy Jao
 * @author Michael Antonacci
 */
public class MapExample {
    public static void main(String[] args) {
        Observable.just(1, 2, 3)
                .map(i -> {
                    // do not do print statements in map! Only done for tutorial reasons!
                    System.out.println("in map function: " + i);
                    return i * 10;
                }).subscribe(new Observer<Integer>() {

            @Override
            public void onNext(Integer value) {
                System.out.println("result: " + value);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("Complete!");
            }
        });
    }
}
