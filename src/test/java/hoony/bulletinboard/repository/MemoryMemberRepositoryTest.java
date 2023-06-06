package hoony.bulletinboard.repository;

import hoony.bulletinboard.member.domain.Member;
import hoony.bulletinboard.member.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {
    // given when then 문법 이용

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() { // 테스트는 독립적으로 이루어져야 한다, DB 리셋
        repository.clearMemory();
    }


    @Test
    void save() {
        //given
        Member member = new Member("Hoony", "0000");

        //when
        repository.save(member);

        //then
        Member getMember = repository.findByName(member.getName()).get();
        assertThat(getMember).isEqualTo(member);

    }

    @Test
    void findByName() {
        //given
        Member member1 = new Member("Hoony1", "0000");
        Member member2 = new Member("Hoony2", "0000");
        repository.save(member1);
        repository.save(member2);

        //when
        Member getMember = repository.findByName("Hoony1").get();

        //then
        assertThat(getMember).isEqualTo(member1);
        assertThat(getMember).isNotEqualTo(member2);



    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("Hoony1", "0000");
        Member member2 = new Member("Hoony2", "0000");
        Member member3 = new Member("Hoony3", "0000");
        repository.save(member1);
        repository.save(member2);
        repository.save(member3);

        //when
        List<Member> list = repository.findAll();

        //then
        assertThat(list.size()).isEqualTo(3);
//        assertThat(repository.findAll().size()).isEqualTo(3);
    }

    @Test
    void update() {
        //given
        Member member = new Member("Hoony", "0000");
        repository.save(member);
        member.setPassword("1111");

        //when
        repository.update(member);
        Member getMember = repository.findByName(member.getName()).get();

        //then
        assertThat(getMember.getPassword()).isEqualTo("1111");


    }

    @Test
    void delete() { // 예외 테스트 다시 확인
        //given
        Member member = new Member("Hoony", "0000");
        repository.save(member);

        //when
        repository.delete(member);
//        Member getMember = repository.findByName(member.getName()).get(); //NoSuchElementException

        //then
//        assertThat(getMember).isNull();
        assertThrows(NoSuchElementException.class, () -> {
            repository.findByName(member.getName()).get();
        });


    }
}