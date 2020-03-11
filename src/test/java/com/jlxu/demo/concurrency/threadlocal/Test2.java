package com.jlxu.demo.concurrency.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 功能 :ThreadLocal使用场景  伪代码
 * 创建时间：2020年03月11日
 * 文件名称：Test2
 * 版本：1.0.0
 * 最后修改时间：2020/3/11 20:59
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class Test2 {
    @Test
    public void test() {
        //工具类(管理数据库连接)
//        public ThreadLocalUtil {
//            private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
//                public Connection initialValue() {
//                    return DriverManager.getConnection(DB_URL);
//                }
//            };
//
//            public static Connection getConnection () {
//                return connectionHolder.get();
//            }
//        }
        //工具类(Session)
//        public ThreadLocalUtil {
//            Session s = (Session) threadSession.get();
//            try {
//                if (s == null) {
//                    s = getSessionFactory().openSession();
//                    threadSession.set(s);
//                }
//            } catch (HibernateException ex) {
//                throw new InfrastructureException(ex);
//            }
//            return s;
//        }
    }
}
