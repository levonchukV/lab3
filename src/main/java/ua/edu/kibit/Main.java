package ua.edu.kibit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) {
        int size = 20_000_000;
        double[] array = new double[size];
        double step = 20.0 / size;

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

            long StartTime = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            array[i] = -10 + i * step;
        }

        long startTime = System.currentTimeMillis();


        try {
            int numTasks = Runtime.getRuntime().availableProcessors();
            int chunkSize = (int) Math.ceil((double) size / numTasks);

            for (int i = 0; i < numTasks; i++) {
                final int start = i * chunkSize;
                final int end = Math.min(start + chunkSize, size);

                executor.submit(new CalculatePlotTask(array, start, end, new MyTrigonometricFunction()));
            }
        } finally {
            executor.shutdown();
            while (!executor.isTerminated()) {
                Thread.onSpinWait();
            }
        }


        long EndTime = System.currentTimeMillis();
            System.out.println("Time taken: " + (EndTime - StartTime) + " ms");

        PlotDisplayer.showChart(array, step);

    }
}
