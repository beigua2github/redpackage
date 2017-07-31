package org.beigua.redpackage.pre;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by beigua on 2017/7/31.
 */
public class SubRedPackage {
    private int parentId ;
    private int subId;
    private int amount;
    private Date createTime;

    public SubRedPackage(int parentId, int subId, int amount, Date createTime) {
        this.parentId = parentId;
        this.subId = subId;
        this.amount = amount;
        this.createTime = createTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SubRedPackage{" +
                "parentId=" + parentId +
                ", subId=" + subId +
                ", amount=" + amount +
                ", createTime=" + new SimpleDateFormat("HH:mm:ss SSS").format(createTime).toString() +
                '}';
    }
}
