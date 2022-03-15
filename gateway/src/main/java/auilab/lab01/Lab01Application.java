package auilab.lab01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class Lab01Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab01Application.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route("bookshelf", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/bookshelf/{id}", "/api/bookshelf/findAll", "/api/bookshelf/")
						.uri("http://localhost:8082"))
				.route("books", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/books", "/api/books/**", "/api/bookshelf/{id}/books", "/api/bookshelf/{id}/books/**")
						.uri("http://localhost:8081"))
				.build();
	}

	@Bean
	public CorsWebFilter corsWebFilter() {

		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
		corsConfig.addAllowedHeader("*");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}
}
