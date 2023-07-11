package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {
    @Test
    public void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1=ac.getBean(ClientBean.class);
        int count1  =clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2=ac.getBean(ClientBean.class);
        int count2  =clientBean2.logic();
        assertThat(count2).isEqualTo(1);

    }


    @RequiredArgsConstructor
    static class ClientBean {

//        @Autowired
        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;


        public int logic(){
            PrototypeBean prototypeBean=prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            int count= prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("init");
        }

        @PreDestroy
        public void destory() {
            System.out.println("end");
        }

    }
}