# The_Core_Principles_of_Spring을 만들며 배운것

-회원 도메인 개발-

HashMap 은 동시성 이슈가 발생할 수 있다. 이런 경우 ConcurrentHashMap 을 사용하자.

동시성이슈란->
여러 프로세스 및 스레드가 동시에 동일한 데이터(공유 데이터)를 조작할 때 타이밍이나 접근 순서에 따라 예상했던 결과가 달라질 수 있는 상황