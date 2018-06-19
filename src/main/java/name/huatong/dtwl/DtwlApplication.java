package name.huatong.dtwl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DtwlApplication {

    public static void main(String[] args) {
        SpringApplication.run(DtwlApplication.class, args);
    }
}
