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
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;

@Order(0)
@WebFilter(filterName="AuthFilter",urlPatterns="/*",initParams={
		@WebInitParam(name="ignore",value="/ignore,/test"),
		@WebInitParam(name="param",value="123")})
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
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		HttpSession session = request.getSession();
		String csrfToken = (String)session.getAttribute("csrfToken");
		if(StringUtils.isEmpty(csrfToken)){
			csrfToken = "1235csrf";
			session.setAttribute("csrfToken", csrfToken);
		}
		
		String uri = request.getRequestURI();
		String contextPath 	= request.getContextPath();
		String target = uri.replace(contextPath, "");
		
		String token = (String)session.getAttribute("authToken");
//		if(StringUtils.isNotEmpty(token)){
//			response.getWriter().write("");
//		}else{
//			
//		}
//		if(!flag){
//			if(isIgnore(target)){
//				filterChain.doFilter(req, res);
//				return;
//			}
//			req.getRequestDispatcher("/login").forward(req, res);
//			flag = true;
//		}else{
			filterChain.doFilter(req, res);
//		}
	}

	private boolean isIgnore(String url){
		return this.ignoreList.contains(url);
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String ignore = config.getInitParameter("ignore");
		if(StringUtils.isNotEmpty(ignore)){
			String[] arr = ignore.split(",");
			for(String uri : arr){
				System.out.println(uri);
				ignoreList.add(uri);
			}
		}
	}

	
}
