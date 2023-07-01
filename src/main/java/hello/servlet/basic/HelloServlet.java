package hello.servlet.basic;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    System.out.println("HelloServlet.service()");
    System.out.printf("request: %s\n", req);
    System.out.printf("response: %s\n", res);

    String username = req.getParameter("username");
    System.out.printf("username: %s\n", username);

    res.setContentType("text/plain");
    res.setCharacterEncoding("utf-8");
    res.getWriter().write("hello " + username);
  }
}
