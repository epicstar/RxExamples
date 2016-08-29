package io.jeremyjao.rxexamples;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Flatmap example that delays iteration of the split string
 *
 * Created by epicstar on 8/28/16.
 */
public class FlatMapExample {
    private final static String input = "why hello there";

    public static void main(String[] args) {

        //noinspection Convert2MethodRef
        Observable.just(input)
                .map(st -> st.split(" "))
                .flatMap(s -> splitDelay(s))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate())
                .subscribe(
                        result -> System.out.println("Result: " + result),
                        error -> error.printStackTrace(),
                        () -> {
                            System.out.println("Complete... exiting explicitly");
                            System.exit(0);
                        }
                );
        //noinspection InfiniteLoopStatement,StatementWithEmptyBody
        while (true) {}
    }

    /**
     * Iterates over spl every 1 second
     * @param spl a string
     * @return an observable that emits something in spl every 1 second
     */
    private static Observable<? extends String> splitDelay(String[] spl) {
        return Observable
                .from(spl)
                .zipWith(Observable.interval(1, TimeUnit.SECONDS), (r, x) -> r);
    }

}
