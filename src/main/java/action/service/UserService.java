package action.service;

import action.bean.User;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: 用户类的接口
 * @date 2017/11/15
 */
public interface UserService {

    /**
     * 查询用户信息
     * @param id
     * @return
     */
    User getUserById(Integer id) throws Exception;

    /**
     * 保存用户信息
     * @param user
     */
    void save(User user) throws Exception;
}
