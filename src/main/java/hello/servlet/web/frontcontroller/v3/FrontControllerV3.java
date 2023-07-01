package hello.servlet.web.frontcontroller.v3;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controllers.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controllers.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controllers.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerV3", urlPatterns = "/v3/*")
public class FrontControllerV3 extends HttpServlet {
  private final Map<String, ControllerV3> controllerMap = new HashMap<>();

  public FrontControllerV3() {
    controllerMap.put("/v3/members/new-form", new MemberFormControllerV3());
    controllerMap.put("/v3/members/save", new MemberSaveControllerV3());
    controllerMap.put("/v3/members", new MemberListControllerV3());
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String requestURI = req.getRequestURI();
    ControllerV3 controller = controllerMap.get(requestURI);

    if (controller == null) {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    Map<String, String> paramMap = createParamMap(req);
    ModelView modelView = controller.process(paramMap);

    String viewPath = viewResolver(modelView, "jsp");
    Map<String, Object> model = modelView.getModel();
    
    MyView myView = new MyView(viewPath);
    myView.render(model, req, resp);
  }

  private String viewResolver(ModelView modelView, String extension) {
    String viewName = modelView.getViewName();
    return "/WEB-INF/views/" + viewName + "." + extension;
  }

  private Map<String, String> createParamMap(HttpServletRequest req) {
    Map<String, String> paramMap = new HashMap<>();
    Enumeration<String> parameterNames = req.getParameterNames();
    parameterNames.asIterator().forEachRemaining(name -> 
      paramMap.put(name, req.getParameter(name))
    );
    return paramMap;
  }
  
}
