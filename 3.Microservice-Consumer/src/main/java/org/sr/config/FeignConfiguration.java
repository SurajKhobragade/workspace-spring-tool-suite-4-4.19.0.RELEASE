package org.sr.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "org.sr.service")
public class FeignConfiguration {
	// Optional: Add additional Feign client configuration if needed
}
