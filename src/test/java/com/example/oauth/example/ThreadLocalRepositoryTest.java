package com.example.oauth.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;

class ThreadLocalRepositoryTest {

    private final ThreadLocalRepository repository = new ThreadLocalRepository();


    @Test
    @DisplayName("")
    void m() throws Exception {
        Runnable resultA = () -> {
            repository.getConnectionThreadLocal("resultA");
        };

        Runnable resultB = () -> {
            repository.getConnectionThreadLocal("resultB");
        };

        Runnable resultC = () -> {
            repository.getConnectionThreadLocal("resultC");
        };

        Thread threadA = new Thread(resultA);
        threadA.setName("threadA");

        Thread threadB = new Thread(resultB);
        threadB.setName("threadB");

        Thread threadC = new Thread(resultC);
        threadC.setName("threadC");

        threadA.start();
        sleep(200);
        threadB.start();
        sleep(1000);
        threadC.start();
        sleep(3000);
    }

    @Test
    @DisplayName("ddd")
    void m2() throws Exception {
        Runnable resultA = () -> {
            repository.getConnectionNormal("resultA");
        };

        Runnable resultB = () -> {
            repository.getConnectionNormal("resultB");
        };

        Runnable resultC = () -> {
            repository.getConnectionNormal("resultC");
        };

        Thread threadA = new Thread(resultA);
        threadA.setName("threadA");

        Thread threadB = new Thread(resultB);
        threadB.setName("threadB");

        Thread threadC = new Thread(resultC);
        threadC.setName("threadC");

        threadA.start();
        sleep(200);
        threadB.start();
        sleep(1000);
        threadC.start();
        sleep(2500);
    }
}
