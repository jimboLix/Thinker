package action.service;

import action.bean.User;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description:
 * @date 2017/11/6
 */
public interface UserService {

    User getUserById(Integer id);
    void save(User user) throws Exception;
    void testException(int choose)throws Exception;
}
