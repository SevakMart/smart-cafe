package am.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("am.smartcafe")
public class SmartCafeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartCafeApplication.class, args);
    }

}
