package hello.servlet.basic.request;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    printStartline(req);
    printHeaders(req);
    printHeaderUtils(req);


    Enumeration<String> parameterNames = req.getParameterNames();
    System.out.println(parameterNames.toString());

    Map<String, String[]> parameterMap = req.getParameterMap();
    System.out.println(parameterMap.toString());
  }

  private void printStartline(HttpServletRequest req) {
    System.out.println();
    System.out.println("------- REQEUST LINE -------");

    System.out.printf("req.getMethod() : %s\n", req.getMethod());
    System.out.printf("req.getProtocol() : %s\n", req.getProtocol());
    System.out.printf("req.getScheme() : %s\n", req.getScheme());
    System.out.printf("req.getRequestURL() : %s\n", req.getRequestURL());
    System.out.printf("req.getRequestURI() : %s\n", req.getRequestURI());
    System.out.printf("req.getQueryString() : %s\n", req.getQueryString());
    System.out.printf("req.isSecure() : %s\n", req.isSecure());
  }

  private void printHeaders(HttpServletRequest req) {
    System.out.println();
    System.out.println("------- Headers LINE -------");

    req.getHeaderNames().asIterator().forEachRemaining(headerName -> 
      System.out.printf("%s : %s\n",headerName, req.getHeader(headerName))
    );

    System.out.println();
  }

  private void printHeaderUtils(HttpServletRequest req) {
    System.out.println("------- Header Utils LINE -------");
    System.out.println("[Host 정보 조회]");
    System.out.printf("req.getServerName() : %s\n", req.getServerName());
    System.out.printf("req.getServerPort() : %s\n", req.getServerPort());
    System.out.println();

    System.out.println("[Accept-Language 정보 조회]");
    req.getLocales().asIterator().forEachRemaining(locale -> 
      System.out.printf("locale : %s\n", locale)
    );
    System.out.printf("req.getLocale() : %s\n", req.getLocale());
    System.out.println();

    System.out.println("[Cookie 정보 조회]");
    if (req.getCookies() == null) {
      System.out.println("등록된 쿠키 정보가 없습니다.");
    }
    else {
      System.out.printf("%s\n", req.getCookies().toString());
    }
    System.out.println();

    System.out.println("[Content 정보 조회]");
    System.out.printf("req.getContentType() : %s\n", req.getContentType());
    System.out.printf("req.getContentLength() : %s\n", req.getContentLength());
    System.out.printf("req.getCharacterEncoding() : %s\n", req.getCharacterEncoding());
    System.out.println();
  }
}
