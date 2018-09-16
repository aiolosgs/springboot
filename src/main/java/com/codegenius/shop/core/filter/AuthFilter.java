package com.codegenius.shop.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;

@Order(0)
@WebFilter(filterName="testFilter",urlPatterns="/*")
public class AuthFilter implements Filter{

	private static boolean flag = false;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("testFilter");
		HttpServletRequest request = (HttpServletRequest)req;

		String uri = request.getRequestURI();
		String contextPath 	= request.getContextPath();
		String target = uri.replace(contextPath, "");
		System.out.println(target);
		
		if(!flag){
			if("/login".equals(target)){
				filterChain.doFilter(req, res);
				return;
			}
			req.getRequestDispatcher("/login").forward(req, res);
			flag = true;
		}else{
			filterChain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
