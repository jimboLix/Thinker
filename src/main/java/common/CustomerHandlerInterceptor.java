package common;

import common.session.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import util.ConstansUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: 自定义拦截器
 * @date 2017/11/20
 */
public class CustomerHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    private SessionProvider sessionProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        //如果是访问登录页面则放行
        if (ConstansUtil.LOGINURL.equals(requestURI) || ConstansUtil.DOLOGINURL.equals(requestURI)) {
            return true;
        }
        //判断是否需要拦截
        if (requestURI.endsWith(ConstansUtil.INTERCEPT_URI)) {
            Serializable userName = sessionProvider.getAttribute(request,response, ConstansUtil.USER_ON_SESSION_NAME);
            if (null == userName) {
                response.sendRedirect("/thinker/home/login.do");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
