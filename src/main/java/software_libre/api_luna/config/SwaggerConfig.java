package software_libre.api_luna.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api luna")
                        .description("Api luna back")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("softwarelibre")
                                .email("auladesoftwarelibre@gmail.com")));
    }
}
