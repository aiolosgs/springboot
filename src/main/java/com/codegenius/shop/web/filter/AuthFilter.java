package com.codegenius.shop.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
@WebFilter(filterName="testFilter",urlPatterns="/test")
public class AuthFilter implements Filter{

	private static boolean flag = false;
	
	private List<String> ignoreList = new ArrayList<String>();
	
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
		
		if(!flag){
			if(isIgnore(target)){
				filterChain.doFilter(req, res);
				return;
			}
			req.getRequestDispatcher("/login").forward(req, res);
			flag = true;
		}else{
			filterChain.doFilter(req, res);
		}
	}

	private boolean isIgnore(String url){
		return this.ignoreList.contains(url);
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		this.ignoreList.add("/login");
		this.ignoreList.add("/druid");
	}

	
}
