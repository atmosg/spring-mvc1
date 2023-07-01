package hello.servlet.web.frontcontroller.v3.controllers;

import java.util.List;
import java.util.Map;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

public class MemberListControllerV3 implements ControllerV3 {
  MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public ModelView process(Map<String, String> paramMap) {
    ModelView modelView = new ModelView("members");
    List<Member> members = memberRepository.findAll();

    modelView.getModel().put("members", members);
    return modelView;
  }
}
