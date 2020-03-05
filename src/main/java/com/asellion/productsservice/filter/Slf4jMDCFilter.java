package com.asellion.productsservice.filter;

import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.core.config.Order;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Order(2)
@Component
public class Slf4jMDCFilter extends OncePerRequestFilter {
	
    private String mdcTokenKey = "transactionId";

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
        throws java.io.IOException, ServletException {
        try {
            final String token = UUID.randomUUID().toString();
            MDC.put(mdcTokenKey, token);
            chain.doFilter(request, response);
        } finally {
            MDC.remove(mdcTokenKey);
        }
    }
}