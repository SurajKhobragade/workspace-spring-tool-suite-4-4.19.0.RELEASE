package org.sr.config;

import java.util.Map;

import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class CustomFeignInterceptor implements RequestInterceptor {

	private static Map<String, String> headers;

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	@Override
	public void apply(RequestTemplate template) {
		if (headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				template.header(entry.getKey(), entry.getValue());
			}
		}
	}
}
