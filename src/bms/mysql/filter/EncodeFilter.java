package bms.mysql.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一设置所有页面的编码
 * @author zhong
 *
 */
public class EncodeFilter implements Filter{
	private String encoding;
	private String contentType;
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest)request;
		HttpServletResponse hres = (HttpServletResponse)response;
		
		
		//将get请求转码
		if("GET".equals(hreq.getMethod().toUpperCase())){
			Iterator<String[]> iter=request.getParameterMap().values().iterator();
		      while(iter.hasNext()){
			       String[] parames=iter.next();
			       for (int i = 0; i < parames.length; i++) {
				       try {
				        	parames[i]=new String(parames[i].getBytes("iso8859-1"),encoding);
				       } catch (UnsupportedEncodingException e) {
				         e.printStackTrace();
				       }
			       } 
		      }
		}
		
		//设置页面编码
		hreq.setCharacterEncoding(this.encoding);
		hres.setContentType(this.contentType);
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig config) throws ServletException {
		this.encoding = config.getInitParameter("encoding");
		this.contentType = config.getInitParameter("contentType");
		
	}

}
