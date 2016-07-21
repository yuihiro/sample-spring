package nagi.starter.SpringRest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import nagi.starter.SpringRest.common.interceptor.AuthInterceptor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc /* <mvc:annotation-driven */
@EnableSwagger2
public class MvcConfig extends WebMvcConfigurerAdapter {

	/* <mvc:default-servlet-handler> 처리할 수 없는 요청은 컨테이너에게 위임 */
	/*
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	*/

	/* <mvc:view-controller path="/accessDenied" view-name="error/accessDenied"/> */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//registry.addViewController("/").setViewName("index");
		registry.addViewController("/").setViewName("forward:/index.jsp");
	}

	/* <<mvc:resources location="/resources/" mapping="/resources/**"> */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/**").addResourceLocations("/");
		registry.addResourceHandler("/**").addResourceLocations("/public/");
		//registry.addResourceHandler("/api/**").addResourceLocations("/public/api/");
		//registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthInterceptor());
		//registry.addInterceptor(new CorsInterceptor());
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(true);
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setContentType("text/html;charset=utf-8");
		viewResolver.setOrder(1);
		return viewResolver;
	}

	/*
	@Bean
	public Docket api() {
		ApiInfo apiInfo = new ApiInfo("Sample", "Sample API Description", "1.0", "Sample API terms of service", "Sample API Contact Email", "Sample API Licence Type",
				"Sample API License URL");
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build().apiInfo(apiInfo);
	}
	*/
}