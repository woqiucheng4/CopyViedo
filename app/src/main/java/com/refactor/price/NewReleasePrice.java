package com.refactor.price;

import com.refactor.Movie;

/**
 * Created by  cheng.qiu
 * on: 2019/9/18
 * Description:
 */
public class NewReleasePrice extends Price {

    @Override
    public int getPriceCode() {
        return 1;
    }

    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
    }
}
