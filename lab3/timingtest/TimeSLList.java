package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static AList<Integer> Ns = new AList<>();
    private static AList<Double> times = new AList<>();
    private static AList<Integer> opCounts = new AList<>();
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        int count = 1000;
        for (int timesCount = 0;timesCount < 8;timesCount++){
            calculate(count);
            count *= 2;
        }
        printTimingTable(Ns, times, opCounts);
        // TODO: YOUR CODE HERE
    }

    private static void calculate(int count){
        SLList<Integer> testList = new SLList<>();
        Stopwatch stopwatch = new Stopwatch();
        for (int n = 0; n < count; n++) {
            testList.addLast(1);
        }
        double time = stopwatch.elapsedTime();
        Ns.addLast(count);
        times.addLast(time);
        opCounts.addLast(count);
    }
}
