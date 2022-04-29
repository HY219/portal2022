package kr.ac.jejunu.userdao;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LombokTests {
    @Test
    public void equals() {
        //user라는 Object 탄생
        //valiable에 대한 ..
        User user = User.builder().id(1).name("hulk").password("1234").build();
        User user2 = User.builder().id(1).name("hulk").password("1234").build();
        assertThat(user, is(user2));
    }
}
