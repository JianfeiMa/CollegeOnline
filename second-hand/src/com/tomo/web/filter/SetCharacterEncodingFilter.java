package com.tomo.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * @author csdn
 */
public class SetCharacterEncodingFilter implements Filter {
	private String encoding;
	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding(encoding);
		res.setCharacterEncoding(encoding);
		
		chain.doFilter(req, res);//把请求或响应交给下一个资源
	}

	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
		if(encoding == null || "".equals(encoding)){
			encoding = "UTF-8";
		}
	}

}
