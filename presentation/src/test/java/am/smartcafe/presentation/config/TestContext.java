package am.smartcafe.presentation.config;

import javax.sql.DataSource;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
public class TestContext {
  @Bean
  public DataSource dataSource() {
    return Mockito.mock(DataSource.class);
  }


}
