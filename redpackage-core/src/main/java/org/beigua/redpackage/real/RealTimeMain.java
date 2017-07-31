package org.beigua.redpackage.real;


/**
 * Created by beigua on 2017/7/31.
 */
public class RealTimeMain {
    private final static long DEFAULT_MAX_TIME = 1 * 10 * 1000;//10's 

    public static RedPackage redPackage;
    //用乐观锁实时计算红包金额
    public static void main(String[] args) {
        //todo
        redPackage = RedPackage.builder()
                .name("老板的红包")
                .quantity(3)
                .total(10)
                .expiredTime(DEFAULT_MAX_TIME)
                .build();

        RedPackageConsumer zhangsan = new RedPackageConsumer("zhangsan", redPackage);
        RedPackageConsumer lisi = new RedPackageConsumer("lisi", redPackage);
        RedPackageConsumer wangwu = new RedPackageConsumer("wangwu", redPackage);
        RedPackageConsumer zhaoliu = new RedPackageConsumer("zhaoliu", redPackage);
        RedPackageConsumer sunqi = new RedPackageConsumer("sunqi", redPackage);


        new Thread(zhangsan).start();
        new Thread(lisi).start();
        new Thread(wangwu).start();
        new Thread(zhaoliu).start();
        new Thread(sunqi).start();
    }
}
