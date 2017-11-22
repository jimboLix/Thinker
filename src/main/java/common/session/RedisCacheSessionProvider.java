package common.session;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: 使用redis缓存做session提供类
 * @date 2017/11/21
 */
public class RedisCacheSessionProvider implements SessionProvider {
    @Override
    public String getSessionId(HttpServletRequest request) {
        return request.getRequestedSessionId();
    }

    @Override
    public void setAttribute(HttpServletRequest request, String attributeName, Serializable value) {

    }

    @Override
    public Serializable getAttribute(HttpServletRequest request, String attributeName) {
        return null;
    }
}
