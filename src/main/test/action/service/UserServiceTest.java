package action.service;

import action.SpringJunitTestCase;
import action.bean.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2017/11/6
 */
public class UserServiceTest extends SpringJunitTestCase {

    @Autowired
    private UserService userService;
    @Test
    public void testGetUserById(){
        User user = userService.getUserById(1);
        user.getName();

    }

    @Test
    public void testSave(){
        User u = new User();
        u.setId(3);
        u.setName("王五");
        try {
            userService.save(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
