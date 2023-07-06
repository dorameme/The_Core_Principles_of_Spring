package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 di 컨테이너")
    void pureContainer(){
        AppConfig appConfig=new AppConfig();
        MemberService memberService1=appConfig.memberService();
        MemberService memberService2=appConfig.memberService();

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }
    @Test
    @DisplayName("스프링컨테이너와 싱글톤")
    void springContainer(){
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1=ac.getBean("memberService",MemberService.class);
        MemberService memberService2=ac.getBean("memberService",MemberService.class);


        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
