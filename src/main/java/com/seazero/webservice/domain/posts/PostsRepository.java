package com.seazero.webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// 보통 ibatis/MyBatis 등에서 Dao라고 불리는 DB Layer접근자이다 | JPA에서는 Repository라고 부르며 인터페이스로 생성한다
// 단순히 인터페이스를 생성후, JpaRepository<Entity클래스, PK타입>을 상속하면 기본적인 CRUD메소드가 자동생성된다. | 특별히 @Repository를 추가할 필요도 없다
public interface PostsRepository  extends JpaRepository<Posts, Long> {
}
