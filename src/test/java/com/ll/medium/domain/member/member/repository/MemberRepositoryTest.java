package com.ll.medium.domain.member.member.repository;

import com.ll.medium.domain.member.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    }