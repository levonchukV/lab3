package ua.edu.kibit;

public class CalculatePlotTask implements Runnable {

    private final double[] array;
    private final int start;
    private final int end;
    private final Calculable function;

    public CalculatePlotTask(double[] array, int start, int end, Calculable function) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.function = function;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            array[i] = function.calculate(array[i]);
        }
    }
}
