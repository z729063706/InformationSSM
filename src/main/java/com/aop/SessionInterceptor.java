package com.aop;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * ������,���ڿ����û�,������session���ܵ�¼
 * @author Administrator
 *
 */
public class SessionInterceptor  extends HandlerInterceptorAdapter{

	/**
	 *  handler �������,���Եõ����õķ����������Ϣ
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//û��session,��ת����¼ҳ
		if(request.getSession().getAttribute("session_user")==null) {
			request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			return false;
		}
		else {
			return true;
		}
	}

}