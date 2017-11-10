package action.service.impl;

import action.bean.User;
import action.dao.UserDao;
import action.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description:
 * @date 2017/11/6
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public void save(User user) throws Exception {
        userDao.save(user);
    }

    @Override
    public void testException(int choose) throws Exception {
        switch (choose) {
            case 1:
                throw new Exception("第一种情况");
            case 2:
                throw new Exception("第二种情况");
            default:
                throw new Exception("默认情况");
        }

    }
}
