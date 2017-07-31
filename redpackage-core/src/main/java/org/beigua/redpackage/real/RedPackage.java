package org.beigua.redpackage.real;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by beigua on 2017/7/31.
 */
public class RedPackage {
    private String name;
    private int id;
    private int total;
    private int quantity;
    private long expiredTime;
    private AtomicInteger remainAmount;
    private AtomicInteger remainQuantity;
    private long createTimeStamp;
    private AtomicInteger version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public long getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(long createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public AtomicInteger getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(AtomicInteger remainAmount) {
        this.remainAmount = remainAmount;
    }

    public AtomicInteger getRemainQuantity() {
        return remainQuantity;
    }

    public void setRemainQuantity(AtomicInteger remainQuantity) {
        this.remainQuantity = remainQuantity;
    }

    public AtomicInteger getVersion() {
        return version;
    }

    public void setVersion(AtomicInteger version) {
        this.version = version;
    }

    public static class Builder {
        private String name;
        private int id;
        private int total;
        private int quantity;
        private long expiredTime;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder total(int total) {
            this.total = total;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder expiredTime(long expiredTime) {
            this.expiredTime = expiredTime;
            return this;
        }

        public RedPackage build() {
            return new RedPackage(this);
        }
    }

    private RedPackage(Builder b) {
        this.name = b.name;
        this.id = b.id;
        this.total = b.total;
        this.quantity = b.quantity;
        this.expiredTime = b.expiredTime;
        this.remainAmount =new AtomicInteger(b.total);
        this.remainQuantity =  new AtomicInteger(b.quantity);
        this.createTimeStamp = System.currentTimeMillis();
        this.version = new AtomicInteger(0);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "RedPackage{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", total=" + total +
                ", quantity=" + quantity +
                ", expiredTime=" + expiredTime +
                ", remainAmount=" + remainAmount +
                ", remainQuantity=" + remainQuantity +
                ", createTimeStamp=" + createTimeStamp +
                '}';
    }
}
