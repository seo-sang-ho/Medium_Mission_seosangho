package com.ll.medium.domain.member.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Member {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String username;
    private String password;

    public Member() {
    }

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isAdmin(){
        return username.equals("admin");
    }

    public List<SimpleGrantedAuthority> getAuthorities(){
        if(isAdmin()){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_MEMBER"));
        }

        return List.of(new SimpleGrantedAuthority("ROLE_MEMBER"));
    }

}
