package am.smartcafe.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"data-access.am.smartcafe.data_access.repository"})
@EntityScan({"data-access.am.smartcafe.data_access.model"})
@ComponentScan( {"data-access.am.smartcafe.data_access","service.am.smartcafe.service"})
@SpringBootApplication
public class SmartCafeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartCafeApplication.class, args);
    }

}
