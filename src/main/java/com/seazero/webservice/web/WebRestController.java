package com.seazero.webservice.web;

import com.seazero.webservice.domain.posts.Posts;
import com.seazero.webservice.domain.posts.PostsRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WebRestController {

    private PostsRepository postsRepository;
    /** 스프링프레임워크에서는 Bean을 주입받는 방식들이 아래와 같습니다.
     * 1 ) @Autowired
     * 2 ) setter
     * 3 ) 생성자
     * 이중 가장 권장하는 방식이 생성자로 주입받는 방식입니다 (@Autowired는 비권장방식)
     * 즉, 생성자로 Bean 객체를 받도록 하면 @Autowired와 동일한 효과를 볼 수 있다는 것이다
     * 여기서 생성자는 @AllArgsConstructor에서 해결해준다
     * 모든 필드를 인자값으로 생성자를 Lombok의 @AllArgsConstructor이 대신 생성해준다
     * 위 코드는 실제로는 아래와 같은 형태이다
     * @RestController
     * public class WebRestController {
     *
     *     private PostsRepository postsRepository;
     *
     *     public WebRestController(PostsRepository postsRepository){
     *         this.postsRepository = postsRepository;
     *     }
     *     ...
     * }
     * 생성자를 직접 안쓰고 Lombok어노테이션을 사용한 이유는 해당 클래스의 의존성 관계가 변경될때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결하기 위함이다
     * (Lombok 어노테이션이 있으면 해당 컨트롤러에 새로운 서비스를 추가하거나, 기존 컴포넌트를 제거하는 등이 발생해도 생성자 코드는 전혀 손대지 않아도 된다)
     */

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld!!!!0000000";
    }

    @PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto dto){
        postsRepository.save(dto.toEntity());
    }

    @Getter
    @Setter // Controller에서 @RequestBody로 외부에서 데이터를 받는 경우에는 [기본생성자 + set메소드 ]를 통해서만 값이 할당되기때문에 이때만 setter를 허용한다
    @NoArgsConstructor
    public class PostsSaveRequestDto {
        private String title;
        private String content;
        private String author;

        public Posts toEntity(){
            return Posts.builder()
                    .title(title)
                    .content(content)
                    .author(author)
                    .build();
        }
    }

    /** Entity 클래스(Posts.java)와 거의 유사한 형태임에도 DTO 클래스를 추가로 생성했다
     * 절대로 테이블과 매핑되는 Entity클래스를 Request/Response클래스로 사용해서는 안된다!!!
     * Entity클래스는 가장 Core한 클래스이고 수많은 서비스 클래스나 비즈니스 로직들이 Entity클래스를 기준으로 동작한다
     * Entity클래스가 변경되면 여러 클래스에 영향을 끼치게 되는 반면 Request와  Response용 DTO는 View를 위한 클래스라서 자주 변경이 필요하다
     * View Layer와 DB Layer를 철저하게 역할 분리를 하는게 좋다
     * 실제로 Controller에서 결과값으로 여러 테이블을 조인해서 줘야할 경우가 빈번하기 때문에 Entity 클래스만으로 표현하기가 어려운 경우가 많다
     * ***** 꼭 Entity 클래스와 Controller에서 쓸 DTO는 분리해서 사용하기 *****
     *
     */

}
