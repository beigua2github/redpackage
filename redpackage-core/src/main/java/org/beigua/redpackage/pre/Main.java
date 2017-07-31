package org.beigua.redpackage.pre;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by beigua on 2017/7/31.
 */
public class Main {
    private final static long DEFAULT_MAX_TIME = 1 * 10 * 1000;//10's 

    public static void main(String[] args) {
        RedPackage redPackage = RedPackage.builder().name("大涛的红包").total(100).quantity(3).expiredTime(DEFAULT_MAX_TIME).build();

        BlockingQueue<SubRedPackage> queue = new LinkedBlockingQueue<SubRedPackage>(redPackage.getQuantity());
        RedPackageProducer redPackageProducer = new RedPackageProducer(queue, redPackage);

        RedPackageConsumer tao_zong = new RedPackageConsumer("涛总", queue);
        RedPackageConsumer xiao_zong = new RedPackageConsumer("肖总", queue);
        RedPackageConsumer lao_duan = new RedPackageConsumer("段总", queue);
        RedPackageConsumer huang_zong = new RedPackageConsumer("黄总", queue);
        RedPackageConsumer chen_zong = new RedPackageConsumer("陈总", queue);


        try {
            new Thread(redPackageProducer).start();
            new Thread(tao_zong).start();
            new Thread(xiao_zong).start();
            new Thread(lao_duan).start();
            new Thread(chen_zong).start();
            new Thread(huang_zong).start();

        } catch (RedPackageException re) {
            System.out.println(re.getMessage());
        }


    }
}
