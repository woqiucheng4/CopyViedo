package com.refactor.price;

/**
 * Created by  cheng.qiu
 * on: 2019/9/18
 * Description:
 */
public class RegularPrice extends Price {

    @Override
    public int getPriceCode() {
        return 0;
    }

    @Override
    public double getCharge(int daysRented){
        double result = 2;
        if (daysRented > 2) {
            result += (daysRented - 2) * 1.5;
        }
        return result;
    }
}
