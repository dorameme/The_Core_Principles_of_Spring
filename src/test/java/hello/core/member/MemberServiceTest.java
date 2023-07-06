package hello.core.member;
import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {
    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService=ac.getBean("memberService", MemberService.class);
    @Test
    void join(){
        //given
        Member member=new Member(1L,"memberA",Grade.VIP);

        //when
        memberService.join(member);
        Member findMember= memberService.findMember(1L);
        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
