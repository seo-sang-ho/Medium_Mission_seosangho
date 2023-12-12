package com.ll.medium.global.loginMember;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.member.member.service.MemberService;
import groovy.util.logging.Slf4j;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
@Getter
@RequiredArgsConstructor
@Slf4j
public class LoginMember {
    private final MemberService memberService;
    private User user;
    private Member member;

    @PostConstruct
    public void init(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.getPrincipal() instanceof User){
            this.user = (User) authentication.getPrincipal();
        }
    }

    private String getMemberUsername(){
        return user.getUsername();
    }

    public boolean isLogined(){
        return user != null;
    }


    public Member getMember(){
        if(!isLogined()){
            return null;
        }
        if(member == null){
            member = memberService.findByUsername(getMemberUsername()).get();
        }

        return member;
    }
}
