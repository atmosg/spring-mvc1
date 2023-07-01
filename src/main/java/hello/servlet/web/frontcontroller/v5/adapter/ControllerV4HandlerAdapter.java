package hello.servlet.web.frontcontroller.v5.adapter;

import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

  @Override
  public boolean supports(Object handler) {
    return handler instanceof ControllerV4;
  }

  @Override
  public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
    ControllerV4 controller = (ControllerV4) handler;

    Map<String, String> paramMap = getParamMap(req);
    ModelView modelView = getModelView(controller, paramMap);

    return modelView;
  }

  private ModelView getModelView(ControllerV4 controller, Map<String, String> paramMap) {
    Map<String, Object> model = new HashMap<>();
    String viewName = controller.process(paramMap, model);
    ModelView modelView = new ModelView(viewName);
    modelView.setModel(model);
    
    return modelView;
  }

  private Map<String, String> getParamMap(HttpServletRequest req) {
    Map<String, String> paramMap = new HashMap<>();
    req.getParameterNames()
      .asIterator()
      .forEachRemaining(name -> paramMap.put(name, req.getParameter(name)));

    return paramMap;
  }
  
}
