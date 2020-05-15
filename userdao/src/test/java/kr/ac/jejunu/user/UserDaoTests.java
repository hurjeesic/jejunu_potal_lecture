package kr.ac.jejunu.user;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    String name = "jimen";
    String password = "1234";

    private static UserDao userDao;

    @BeforeAll
    public static void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("kr.ac.jejunu.user"); // annotation을 통한 환경 설정
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);

        // 정적으로 환경 설정
//        StaticApplicationContext applicationContext = new StaticApplicationContext();
//
//        BeanDefinition dataSourceBeanDefinition = new RootBeanDefinition(SimpleDriverDataSource.class);
//        dataSourceBeanDefinition.getPropertyValues().addPropertyValue("driverClass",
//                Class.forName(System.getenv("DB_CLASSNAME")));
//        dataSourceBeanDefinition.getPropertyValues().addPropertyValue("url",
//                System.getenv("DB_URL"));
//        dataSourceBeanDefinition.getPropertyValues().addPropertyValue("username",
//                System.getenv("DB_USERNAME"));
//        dataSourceBeanDefinition.getPropertyValues().addPropertyValue("password",
//                System.getenv("DB_PASSWORD"));
//        applicationContext.registerBeanDefinition("dataSource", dataSourceBeanDefinition);
//
//        BeanDefinition jdbcContextBeanDefinition = new RootBeanDefinition(JdbcTemplate.class);
//        jdbcContextBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(new RuntimeBeanReference("dataSource"));
//        applicationContext.registerBeanDefinition("jdbcContext", jdbcContextBeanDefinition);
//
//        BeanDefinition beanDefinition = new RootBeanDefinition(UserDao.class);
//        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(new RuntimeBeanReference("jdbcContext"));
//        applicationContext.registerBeanDefinition("userDao", beanDefinition);

//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("daoFactory.xml"); // xml을 통한 환경 설정
//        ApplicationContext applicationContext = new GenericGroovyApplicationContext("daoFactory.groovy"); // groovy를 통한 환경 설정

        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException {
        Integer id = 1;

        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(0));

        User insertedUser = userDao.get(user.getId());
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void update() throws SQLException {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        String updatedName = "jeesic";
        String updatedPassword = "1111";

        user.setName(updatedName);
        user.setPassword(updatedPassword);

        userDao.update(user);

        User updatedUser = userDao.get(user.getId());
        assertThat(updatedUser.getName(), is(updatedName));
        assertThat(updatedUser.getPassword(), is(updatedPassword));
    }

    @Test
    public void delete() throws SQLException {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        userDao.delete(user.getId());

        User deletedUser = userDao.get(user.getId());

        assertThat(deletedUser, IsNull.nullValue());
    }
}
