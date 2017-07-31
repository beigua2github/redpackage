package org.beigua.redpackage.real;

import org.beigua.redpackage.pre.RedPackageException;
import org.beigua.redpackage.pre.SubRedPackage;
import org.beigua.redpackage.utils.RandomUtils;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by beigua on 2017/7/31.
 */
public class RedPackageConsumer implements Runnable {
    private String name;
    private AtomicBoolean isGrab = new AtomicBoolean(false);
    private RedPackage redPackage;

    public RedPackageConsumer(String name, RedPackage redPackage) {
        this.name = name;
        this.redPackage = redPackage;
    }

    @Override
    public void run() {
        try {
            for (; ; ) {
                check();
                calculate();
            }
        } catch (RedPackageException r) {
        }
    }

    private void calculate() {
        int version = redPackage.getVersion().get();
        int remainQuantity = redPackage.getRemainQuantity().get();
        int remainAmount = redPackage.getRemainAmount().get();
        int targetRemainQuantity;
        int targetRemainAmount;

        int subAmount;

        //quantity is zero ,return
        if (remainQuantity <= 0) {
            throw new RedPackageException("The redPackage is empty");
        }

        if (remainQuantity == 1 || remainAmount == 1) {//only one pkg or last pkg
            subAmount = remainAmount;
            targetRemainAmount = 0;
            targetRemainQuantity = 0;
        } else {//normal calculate
            subAmount = RandomUtils.getRandomWithVariance((remainAmount / remainQuantity));
            targetRemainAmount = remainAmount - subAmount;
            targetRemainQuantity = remainQuantity - 1;
        }

        if (compareAndSet(version) && redPackage.getRemainQuantity().compareAndSet(remainQuantity, targetRemainQuantity)
                && redPackage.getRemainAmount().compareAndSet(remainAmount, targetRemainAmount)) {
            System.out.println(name + "抢到了" + subAmount + "分钱!");
        }
    }

    private void check() {
        //check expire
        if (System.currentTimeMillis() > redPackage.getCreateTimeStamp() + redPackage.getExpiredTime()) {
            throw new RedPackageException("The redPackage is expired!");
        }

        if (redPackage.getTotal() < redPackage.getQuantity()) {
            throw new RedPackageException("The redPackage is too small!");
        }

        if (redPackage.getQuantity() <= 0) {
            throw new RedPackageException("There are no more redPackages!");
        }

        if (isGrab.get()) {
            throw new RedPackageException();
        }
    }

    private boolean compareAndSet(int expect) {
        if (redPackage.getVersion().compareAndSet(expect, expect + 1)) {
            isGrab.set(true);
        }
        return isGrab.get();
    }
}
