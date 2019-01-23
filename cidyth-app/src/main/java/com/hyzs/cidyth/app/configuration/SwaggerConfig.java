package com.hyzs.cidyth.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static String apiTitle = "华云中盛CID一体化系统——RESTFful APIS";
	
	private static String version = "0.3.0";
	
	@Bean
	public Docket testApi() {
		 return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.hyzs")).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
                .title(apiTitle)
                .version(version)
                .license("详情请查看swagger官网api")
                .licenseUrl("https://github.com/OAI/OpenAPI-Specification/blob/master/versions/1.2.md#524-parameter-object")
                .build();
	}

}
