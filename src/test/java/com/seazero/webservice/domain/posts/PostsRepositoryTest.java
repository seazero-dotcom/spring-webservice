package com.seazero.webservice.domain.posts;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        /** 이후 테스트 코드에 영향을 끼치지 않기 위해
         * 테스트 메소드가 끝날때 마다 repository 전체를 비우는 코드
         */
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given, when, then은 BDD(Behaviour-Driven Development)에서 사용하는 용어이다 | 전문 BDD프레임워크로 Groovy기반의 Spock를 많이 사용하고 있다

        //given -> 테스트 기반 환경을 구축하는 단계 | 여기선 @builder의 사용법도 같이 확인
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .content("테스트 본문")
                .author("seazerodotcom")
                .build());

        //when -> 테스트 하고자 하는 행위 선언 | 여기선 Posts가 DB에 insert 되는것을 확인하기 위함
        List<Posts> postsList = postsRepository.findAll();

        //then -> 테스트 결과 검증 | 실제로 DB에 insert 되었는지 확인하기 위해 조회 후, 입력된 값 확인
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is("테스트 게시글"));
        assertThat(posts.getContent(), is("테스트 본문"));
    }
}
