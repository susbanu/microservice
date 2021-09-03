package com.spring.microservices.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {
		
		// Build custom routes
		return routeLocatorBuilder.routes()
				// If path is /get then we redirect to specific URI (for server URI ex. - http://httpbin.org:80/get and, Add custom header and request param)
				.route(predicateSpec -> predicateSpec.path("/get")
						.filters(gatewayFilterSpec -> gatewayFilterSpec
														.addRequestHeader("MyCustomHeader", "HeaderValue")
														.addRequestParameter("MyRequestParam", "ParamValue"))
						.uri("http://httpbin.org:80")) // URI : Specify redirect URI
				
				// http://localhost:8765/currency-conversion-service/currency-conversion-feign/from/USD/to/INR/quantity/10
				// TO
				// http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10
				.route(predicateSpec -> predicateSpec.path("/currency-conversion-feign/**") 
						.uri("lb://currency-conversion-service")) // redirect to naming server(service registration name same as discovery server) and also wanted to do load balancer 
				
				.route(predicateSpec -> predicateSpec.path("/currency-conversion/**") 
						.uri("lb://currency-conversion-service")) // redirect to naming server(service registration name same as discovery server) and also wanted to do load balancer 
				
				.route(predicateSpec -> predicateSpec.path("/currency-exchange/**") 
						.uri("lb://currency-exchange-service")) // redirect to naming server(service registration name same as discovery server) and also wanted to do load balancer 
				
				// http://localhost:8765/currency-conversion-service/currency-conversion-feign/from/USD/to/INR/quantity/10
				// TO
				// http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/10
				.route(predicateSpec -> predicateSpec.path("/currency-conversion-new/**")
						.filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath(
								"/currency-conversion-new/(?<segment>.*)", "/currency-conversion-feign/${segment}"))
						.uri("lb://currency-conversion-service")) // redirect to naming server(service registration name same as discovery server) and also wanted to do load balancer 
				
				.build();
	}
}
