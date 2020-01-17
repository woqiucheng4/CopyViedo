package com.refactor;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by  cheng.qiu
 * on: 2019/9/17
 * Description:
 */
public class Customer {
    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    public String getResult() {
        Enumeration rentals = _rentals.elements();
        String result =  "\n"+"Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
        }

        result += " Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotaIFrequentRenterPoints()) + " frequent renter points ";
        return result;
    }

    private double getTotalCharge(){
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while(rentals.hasMoreElements()){
            Rental each = (Rental)rentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }

    private double getTotaIFrequentRenterPoints(){
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while(rentals.hasMoreElements()){
            Rental each = (Rental)rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }

}