package com.ll.medium.domain.member.member.controller;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.domain.member.member.service.MemberService;
import com.ll.medium.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/join")
    public String showJoin(){
        return "domain/member/member/join";
    }

    @Data
    public static class JoinForm{
        @NotBlank
        private  String username;
        @NotBlank
        private String password;
    }

    @PostMapping("/member/join")
    public String doJoin(@Valid JoinForm joinForm){

        RsData<Member> join = memberService.join(joinForm.username, joinForm.password);

        String msg = join.getMsg();

        return "redirect:/member/join";
    }

    @GetMapping("/member/login")
    public String showLogin(){
        return "domain/member/member/login";
    }
}
