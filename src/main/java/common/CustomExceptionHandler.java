package common;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: 统一异常处理
 * @date 2017/11/7
 */
public class CustomExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,//
                                         Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error");
        String msg = e.getMessage();
        e.printStackTrace();
        modelAndView.addObject("msg",msg);
        return modelAndView;
    }
}
