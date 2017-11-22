package action.controller;

import action.bean.User;
import action.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: 用户中心
 * @date 2017/11/16
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/get/{id}")
    public String get(@PathVariable("id") Integer id, Model model)throws Exception{
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        return "user/user-detail";
    }
}
