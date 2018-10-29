package Assignment5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiClientShooter {
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            executorService.submit(new Client(String.valueOf(i)));
        }

    }
}
