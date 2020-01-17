package com.refactor.price;

/**
 * Created by  cheng.qiu
 * on: 2019/9/17
 * Description:
 */
public abstract class Price {

    public abstract int getPriceCode();

    public abstract double getCharge(int daysRented);

}