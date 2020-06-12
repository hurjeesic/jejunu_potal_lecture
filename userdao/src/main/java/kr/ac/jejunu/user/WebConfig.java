package kr.ac.jejunu.user;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import java.util.List;

@Configuration
@EnableWebMvc // <annotation-driven />
@ComponentScan("kr.ac.jejunu.user") // <context:component-scan base-package="kr.ac.jejunu.user" />
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		<interceptors>
//	        <interceptor>
//	            <mapping path="/**/*"/>
//	            <beans:bean class="kr.ac.jejunu.user.UserInterceptor" />
//	        </interceptor>
//	    </interceptors>
		registry.addInterceptor(new UserInterceptor()).addPathPatterns("/**/*");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		<resources mapping="/images/**" location="/WEB-INF/static/" />
		registry.addResourceHandler("/images/**")
				.addResourceLocations("/WEB-INF/static/");
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		<beans:property name="mediaTypes">
//			<beans:props>
//				<beans:prop key="js">application/json</beans:prop>
//				<beans:prop key="x">application/xml</beans:prop>
//			</beans:props>
//		</beans:property>
		configurer.mediaType("js", MediaType.APPLICATION_JSON)
				.mediaType("x", MediaType.APPLICATION_XML);
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
//		<beans:bean class="org.springframework.web.servlet.view.json.MappingJackson2JsonView" />
		registry.enableContentNegotiation(new MappingJackson2JsonView());
//		<beans:bean class="org.springframework.web.servlet.view.xml.MappingJackson2XmlView" />
		registry.enableContentNegotiation(new MappingJackson2XmlView());
//		<beans:bean name="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//			<beans:property name="prefix" value="/WEB-INF/views/" />
//			<beans:property name="suffix" value=".jsp" />
//		</beans:bean>
		registry.jsp().prefix("/WEB-INF/views/")
				.suffix(".jsp");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
	}
}
