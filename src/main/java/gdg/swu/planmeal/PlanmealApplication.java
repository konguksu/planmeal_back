package gdg.swu.planmeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PlanmealApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanmealApplication.class, args);
	}

}
