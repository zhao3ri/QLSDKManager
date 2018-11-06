package com.item.domain;

/**
 * Created by engine on 2017/2/21.
 */
public class Dashbord {
    private int totalPay = 0;
    private int currentNewUser;
    private long totalMonthlyNewUser;
    private long totalMonthlyPay;

    public int getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(int totalPay) {
        this.totalPay = totalPay;
    }

    public int getCurrentNewUser() {
        return currentNewUser;
    }

    public void setCurrentNewUser(int currentNewUser) {
        this.currentNewUser = currentNewUser;
    }

    public long getTotalMonthlyNewUser() {
        return totalMonthlyNewUser;
    }

    public void setTotalMonthlyNewUser(long totalMonthlyNewUser) {
        this.totalMonthlyNewUser = totalMonthlyNewUser;
    }

    public long getTotalMonthlyPay() {
        return totalMonthlyPay;
    }

    public void setTotalMonthlyPay(long totalMonthlyPay) {
        this.totalMonthlyPay = totalMonthlyPay;
    }
}
