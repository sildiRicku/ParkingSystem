package com.example.system.helperclasses;

public class MutableDouble {
    private double value;

    public MutableDouble(double value) {
        this.value = value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
