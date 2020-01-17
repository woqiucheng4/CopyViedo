package com.refactor.price;

/**
 * Created by  cheng.qiu
 * on: 2019/9/18
 * Description:
 */
public class ChildrensPrice extends Price {

    @Override
    public int getPriceCode() {
        return 2;
    }

    @Override
    public double getCharge(int daysRented){
        double result = 1.5;
        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }
}
