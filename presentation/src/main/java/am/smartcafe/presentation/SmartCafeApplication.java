package am.smartcafe.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = {"am.smartcafe.data_access", "am.smartcafe.presentation", "am.smartcafe.service"})
@EnableJpaRepositories(basePackages = {"am.smartcafe.data_access"})
@EntityScan(basePackages = {"am.smartcafe.dataaccess"})

public class SmartCafeApplication {


    public static void main(String[] args) {
        SpringApplication.run(SmartCafeApplication.class, args);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
