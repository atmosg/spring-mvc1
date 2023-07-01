package hello.servlet.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

  @Override
  public boolean supports(Object handler) {
    return handler instanceof ControllerV3;
  }

  @Override
  public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException {
    ControllerV3 controller = (ControllerV3) handler;

    Map<String, String> paramMap = getParamMap(req);
    ModelView modelView = controller.process(paramMap);
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
