package am.smartcafe.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("am.smartcafe.data_access.repository")
@SpringBootApplication
public class SmartCafeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartCafeApplication.class, args);
    }

}
