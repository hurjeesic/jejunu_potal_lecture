package kr.ac.jejunu.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@Controller("/userServlet")
@WebServlet(urlPatterns = "/hello")
public class UserServlet extends GenericServlet {
    @Autowired
    private UserDao userDao;

    @Override
    public void destroy() {
        System.out.println("*********************** destroy ***********************");
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("*********************** init ***********************");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("kr.ac.jejunu.user");
        this.userDao = applicationContext.getBean("userDao", UserDao.class);
        super.init(config);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("*********************** service ***********************");
        User user = userDao.get(1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        stringBuilder.append("<h1>");
        stringBuilder.append(String.format("Hello %s!!!", user.getName()));
        stringBuilder.append("</h1>");
        stringBuilder.append("</html>");
        res.setContentType("text/html; charset=UTF-8");
        res.getWriter().println(stringBuilder.toString());
    }
}
