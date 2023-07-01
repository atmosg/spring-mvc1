package hello.servlet.web.frontcontroller.v4;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controllers.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controllers.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controllers.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerV4", urlPatterns = "/v4/*")
public class FrontControllerV4 extends HttpServlet {
  private final Map<String, ControllerV4> controllerMap = new HashMap<>();

  public FrontControllerV4() {
     controllerMap.put("/v4/members/new-form", new MemberFormControllerV4()); 
     controllerMap.put("/v4/members/save", new MemberSaveControllerV4());
     controllerMap.put("/v4/members/", new MemberListControllerV4());
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, Object> model = new HashMap<>();
    Map<String, String> paramMap = getParamMap(req);
    
    String requestURI = req.getRequestURI();
    ControllerV4 controller = controllerMap.get(requestURI);
    
    if (controller == null) {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    String viewName = controller.process(paramMap, model);
    String viewPath = viewResolver(viewName, "jsp");

    MyView view = new MyView(viewPath);
    view.render(model, req, resp);
  }

  private Map<String, String> getParamMap(HttpServletRequest req) {
    Map<String, String> paramMap = new HashMap<>();
    Enumeration<String> parameterNames = req.getParameterNames();
    parameterNames.asIterator().forEachRemaining(name -> 
      paramMap.put(name, req.getParameter(name))
    );

    return paramMap;
  }

  private String viewResolver(String viewName, String extension) {
    return "/WEB-INF/views/" + viewName + "." + extension;
  }
  
}
