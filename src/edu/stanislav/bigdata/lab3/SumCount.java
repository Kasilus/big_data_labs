package edu.stanislav.bigdata.lab3;

public class SumCount {

    private Integer count;
    private Double sum;

    public SumCount() {
        this.count = 0;
        this.sum = 0.0;
    }

    public Integer getCount() {
        return count;
    }

    public void addCount() {
        count++;
    }

    public Double getSum() {
        return sum;
    }

    public void addToSum(Double addToSum) {
        this.sum += addToSum;
    }
}
