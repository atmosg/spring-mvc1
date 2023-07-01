package hello.servlet.web.frontcontroller.v2.controllers;

import java.io.IOException;
import java.util.List;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberListControllerV2 implements ControllerV2 {
  MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Member> members = memberRepository.findAll();
    req.setAttribute("members", members);

    return new MyView("/WEB-INF/views/members.jsp");
  }
  
}
