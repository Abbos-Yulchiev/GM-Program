package demo.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("demo.com"))
                .paths(PathSelectors.ant("/api/v1/eventService/*"))
                .build()
                .apiInfo(apiInfo())
                .pathMapping("/api/v1/eventService/*")
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Rest Api Architecture")
                .description("It is for Java Global Mentoring Program")
                .termsOfServiceUrl("http://rest-api-architecture.com")
                .contact(new Contact("demo", "/demo", "demo@demo.com"))
                .license("Hi, for now we don't have any license, but you feel free to use")
                .licenseUrl("demo@demo.com")
                .version("1.0")
                .build();
    }
}
