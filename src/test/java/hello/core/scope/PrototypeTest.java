package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.SQLOutput;

public class PrototypeTest {
    @Test
    public void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        ProtoTypeBean protoTypeBean1=ac.getBean(ProtoTypeBean.class);
        ProtoTypeBean protoTypeBean2=ac.getBean(ProtoTypeBean.class);
        System.out.println(protoTypeBean1);
        System.out.println(protoTypeBean2);
        Assertions.assertThat(protoTypeBean2).isNotSameAs(protoTypeBean1);
        ac.close();
    }

    @Scope("prototype")
    static class ProtoTypeBean{
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }
        @PreDestroy
        public void destory(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
