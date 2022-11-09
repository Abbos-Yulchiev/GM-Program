package epam.com.springbootmodule;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
//@EnableResourceServer
//@EnableAuthorizationServer
public class SpringBootModuleApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(SpringBootModuleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("hello world");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public TimedAspect timedAspect(MeterRegistry registry) {
		return new TimedAspect(registry);
	}

}


