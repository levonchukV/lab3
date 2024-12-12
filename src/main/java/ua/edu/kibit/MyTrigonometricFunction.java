package ua.edu.kibit;

public class MyTrigonometricFunction implements Calculable {

    @Override
    public double calculate(double x){
        return Math.sin(x) * Math.cos(x) + Math.cos(x) * Math.cos(x) + Math.sin(x) * Math.sin(x);
    }
}
