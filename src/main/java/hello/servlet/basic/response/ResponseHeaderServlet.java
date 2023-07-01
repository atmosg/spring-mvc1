package hello.servlet.basic.response;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setStatus(HttpServletResponse.SC_OK);

    resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    resp.setHeader("Pragma", "no-cache");
    resp.setHeader("my-header", "this is mine");

    content(resp);
    cookie(resp);
    
    PrintWriter writer = resp.getWriter();
    writer.write("ok");
  }

  private void content(HttpServletResponse resp) {
    resp.setContentType("text/plain");
    resp.setCharacterEncoding("utf-8");
  }

  private void cookie(HttpServletResponse resp) {
    Cookie cookie = new Cookie("myCookie", "good");
    cookie.setMaxAge(600);
    resp.addCookie(cookie);
  }

  private void redirect(HttpServletResponse resp) throws IOException {
    resp.sendRedirect("/basic/hell-form.html");
  }
}
