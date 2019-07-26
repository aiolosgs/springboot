package com.codegenius.shop.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

import com.codegenius.shop.web.vo.ResultVo;

@Order(0)
@WebFilter(filterName="AuthFilter",urlPatterns="/*",initParams={
		@WebInitParam(name="ignore",value="/login/getRSAKey,/login/doLogin"),
		@WebInitParam(name="param",value="123")})
public class AuthFilter implements Filter{

	private static List<String> ignoreList = new ArrayList<String>();
	
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
		
		//白名单检查
		String uri = request.getRequestURI();
		String contextPath 	= request.getContextPath();
		String target = uri.replace(contextPath, "");
		if(isIgnore(target)){
			filterChain.doFilter(req, res);
			return;
		}
		
		//检查是否登录
		String token = (String)session.getAttribute("authToken");
		if(StringUtils.isNotEmpty(token)){
			//匹配csrfToken
			String clientCsrfToken = request.getHeader("csrfToken");
			if(StringUtils.isNotEmpty(clientCsrfToken)){
				String serverCsrfToken = (String)session.getAttribute("csrfToken");
				if(clientCsrfToken.equals(serverCsrfToken)){
					filterChain.doFilter(req, res);
				}else{
					//是否要注销用户？
					response.getWriter().write(new ResultVo(false,"420").toString());
				}
			}else{
				response.getWriter().write(new ResultVo(false,"420").toString());
			}
		}else{
			response.getWriter().write(new ResultVo(false,"401").toString());
		}
	}

	private static boolean isIgnore(String url){
		for(String ignoreUri : ignoreList){
//			String ignoreRegExp = ignoreUri.replaceAll("/\\*", "[/.+]{0,1}");
			String ignoreRegExp = ignoreUri.replaceAll("\\*",".+");
			if(Pattern.matches(ignoreRegExp, url)){
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		ignoreList.add("/login/*");
		ignoreList.add("/ignore");
		ignoreList.add("/test/*/add");
		ignoreList.add("*/delete");
		
		System.out.println(isIgnore("/login"));
		System.out.println(isIgnore("/login/doLogin"));
		System.out.println(isIgnore("/test/add"));
		System.out.println(isIgnore("/test/user/add"));
		System.out.println(isIgnore("/test/user/add/11"));
		System.out.println(isIgnore("/role/delete"));
		System.out.println(isIgnore("/role/delete/11"));
		System.out.println(isIgnore("/delete/role"));
		System.out.println(isIgnore("/delete"));
		
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
