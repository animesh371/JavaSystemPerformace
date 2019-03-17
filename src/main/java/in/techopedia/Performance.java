package in.techopedia;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static com.codahale.metrics.MetricRegistry.name;

public class Performance {
    private static final MetricRegistry metrics = new MetricRegistry();

    public static void main(String[] args) {
        startReport();
        List<Integer> integerList = new ArrayList<>();
        for (int counter = 0; counter < 10000000; ++counter) {
            Random rand = new Random();
            int nextNum = rand.nextInt(50);
            if (nextNum % 2 == 0)
                integerList.add(nextNum);
        }
        integerList.add(3);
        int count = 0;
        while (true) {
            if (count < 1) {
                processForEach(integerList);
                processStream(integerList);
            }
            ++count;
        }
    }

    private static void processForEach(List<Integer> integerList) {
        final Timer foreachCall = metrics.timer(name(Performance.class, "forEachCall"));
        final Timer.Context context = foreachCall.time();
        int oddNumber = 1;
        for (Integer integer : integerList) {
            if (integer % 2 == 1) {
                oddNumber = integer;
                break;
            }
        }
        context.stop();
    }

    private static void processStream(List<Integer> integerList) {
        final Timer streamCall = metrics.timer(name(Performance.class, "streamCall"));
        final Timer.Context context = streamCall.time();
        Stream<Integer> number = integerList
                .parallelStream()
                .filter(integer -> integer % 2 == 1);
        Integer result = number.findFirst().get();
        context.stop();
    }

    private static void startReport() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(5, TimeUnit.SECONDS);
    }

}
