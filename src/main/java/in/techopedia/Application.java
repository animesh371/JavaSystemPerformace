package in.techopedia;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import in.dao.StudentsDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@ComponentScan("in.controller")
public class Application {
    public static final MetricRegistry metrics = new MetricRegistry();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        startReport();
    }

    private static void startReport() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(5, TimeUnit.SECONDS);
    }

    @Bean
    public StudentsDAO getStudentDAO() {
        return new StudentsDAO();
    }
}
