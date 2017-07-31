package org.beigua.redpackage.pre;

import org.beigua.redpackage.utils.RandomUtils;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

/**
 * Created by smzdm on 2017/7/31.
 */
public class RedPackageProducer implements Runnable {
    private final BlockingQueue<SubRedPackage> queue;
    private final RedPackage redPackage;

    public RedPackageProducer(BlockingQueue<SubRedPackage> queue, RedPackage redPackage) {
        this.queue = queue;
        this.redPackage = redPackage;
        System.out.println(redPackage.getName() + ",金额:" + redPackage.getTotal() + "分,总共:" + redPackage.getQuantity() + "个");
    }

    @Override
    public void run() {

        try {
            for (; ; ) {
                check();
                calculate();
            }
        } catch (RedPackageException r) {
        } catch (InterruptedException e) {
        } finally {
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
    }


    private void calculate() throws InterruptedException {
        int remainQuantity = redPackage.getRemainQuantity();
        int remainAmount = redPackage.getRemainAmount();
        int subAmount;

        //quantity is zero ,return
        if (remainQuantity <= 0) {
            return;
        }

        if (redPackage.getRemainQuantity() == 1 || redPackage.getRemainAmount() == 1) {//only one pkg or last pkg
            redPackage.setRemainQuantity(0);
            redPackage.setRemainAmount(0);
            subAmount = remainAmount;
        } else {//normal calculate
            subAmount = RandomUtils.getRandomWithVariance((remainAmount / remainQuantity));
            redPackage.setRemainAmount(remainAmount - subAmount);
            redPackage.setRemainQuantity(remainQuantity - 1);
        }

        SubRedPackage subRedPackage = new SubRedPackage(redPackage.getId(),
                redPackage.getQuantity() - redPackage.getRemainQuantity(),
                subAmount,
                new Date());

        queue.put(subRedPackage);
    }


}
