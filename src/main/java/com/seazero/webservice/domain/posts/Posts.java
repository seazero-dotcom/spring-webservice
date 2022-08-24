package com.seazero.webservice.domain.posts;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Entity클래스를 프로젝트 코드상에서 기본생성자로 생성하는 것은 막되, JPA에서 Entity 클래스를 생성하는 것을 허용하기 위해 추가
@NoArgsConstructor(access = AccessLevel.PROTECTED) //(Lombok) 기본생성자 자동 추가  | access = AccessLevel.PROTECTED는 기본생성자의 접근 권한을 protected로 제한 | 생성자로 protected Posts() {}와 같은 효과
@Getter //(Lombok) 클래스내 모든 필드의 Getter메소드를 자동생성
@Entity // 테이블과 링크될 클래스임을 나타낸다 | 언더스코어 네이밍(_)으로 이름을 매칭한다. ex) SalesManager.java -> sales_manager table
public class Posts {
// 실제 DB의 테이블과 매칭될 클래스이며 보통 Entity클래스라고 한다
// JPA를 사용하면 DB데이터에 작업할 경우 실제 쿼리를 날리기 보다는, 이 Entity 클래스의 수정을 통해 작업한다

    @Id // 해당 테이블의 PK 필드를 나타낸다
    @GeneratedValue // PK의 생성 규칙을 나타낸다 | 기본값은 AUTO로, MySQL의 auto_increment와 같이 자동증가하는 정수형 값이 된다 | 스프링부트2.0에서는 옵션을 추가해야 auto_increment가 된다
    private Long id;
    /** 웬만하면 Entity의 PK는 Long타입의 Auto_increment를 사용하는것을 추천한다고한다 (MySQL기준으로 이렇게 하면 bigint가 된다)
     * 주민등록번호와 같은 비즈니스상 유니크키나, 여러키를 조합한 복합키로 PK를 잡을 경우 난감한 상황이 올 수 있다
     * 1 ) FK를 맺을 때 다른 테이블에서 복합키 전부를 갖고 있거나, 중간 테이블을 하나더 둬야하는 상황이 발생한다
     * 2 ) 인덱스에 좋은 영향을 끼치지 못한다
     * 3 ) 유니크한 조건이 변경될 경우 PK 전체를 수정해야하는 일이 발생한다
     * 주민등록번호, 복합키 등은 유니크키로 별도로 추가하는 것을 추천한다
     *
     */

    @Column(length = 500, nullable = false) // 테이블의 컬럼을 나타내면, 굳이 이걸 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 된다!
    // 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있을경우 사용한다 | 문자열의 경우 VARCHAR(255)가 기본값인데, (ex; title)사이즈를 500으로 늘리고 싶거나, (ex; contnet)타입을 TEXT로 변경하고 싶거나 등의 경우에 사용된다
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //(Lombok) 해당 클래스의 빌더패턴 클래스를 생성 | 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
