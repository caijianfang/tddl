package com.taobao.tddl.common.exception.code;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

/**
 * <pre>
 * ResourceBundle工具类
 * 不同的资源文件,创建不同的 ResourceBundleUtil,从该资源文件中,得到key对应的描述信息
 * 
 * 使用方法:
 * 
 * 资源文件 res/BundleName.properties内容如下:
 * key1=value1
 * key2=value2,{0}
 * 
 * 代码:
 * ResourceBundleUtil util = new ResourceBundleUtil("res/BundleName");
 * util.getMessage("key1");                   //输出:value1
 * util.getMessage("key2","stone");           //输出:value2,stone
 * </pre>
 * 
 * @author jianghang 2014-3-20 下午6:02:00
 * @since 5.0.4
 */
public class ResourceBundleUtil {

    private static ResourceBundleUtil instance = new ResourceBundleUtil("res/ErrorCode");
    private ResourceBundle            bundle;                                            // 资源

    public static ResourceBundleUtil getInstance() {
        return instance;
    }

    /**
     * 构建ResourceBundleUtil,初始化bundle
     * 
     * @param bundleName 资源名
     * @throws MissingResourceException 资源文件不存在,则抛出运行期异常
     */
    public ResourceBundleUtil(String bundleName){
        this.bundle = ResourceBundle.getBundle(bundleName);
    }

    /**
     * <pre>
     * 从资源文件中,根据key,得到详细描述信息
     * 资源文件中,key对应的message允许占位符,根据params组成动态的描述信息
     * 
     * 如果key为null,则返回null
     * 如果key对应的message为null,则返回null
     * </pre>
     * 
     * @param key 详细描述对应的关键词
     * @param params 占位符对应的内容
     * @return 详细描述信息
     */
    public String getMessage(String key, String... params) {
        // 参数校验
        if (key == null) {
            return null;
        }
        // 得到message内容
        String msg = bundle.getString(key);
        // 如果不存在动态内容,则直接返回msg
        if (params == null || params.length == 0) {
            return msg;
        }
        // 存在动态内容,渲染后返回新的message
        if (StringUtils.isBlank(msg)) {
            // 如果得到的msg为null或者空字符串,则直接返回msg本身
            return msg;
        }
        return MessageFormat.format(msg, (Object[]) params);
    }

}
