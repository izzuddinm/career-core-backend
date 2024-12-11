package com.izzuddinm.career_core_backend.base.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.izzuddinm.career_core_backend.base.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestApiKey = request.getHeader("api-key");

        if (apiKey.equals(requestApiKey)) {
            return true;
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            BaseResponse<Object> baseResponse = BaseResponse.fail("Unauthorized: Invalid API Key", null);
            String jsonResponse = objectMapper.writeValueAsString(baseResponse);

            response.getWriter().write(jsonResponse);
            response.getWriter().flush();
            return false;
        }
    }
}