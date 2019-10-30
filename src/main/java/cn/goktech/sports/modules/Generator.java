package cn.goktech.sports.modules;

import cn.goktech.sports.modules.sys.generator.JdbcGenUtils;
import cn.goktech.sports.modules.sys.generator.JdbcGenUtils;

/**
 * 代码生成器
 * @author zcl<yczclcn@163.com>
 */
public class Generator {

    public static void main(String[] args) throws Exception {

        String jdbcDriver = "com.mysql.jdbc.Driver";
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/sports_news?useUnicode=true&characterEncoding=utf-8";
        String jdbcUsername = "root";
        String jdbcPassword = "123456";

        String tablePrefix = "good";

        String javaModule = "test";
        String webModule = "test";

        JdbcGenUtils.generatorCode(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword, tablePrefix, javaModule, webModule);

    }

}
