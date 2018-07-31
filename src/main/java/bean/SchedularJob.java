package bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: 自定义的任务实体
 * @date 2018/7/30
 */
@Data
@NoArgsConstructor
public class SchedularJob implements Serializable {
    private static final long serialVersionUID = 5438551479239198362L;

    private String name;

    private String clazz;

    private String method;

    private String time;

    private List args;

    private boolean enable;
}
