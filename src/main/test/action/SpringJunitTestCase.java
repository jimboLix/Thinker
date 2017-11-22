package action;

import init.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2017/11/6
 */
@ContextConfiguration(classes = {ApplicationRootConfig.class, InitTransactionConfig.class, IntitSessionFactory.class, AopConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class SpringJunitTestCase {
}
