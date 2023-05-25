package timf.voc.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TimfVocTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimfVocTaskApplication.class, args);
	}

}
