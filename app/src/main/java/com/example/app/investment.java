package com.example.app;

/**
 * priceAtPurchase : price at time of buy
 * priceNow : total amount
 * units : number of units allotted that time
 * type : {
 *      "11" : "short term beer"
 *      "12" : "short term wine"
 *
 *      "21" : "mid term wine"
 *      "22" : "mid term beer"
 *
 *      "31" : "long term wine"
 *      "32" : :long term beer"
 * }
 * */
public class investment {
    private double priceAtPurchase , priceNow , units;
    private int tenure; // in years
    private int type;

    public investment(double priceAtPurchase, double priceNow, double units, int tenure,int type) {
        this.priceAtPurchase = priceAtPurchase;
        this.priceNow = priceNow;
        this.units = units;
        this.tenure = tenure;
        this.type = type;
    }
    public investment(){}

    public double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    public double getPriceNow() {
        return priceNow;
    }

    public void setPriceNow(double priceNow) {
        this.priceNow = priceNow;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }

    public int getTenure() {
        return tenure;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
