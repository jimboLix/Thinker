package common.session;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: 使用redis缓存做session提供类
 * @date 2017/11/21
 */
public class RedisCacheSessionProvider implements SessionProvider {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    private final String JSESSIONID = "JSESSIONID";

    @Override
    public String getSessionId(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if(null != cookies && cookies.length > 0){
            for (Cookie c : cookies){
                if(JSESSIONID.equals(c.getName())){
                    return c.getValue();
                }
            }
        }

        String sessionId = UUID.randomUUID().toString().replaceAll("-", "");
        Cookie cookie = new Cookie(JSESSIONID,sessionId);
        cookie.setPath("/");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        return sessionId;
    }

    @Override
    public void setAttribute(HttpServletRequest request,HttpServletResponse response, String attributeName, Serializable value) {
        Map<String, Serializable> session = null;
        String sessionId = this.getSessionId(request,response);
        try {
            if (StringUtils.isNotBlank(sessionId)) {
                Serializable sessionStr = redisTemplate.opsForValue().get(sessionId);
                if (null != sessionStr) {
                    session = (Map<String, Serializable>) JSONUtils.parse((String) sessionStr);
                    session.put(attributeName, value);
                } else {
                    session = new HashMap<String, Serializable>();
                    session.put(attributeName, value);
                }

                ObjectMapper om = new ObjectMapper();
                om.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
                StringWriter sw = new StringWriter();
                om.writeValue(sw, session);
                //放入redis缓存中
                redisTemplate.opsForValue().set(sessionId,sw.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Serializable getAttribute(HttpServletRequest request,HttpServletResponse response, String attributeName) {
        Map<String, Serializable> session = null;
        String sessionId = this.getSessionId(request,response);
        Serializable sessionStr = redisTemplate.opsForValue().get(sessionId);
        if(null == sessionStr){
            return null;
        }else {
            session = (Map<String, Serializable>) JSONUtils.parse((String) sessionStr);
            return session.get(attributeName);
        }
    }
}
