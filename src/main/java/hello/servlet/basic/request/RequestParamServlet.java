package hello.servlet.basic.request;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getParameterNames().asIterator().forEachRemaining(name -> 
      System.out.printf("%s : %s\n", name, req.getParameter(name))
    );

    String username = req.getParameter("username");
    System.out.println(username);

    resp.getWriter().write("Clear");
  }
}
