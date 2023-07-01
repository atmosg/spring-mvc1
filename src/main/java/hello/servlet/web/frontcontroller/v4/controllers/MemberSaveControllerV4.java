package hello.servlet.web.frontcontroller.v4.controllers;

import java.util.Map;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

public class MemberSaveControllerV4 implements ControllerV4 {
  MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public String process(Map<String, String> paramMap, Map<String, Object> model) {
    String viewName = "save-result";
    
    Member member = new Member(paramMap.get("username"),  Integer.parseInt(paramMap.get("age")));
    memberRepository.save(member);
    model.put("member", member);

    return viewName;
  }
}