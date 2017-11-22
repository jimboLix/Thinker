package common.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: HttpSession提供类
 * @date 2017/11/21
 */
public class HttpSessionProvider implements SessionProvider {
    @Override
    public String getSessionId(HttpServletRequest request) {
        return request.getRequestedSessionId();
    }

    @Override
    public void setAttribute(HttpServletRequest request, String attributeName, Serializable value) {
        //如果不存在session，则创建
        HttpSession session = request.getSession(true);
        session.setAttribute(attributeName, value);
    }

    @Override
    public Serializable getAttribute(HttpServletRequest request, String attributeName) {
        //如果session不存在，则不创建
        HttpSession session = request.getSession(false);
        return null == session ? null : (Serializable) session.getAttribute(attributeName);
    }
}
