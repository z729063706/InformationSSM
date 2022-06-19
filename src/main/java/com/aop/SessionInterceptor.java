package com.aop;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 拦截器,用于控制用户,必须有session才能登录
 * @author Administrator
 *
 */
public class SessionInterceptor  extends HandlerInterceptorAdapter{

	/**
	 *  handler 这个参数,可以得到调用的方法的相关信息
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//没有session,则转到登录页
		if(request.getSession().getAttribute("session_user")==null) {
			request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			return false;
		}
		else {
			return true;
		}
	}

}