package org.beigua.redpackage.utils;


import java.util.Random;

/**
 * Created by beigua on 2017/7/31.
 */
public final class RandomUtils {
    private final static int VARIANCE_DEFAULT = 1;

    private RandomUtils() {
    }

    public static int getRandomWithVariance(int average) {
        Random random = new Random();
        double randomSub = Math.sqrt(VARIANCE_DEFAULT) * random.nextGaussian() + average;

        int randomInt = (int) Math.round(randomSub);
        return randomInt == 0 ? 1 : randomInt;
    }

    public static int getRandomNumber(int average) {
        Random random = new Random();
        //random 0 ~ average*2
        int randomInt = random.nextInt(average * 2);

        return randomInt == 0 ? 1 : randomInt;
    }
}
