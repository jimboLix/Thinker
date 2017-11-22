package action.controller;

import action.bean.User;
import action.service.UserService;
import common.session.SessionProvider;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import util.ConstansUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description:
 * @date 2017/11/15
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private SessionProvider sessionProvider;

    @RequestMapping("/home.do")
    public String home() throws Exception{
        System.out.println("aaaaaaaaaaaaaaaaaaaa");
        User u = new User();
        u.setId(101);
        u.setName("test");
        userService.save(u);
//        throw new RuntimeException("test............");
        return "home";
    }

    @RequestMapping("/login.do")
    public String login() throws Exception{
        return "login";
    }

    @RequestMapping("/doLogin.do")
    public String doLogin(Integer id, HttpServletRequest request)throws Exception{
        User user = userService.getUserById(id);
        if(null != user){
            sessionProvider.setAttribute(request, ConstansUtil.USER_ON_SESSION_NAME,user.getName());
        }
        return "redirect:login.do";
    }
}
