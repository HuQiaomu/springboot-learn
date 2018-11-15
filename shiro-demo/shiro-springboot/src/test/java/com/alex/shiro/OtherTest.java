package com.alex.shiro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wh1507006
 * @date 2018/11/14 10:58
 */
public class OtherTest {

    public static void main(String[] args) {
        String str = "abc";
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> System.out.println("1" + str));

        executorService.execute(() ->  System.out.println("2" + str));

        executorService.execute(() -> System.out.println("3" + str));
    }
}
