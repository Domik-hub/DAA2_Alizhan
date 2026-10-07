import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class Benchmark {
    public static void main(String[] args) throws Exception {
        int[] sizes = {100, 1000, 10000, 100000};
        PrintWriter writer = new PrintWriter(new FileWriter("results.csv"));
        writer.println("workload,variant,structure,n,time_ms,steps,moves,comparisons");

        for (int n : sizes) {
            runWorkload1(n, writer);
            runWorkload2(n, writer);
            runWorkload3(n, "head", writer);
            runWorkload3(n, "middle", writer);
            runWorkload4(n, writer);
        }
        writer.close();
    }

    private static void runWorkload1(int n, PrintWriter writer) {
        Random rand = new Random(42);
        int[] data = new int[n];
        for (int i = 0; i < n; i++) data[i] = rand.nextInt();

        long totalTimeDA = 0, totalStepsDA = 0, totalMovesDA = 0, totalCompDA = 0;
        for (int run = 0; run < 6; run++) {
            DynamicArray da = new DynamicArray();
            for (int val : data) da.add(val);
            da.resetCounters();
            long start = System.nanoTime();
            for (int i = 0; i < 10000; i++) {
                da.get(Math.abs(rand.nextInt()) % n);
            }
            long time = System.nanoTime() - start;
            if (run > 0) {
                totalTimeDA += time;
                totalStepsDA += da.steps;
                totalMovesDA += da.moves;
                totalCompDA += da.comparisons;
            }
        }
        writer.printf("W1,none,DynamicArray,%d,%d,%d,%d,%d\n", n, totalTimeDA / 5 / 1000000, totalStepsDA / 5, totalMovesDA / 5, totalCompDA / 5);

        long totalTimeLL = 0, totalStepsLL = 0, totalMovesLL = 0, totalCompLL = 0;
        for (int run = 0; run < 6; run++) {
            MyLinkedList ll = new MyLinkedList();
            for (int val : data) ll.add(val);
            ll.resetCounters();
            long start = System.nanoTime();
            for (int i = 0; i < 10000; i++) {
                ll.get(Math.abs(rand.nextInt()) % n);
            }
            long time = System.nanoTime() - start;
            if (run > 0) {
                totalTimeLL += time;
                totalStepsLL += ll.steps;
                totalMovesLL += ll.moves;
                totalCompLL += ll.comparisons;
            }
        }
        writer.printf("W1,none,MyLinkedList,%d,%d,%d,%d,%d\n", n, totalTimeLL / 5 / 1000000, totalStepsLL / 5, totalMovesLL / 5, totalCompLL / 5);
    }

    private static void runWorkload2(int n, PrintWriter writer) {
        Random rand = new Random(42);
        int[] data = new int[n];
        for (int i = 0; i < n; i++) data[i] = rand.nextInt();

        int[] queries = new int[1000];
        for (int i = 0; i < 500; i++) queries[i] = data[Math.abs(rand.nextInt()) % n];
        for (int i = 500; i < 1000; i++) queries[i] = -999999;

        long totalTimeDA = 0, totalStepsDA = 0, totalMovesDA = 0, totalCompDA = 0;
        for (int run = 0; run < 6; run++) {
            DynamicArray da = new DynamicArray();
            for (int val : data) da.add(val);
            da.resetCounters();
            long start = System.nanoTime();
            for (int q : queries) da.contains(q);
            long time = System.nanoTime() - start;
            if (run > 0) {
                totalTimeDA += time;
                totalStepsDA += da.steps;
                totalMovesDA += da.moves;
                totalCompDA += da.comparisons;
            }
        }
        writer.printf("W2,none,DynamicArray,%d,%d,%d,%d,%d\n", n, totalTimeDA / 5 / 1000000, totalStepsDA / 5, totalMovesDA / 5, totalCompDA / 5);

        long totalTimeLL = 0, totalStepsLL = 0, totalMovesLL = 0, totalCompLL = 0;
        for (int run = 0; run < 6; run++) {
            MyLinkedList ll = new MyLinkedList();
            for (int val : data) ll.add(val);
            ll.resetCounters();
            long start = System.nanoTime();
            for (int q : queries) ll.contains(q);
            long time = System.nanoTime() - start;
            if (run > 0) {
                totalTimeLL += time;
                totalStepsLL += ll.steps;
                totalMovesLL += ll.moves;
                totalCompLL += ll.comparisons;
            }
        }
        writer.printf("W2,none,MyLinkedList,%d,%d,%d,%d,%d\n", n, totalTimeLL / 5 / 1000000, totalStepsLL / 5, totalMovesLL / 5, totalCompLL / 5);
    }

    private static void runWorkload3(int n, String variant, PrintWriter writer) {
        Random rand = new Random(42);
        int[] data = new int[n];
        for (int i = 0; i < n; i++) data[i] = rand.nextInt();

        int index = variant.equals("head") ? 0 : n / 2;

        long totalTimeDA = 0, totalStepsDA = 0, totalMovesDA = 0, totalCompDA = 0;
        for (int run = 0; run < 6; run++) {
            DynamicArray da = new DynamicArray();
            for (int val : data) da.add(val);
            da.resetCounters();
            long start = System.nanoTime();
            for (int i = 0; i < 1000; i++) {
                da.add(index, i);
                da.remove(index);
            }
            long time = System.nanoTime() - start;
            if (run > 0) {
                totalTimeDA += time;
                totalStepsDA += da.steps;
                totalMovesDA += da.moves;
                totalCompDA += da.comparisons;
            }
        }
        writer.printf("W3,%s,DynamicArray,%d,%d,%d,%d,%d\n", variant, n, totalTimeDA / 5 / 1000000, totalStepsDA / 5, totalMovesDA / 5, totalCompDA / 5);

        long totalTimeLL = 0, totalStepsLL = 0, totalMovesLL = 0, totalCompLL = 0;
        for (int run = 0; run < 6; run++) {
            MyLinkedList ll = new MyLinkedList();
            for (int val : data) ll.add(val);
            ll.resetCounters();
            long start = System.nanoTime();
            for (int i = 0; i < 1000; i++) {
                ll.add(index, i);
                ll.remove(index);
            }
            long time = System.nanoTime() - start;
            if (run > 0) {
                totalTimeLL += time;
                totalStepsLL += ll.steps;
                totalMovesLL += ll.moves;
                totalCompLL += ll.comparisons;
            }
        }
        writer.printf("W3,%s,MyLinkedList,%d,%d,%d,%d,%d\n", variant, n, totalTimeLL / 5 / 1000000, totalStepsLL / 5, totalMovesLL / 5, totalCompLL / 5);
    }

    private static void runWorkload4(int n, PrintWriter writer) {
        Random rand = new Random(42);
        int[] data = new int[n];
        for (int i = 0; i < n; i++) data[i] = rand.nextInt();

        long totalTime = 0, totalSteps = 0, totalMoves = 0, totalComp = 0;
        for (int run = 0; run < 6; run++) {
            MinHeap heap = new MinHeap(n);
            for (int val : data) heap.insert(val);
            heap.resetCounters();
            long start = System.nanoTime();
            for (int i = 0; i < n; i++) {
                heap.extractMin();
            }
            long time = System.nanoTime() - start;
            if (run > 0) {
                totalTime += time;
                totalSteps += heap.steps;
                totalMoves += heap.moves;
                totalComp += heap.comparisons;
            }
        }
        writer.printf("W4,none,MinHeap,%d,%d,%d,%d,%d\n", n, totalTime / 5 / 1000000, totalSteps / 5, totalMoves / 5, totalComp / 5);
    }
}