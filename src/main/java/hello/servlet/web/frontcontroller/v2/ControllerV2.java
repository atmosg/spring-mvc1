package hello.servlet.web.frontcontroller.v2;

import java.io.IOException;

import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ControllerV2 {
  MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
