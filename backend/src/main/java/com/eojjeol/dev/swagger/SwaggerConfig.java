package com.eojjeol.dev.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
//@EnableWebMvc//스프링 부트에서는 X(무조건),스프링에서는 필수!
// 접속 url : https://localhost:8443/swagger-ui/index.html
public class SwaggerConfig implements WebMvcConfigurer {
    /**
     * Swagger를 위한 Docket 빈을 추가한다.
     *
     * @return
     */
    @Bean
    public Docket api() {
        final ApiInfo apiInfo = new ApiInfoBuilder()
                .title("ASAP REST API")
                .description("<h3>ASAP에서 사용되는 RestApi에 대한 문서를 제공한다.</h3>")
                .contact(new Contact("ASAP", "https://asap.com", "asap@asap.com"))
                .license("MIT License")
                .version("1.01")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(Errors.class,AuthenticationPrincipal.class)// Swagger 2.0 기반의 문서 작성
                .apiInfo(apiInfo)                             // 문서에 대한 정보를 설정한다.
                .select()                                    // ApiSelectorBuilder를 반환하며 상세한 설정 처리
                .apis(RequestHandlerSelectors.basePackage("com.eojjeol.dev"))// 대상으로하는 api 설정
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()));
        // Docket 객체 생성
    }
    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return springfox
                .documentation
                .spi.service
                .contexts
                .SecurityContext
                .builder()
                .securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    }
}
