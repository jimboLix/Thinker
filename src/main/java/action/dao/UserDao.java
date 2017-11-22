package action.dao;

import action.bean.User;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2017/10/11
 */
public interface UserDao {
    /**
     * 查询用户信息
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 保存用户信息
     * @param user
     */
    void save(User user);

}
