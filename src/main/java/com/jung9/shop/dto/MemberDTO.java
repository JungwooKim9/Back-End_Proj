package com.jung9.shop.dto;

import com.jung9.shop.constant.MemberRole;
import com.jung9.shop.entity.MemberEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberBirthDate;
    private String memberPhoneNum;
    private String memberAddress;
    private LocalDate memberJoinDate;
    private MemberRole memberRole;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberBirthDate(memberEntity.getMemberBirthDate());
        memberDTO.setMemberPhoneNum(memberEntity.getMemberPhoneNum());
        memberDTO.setMemberAddress(memberEntity.getMemberAddress());
        memberDTO.setMemberJoinDate(memberEntity.getMemberJoinDate());
        memberDTO.setMemberRole(memberEntity.getMemberRole());
        return memberDTO;
    }
}
