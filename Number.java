package com.tsystems.javaschool.tasks;

public final class Number implements Expression {

    private double num;

    public Number(double num) {
        this.num = num;
    } 

    public double evaluate() {
        return num;
    }
}