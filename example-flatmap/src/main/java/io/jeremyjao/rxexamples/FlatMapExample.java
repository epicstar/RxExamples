package io.jeremyjao.rxexamples;

import rx.Observable;
import rx.Observer;
import rx.observers.Observers;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Flatmap example that delays iteration of the split string
 *
 * Created by epicstar on 8/28/16.
 * @author Jeremy Jao
 * @author Michael Antonacci
 */
public class FlatMapExample {
    private final static String[] input = new String[] {"why hello there","how are you","doing today?"};

    public static void main(String[] args) {

        Observable<String> phrases = Observable.from(input);

        //noinspection Convert2MethodRef
        Observable<String> words = phrases
                .flatMap(s -> splitDelay(s));

        //noinspection Convert2MethodRef
        Observer<String> obs = Observers.create(
            result -> System.out.println("Result: " + result),
            error -> error.printStackTrace(),
            () -> System.out.println("Complete... exiting explicitly")
        );

        words.subscribe(obs);
        (new Scanner(System.in)).nextLine();
    }

    /**
     * split string into words and emit one word per second
     * Don't worry about the implementation
     * @param str a string
     * @return an observable that emits one word per second
     */
    private static Observable<String> splitDelay(String str) {
        return Observable
                .from(str.split(" "))
                .zipWith(Observable.interval(1, TimeUnit.SECONDS),(r, i) -> r);
    }

}
