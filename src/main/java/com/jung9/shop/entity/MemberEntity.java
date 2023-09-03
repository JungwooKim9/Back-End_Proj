package com.jung9.shop.entity;

import com.jung9.shop.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id     // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // auto_increment
    private Long id;

    @Column(unique = true)     // unique 제약조건을 추가
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    @Column
    private String memberBirthDate;

    @Column
    private String memberPhoneNum;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberBirthDate(memberDTO.getMemberBirthDate());
        memberEntity.setMemberPhoneNum(memberDTO.getMemberPhoneNum());
        return memberEntity;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
