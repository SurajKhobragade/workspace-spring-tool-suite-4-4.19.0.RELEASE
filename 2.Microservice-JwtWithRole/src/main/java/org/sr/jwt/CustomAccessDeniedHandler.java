package org.sr.jwt;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response, AccessDeniedException accessDeniedException)
			throws IOException, jakarta.servlet.ServletException {
		System.out.println("CustomAccessDeniedHandler.handle()");
		// Customize the access denied response
		response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN);
		response.setContentType("application/json");
		response.getWriter().write("Access Denied: You don't have permission to access this resource.");
		response.getWriter().flush();
	}

}
