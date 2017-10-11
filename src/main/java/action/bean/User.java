package action.bean;

import java.io.Serializable;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description:
 * @date 2017/10/11
 */
public class User implements Serializable {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
