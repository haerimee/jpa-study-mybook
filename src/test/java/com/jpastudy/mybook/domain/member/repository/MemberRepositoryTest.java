package com.jpastudy.mybook.domain.member.repository;

import com.jpastudy.mybook.domain.member.domain.Member;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Rollback(false)
    @Test
    public void testSave() {
        Member member = new Member();
        member.setName("장해림");
        Member savedMember = memberRepository.save(member);

        Assertions.assertThat(savedMember.getId()).isNotNull();
        Assertions.assertThat(savedMember.getName()).isEqualTo(member.getName());
    }

    @Test
    public void testFindById() {
        Optional<Member> foundMember = Optional.ofNullable(memberRepository.findById(1L));

        foundMember.ifPresent(selectedMember -> {
            System.out.println("============member name: "+selectedMember.getName());
        });
    }

    @Rollback(false)
    @Test
    public void testUpdate(){
        Optional<Member> foundMember = Optional.ofNullable(memberRepository.findById(1L));

        foundMember.ifPresent(selectedMember -> {
            selectedMember.setName("수정된 장해림");
            memberRepository.save(selectedMember);
        });
    }

    @Rollback(false)
    @Test
    public void testDelete() {
        Optional<Member> foundMember = Optional.ofNullable(memberRepository.findById(1L));

        foundMember.ifPresent(selectedMember -> {
            memberRepository.delete(selectedMember);
        });
    }
}