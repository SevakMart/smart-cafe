package am.smartcafe.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"am.smartcafe.data_access.repository"})
@EntityScan({"am.smartcafe.data_access.model"})
@ComponentScan({"am.smartcafe.data_access", "am.smartcafe.service", "am.smartcafe.presentation"})
@SpringBootApplication
public class SmartCafeApplication {

  public static void main(String[] args) {
    SpringApplication.run(SmartCafeApplication.class, args);
  }
}
