package hello.servlet.web.frontcontroller.v2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controllers.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controllers.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controllers.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerV2", urlPatterns = "/v2/*")
public class FrontControllerV2 extends HttpServlet {
  private final Map<String, ControllerV2> controllerMap = new HashMap<>();

  public FrontControllerV2() {
    controllerMap.put("/v2/members/new-form", new MemberFormControllerV2());
    controllerMap.put("/v2/members/save", new MemberSaveControllerV2());
    controllerMap.put("/v2/members", new MemberListControllerV2());
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String requestURI = req.getRequestURI();
    ControllerV2 controller = controllerMap.get(requestURI);

    if (controller == null) {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    MyView view = controller.process(req, resp);
    view.render(req, resp);
  }
}
