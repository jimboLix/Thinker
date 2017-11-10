package common;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: Session提供接口
 * @date 2017/11/7
 */
public interface SessionProvider {

    /**
     * 向session中设置值
     * @param request
     * @param name
     * @param value
     */
    void setAttribute(HttpServletRequest request, String name, Serializable value);

    /**
     *
     * @param request
     * @param name
     * @return
     */
    Serializable getAttribute(HttpServletRequest request,String name);


}
