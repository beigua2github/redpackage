package org.beigua.redpackage.pre;

/**
 * Created by smzdm on 2017/7/31.
 */
public class RedPackage {
    private String name;
    private int id;
    private int total;
    private int quantity;
    private long expiredTime;
    private int remainAmount;
    private volatile int remainQuantity;
    private long createTimeStamp;

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

    public int getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(int remainAmount) {
        this.remainAmount = remainAmount;
    }

    public int getRemainQuantity() {
        return remainQuantity;
    }

    public void setRemainQuantity(int remainQuantity) {
        this.remainQuantity = remainQuantity;
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
        this.remainAmount =b.total;
        this.remainQuantity =  b.quantity;
        this.createTimeStamp = System.currentTimeMillis();
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
