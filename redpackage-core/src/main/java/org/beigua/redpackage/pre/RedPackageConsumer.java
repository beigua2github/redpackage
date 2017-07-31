package org.beigua.redpackage.pre;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by smzdm on 2017/7/31.
 */
public class RedPackageConsumer implements Runnable {
    private String name;
    private BlockingQueue<SubRedPackage> queue;

    public RedPackageConsumer(String name, BlockingQueue<SubRedPackage> queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {

            SubRedPackage subRedPackage = queue.poll(1, TimeUnit.SECONDS);

            if (subRedPackage != null) {
                System.out.println(name + "抢到了!" + subRedPackage.getAmount() + "分钱!");
            } else {
                System.out.println(name + "没抢到!");
                System.exit(1);
            }
        } catch (RedPackageException r) {

        } catch (InterruptedException e) {

        } finally {
        }
    }
}
