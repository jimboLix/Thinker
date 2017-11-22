package action.service.impl;

import action.bean.User;
import action.dao.UserDao;
import action.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description:
 * @date 2017/11/15
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User getUserById(Integer id) throws Exception{

        return userDao.getUserById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(User user)throws Exception {
        this.userDao.save(user);
    }
}
