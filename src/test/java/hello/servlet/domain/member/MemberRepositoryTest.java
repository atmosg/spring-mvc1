package hello.servlet.domain.member;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MemberRepositoryTest {
  MemberRepository memberRepository = MemberRepository.getInstance();
  
  @AfterEach
  void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  void save() {
    //given
    Member member = new Member("김씨", 20);

    //when
    Member saved = memberRepository.save(member);

    //then
    Member found = memberRepository.findById(saved.getId());
    Assertions.assertThat(saved).isEqualTo(found);
  }

  @Test
  void findAll() {
    Member member1 = new Member("김씨", 20);
    Member member2 = new Member("나씨", 30);
    memberRepository.save(member1);
    memberRepository.save(member2);
    
    List<Member> foundList = memberRepository.findAll();

    Assertions.assertThat(foundList).contains(member1, member2);
  }
}
