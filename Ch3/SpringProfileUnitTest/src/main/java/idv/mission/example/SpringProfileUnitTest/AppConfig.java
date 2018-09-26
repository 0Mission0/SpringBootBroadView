package idv.mission.example.SpringProfileUnitTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {

	@Bean
	@Profile("dev")
	public TestBean devTestBean() {
		return new TestBean("Dev Environment");
	}

	@Bean
	@Profile("prod")
	public TestBean prodTestBean() {
		return new TestBean("Prod Environment");
	}

}
