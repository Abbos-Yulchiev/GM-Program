package demo.com;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@EnableSwagger2
@EnableWebMvc
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}

class Demo {
    public static void main(String[] args) {
        String[] s1 = {"Camel", "Peacock", "Llama"}, s2 = {"Camel", "Llama", "Peacock"};
        System.out.println(Arrays.compare(s1, s2));
        StringBuilder stringBuilder = new StringBuilder("Java");
        System.out.println(stringBuilder.reverse());
//        PriorityQueue
        System.out.println(10 >> 1);
    }
}
