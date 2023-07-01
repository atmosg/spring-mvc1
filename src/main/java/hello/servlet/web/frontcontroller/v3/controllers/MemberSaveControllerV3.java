package hello.servlet.web.frontcontroller.v3.controllers;

import java.util.Map;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

public class MemberSaveControllerV3 implements ControllerV3 {
  MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public ModelView process(Map<String, String> paramMap) {
    ModelView modelView = new ModelView("/save-result");
    Map<String, Object> model = modelView.getModel();

    String username = paramMap.get("username");
    int age = Integer.parseInt(paramMap.get("age"));
    
    Member member = new Member(username, age);
    memberRepository.save(member);
    model.put("member", member);

    return modelView;
  }
}
