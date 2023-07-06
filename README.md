# The_Core_Principles_of_Spring을 만들며 배운것

-예제 만들기-

HashMap 은 동시성 이슈가 발생할 수 있다. 이런 경우 ConcurrentHashMap 을 사용하자.

동시성이슈란->
여러 프로세스 및 스레드가 동시에 동일한 데이터(공유 데이터)를 조작할 때 타이밍이나 접근 순서에 따라 예상했던 결과가 달라질 수 있는 상황

회원 도메인 설계의 문제점
이 코드의 설계상 문제점-> 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음
다른 저장소로 변경할 때 OCP 원칙을 준수하지못하고?
DIP를 잘 지키고 있지 않다.

-객체 지향 원리 적용-
할인 정책을 변경하려면 클라이언트인 OrderServiceImpl 코드를 고쳐야 한다.
결국 OCP(개방 폐쇄 원칙),DIP(의존 역전 원칙)같은 객체지향 설계 원칙을 지키지 못하게된다.
따라서 해결방안으로 따로 누군가가 클라이언트의 구현 객체를 대신 생성하고 주입해주어야 한다.->(인터페이스에만 의존하도록 설계를 변경하자)
이러한 역할을 해주는 것이  AppConfig
AppConfig가 구체 클래스를 선택한다.  애플리케이션이 어떻게 동작해야 할지 전체 구성을 책임진다.

IoC
프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 제어의 역전(IoC)

프레임워크 vs 라이브러리
프레임워크가 내가 작성한 코드를 제어하고, 대신 실행하면 그것은 프레임워크가 맞다. (JUnit)
반면에 내가 작성한 코드가 직접 제어의 흐름을 담당한다면 그것은 프레임워크가 아니라 라이브러리다.


정적인 클래스 의존관계
클래스가 사용하는 import 코드만 보고 의존관계를 쉽게 판단할 수 있다.
동적인 객체 인스턴스 의존 관계
애플리케이션 실행 시점에 실제 생성된 객체 인스턴스의 참조가 연결된 의존 관계다.

AppConfig 
객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을(ex.AppConfig) IoC 컨테이너 또는 DI 컨테이너라 한다.

AppConfig에 설정을 구성한다는 뜻의 @Configuration 을 붙여준다.
각 메서드에 @Bean 을 붙여준다. 이렇게 하면 스프링 컨테이너에 스프링 빈으로 등록한다.

-스프링 컨테이너와 스프링 빈-

스프링 컨테이너에서 스프링 빈을 찾는 가장 기본적인 조회 방법
ac.getBean(빈이름, 타입)/ac.getBean(타입)

스프링 빈 조회 - 상속 관계
부모 타입으로 조회하면, 자식 타입도 함께 조회한다.

BeanFactory
스프링 컨테이너의 최상위 인터페이스다.
스프링 빈을 관리하고 조회하는 역할을 담당한다.
getBean() 을 제공한다.
지금까지 우리가 사용했던 대부분의 기능은 BeanFactory가 제공하는 기능이다.
ApplicationContext
BeanFactory 기능을 모두 상속받아서 제공한다.
빈을 관리하고 검색하는 기능을 BeanFactory가 제공해주는데, 애플리케이션을 개발할 때는 빈을 관리하고 조회하는 기능은 물론이고, 수 많은 부가기능이 필요하다
따라서 applicationContext이용

eanDefinition 이라는 추상화가 있어서 다양한 설정 형식을 지원-> 역할과 구현의 개념을 나눈것
XML을 읽어서 BeanDefinition을 만들면 된다.
자바 코드를 읽어서 BeanDefinition을 만들면 된다.
스프링 컨테이너는 자바 코드인지, XML인지 몰라도 된다. 오직 BeanDefinition만 알면 된다.
BeanDefinition 을 빈 설정 메타정보라 한다.
@Bean , <bean> 당 각각 하나씩 메타 정보가 생성된다.
스프링 컨테이너는 이 메타정보를 기반으로 스프링 빈을 생성한다.