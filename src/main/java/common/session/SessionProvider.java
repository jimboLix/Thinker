package common.session;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description:  Session提供类接口
 * @date 2017/11/21
 */
public interface SessionProvider {

    /**
     * 获取sessionId
     * @param request
     * @return
     */
    String getSessionId(HttpServletRequest request);

    /**
     * 向session中存放属性值
     * @param request
     * @param attributeName 属性名称
     * @param value 值
     */
    void setAttribute(HttpServletRequest request,String attributeName,Serializable value);

    /**
     * 获取session中的属性值
     * @param request
     * @param attributeName 属性名称
     * @return 属性值
     */
    Serializable getAttribute(HttpServletRequest request,String attributeName);

}
