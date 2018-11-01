package com.example.service;

/**
 * ClassName: GenericEnum <br/>
 * Description:枚举通用接口 <br/>
 * Date:	2016/9/1 15:18<br/>
 * <p>
 * Modification History:<br/>
 * Date           Author          Version          Description<br/>
 * -----------------------------------------------------------------------------------<br/>
 * 2016/9/1    张胜飞         1.0              <初始创建><br/>
 *
 * @author <a href="mailto:hotyei2002@163.com">ZhangShengfei</a>
 * @version 1.0
 * @since 1.0
 */
public interface GenericEnum {
    /**
     * 获取Code
     *
     * @return 获取Code
     */
    String getCode();

    /**
     * 枚举名称
     *
     * @return 枚举名称
     */
    String getDesc();
}
