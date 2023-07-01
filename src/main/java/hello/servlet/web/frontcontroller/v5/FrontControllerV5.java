package hello.servlet.web.frontcontroller.v5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controllers.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controllers.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controllers.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controllers.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controllers.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controllers.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerV5", urlPatterns = "/v5/*")
public class FrontControllerV5 extends HttpServlet {
  private final Map<String, Object> handlerMap = new HashMap<>();
  private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

  public FrontControllerV5() {
    handlerMap.put("/v5/v3/members/new-form", new MemberFormControllerV3());
    handlerMap.put("/v5/v3/members/save", new MemberSaveControllerV3());
    handlerMap.put("/v5/v3/members", new MemberListControllerV3());

    handlerMap.put("/v5/v4/members/new-form", new MemberFormControllerV4());
    handlerMap.put("/v5/v4/members/save", new MemberSaveControllerV4());
    handlerMap.put("/v5/v4/members", new MemberListControllerV4());

    handlerAdapters.add(new ControllerV3HandlerAdapter());
    handlerAdapters.add(new ControllerV4HandlerAdapter());
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String requestURI = req.getRequestURI();
    Object handler = handlerMap.get(requestURI);
    
    if (handler == null) {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    MyHandlerAdapter adapter = getHandler(handler);
    ModelView modelView = adapter.handle(req, resp, handler);
    MyView view = viewResolver(modelView);
    
    view.render(modelView.getModel(), req, resp);
  }

  private MyView viewResolver(ModelView modelView) {
    String viewName = modelView.getViewName();
    String viewPath = "/WEB-INF/views/" + viewName + ".jsp";
    return new MyView(viewPath);
  }

  private MyHandlerAdapter getHandler(Object handler) {
    for (MyHandlerAdapter adapter : handlerAdapters) {
      if (!adapter.supports(handler)) continue;

      return adapter;
    }
    throw new IllegalArgumentException("handler adapter가 존재하지 않습니다.");
  }
}
