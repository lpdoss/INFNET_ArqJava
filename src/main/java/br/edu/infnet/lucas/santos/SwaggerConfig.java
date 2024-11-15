package br.edu.infnet.lucas.santos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API: loja de inverno")
                .version("1.0")
                .description("API para loja de produtos de inverno")
                .contact(new Contact()
                    .name("Lucas Santos")
                    .email("lucas.psantos@al.infnet.edu.br")));
    }
}
