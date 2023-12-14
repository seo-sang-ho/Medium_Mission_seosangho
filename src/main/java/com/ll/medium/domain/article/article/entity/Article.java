package com.ll.medium.domain.article.article.entity;

import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString(callSuper = true)
public class Article extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;
    private String title;
    private String body;
    private boolean publish; // 공개 여부
}
