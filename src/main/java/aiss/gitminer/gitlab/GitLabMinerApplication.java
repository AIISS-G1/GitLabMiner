package aiss.gitminer.gitlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan("aiss.gitminer") // scan base project too
public class GitLabMinerApplication {

	public static final String BASE_DIR = "https://gitlab.com/api/v4/projects";

	public static void main(String[] args) {
		SpringApplication.run(GitLabMinerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
