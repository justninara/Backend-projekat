package rpp;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class Swagger {
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
						 .select()
						 .apis(RequestHandlerSelectors.basePackage("rpp"))
						 .build()
						 .apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo("Backend RPP/APURSP Swagger",
									  "Agilni pristupi u razvoju softverskih proizvoda/Razvoj programskih proizvoda",
									  "1.0",
									  "",
									  new Contact("Nikolina Damjanovic", "https://github.com/RPPIIM/backend-justninara","nikolinadamjanovic24@gmail.com"),
									  "",
									  "");
		return apiInfo;
							
	}
}


