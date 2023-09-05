package com.jung9.shop.service;

import com.jung9.shop.constant.MemberRole;
import com.jung9.shop.dto.MemberDTO;
import com.jung9.shop.entity.MemberEntity;
import com.jung9.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자 정보를 검색하고 UserDetails 객체를 반환하는 로직을 작성
        // 이 예제에서는 사용자 이메일을 사용하여 검색하도록 가정
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(username);
        if (byMemberEmail.isPresent()) {
            MemberEntity memberEntity = byMemberEmail.get();
            return new User(
                    memberEntity.getMemberEmail(),
                    memberEntity.getMemberPassword(),
                    Collections.emptyList() // 사용자 권한 설정 (빈 리스트인 경우 권한 없음)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public void join(MemberDTO memberDTO) {
        // 이메일로 이미 가입된 회원이 있는지 확인
        if (memberRepository.findByMemberEmail(memberDTO.getMemberEmail()).isPresent()) {
            throw new RuntimeException("이미 가입된 회원입니다.");
//            throw new IllegalStateException("이미 가입된 회원입니다.");
        }

        // 1. dto -> entity 변환
        // 2. repository의 save 메소드 호출

         memberDTO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));

        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        memberEntity.setMemberJoinDate(LocalDate.now());
        memberRepository.save(memberEntity);
        // repository의 save 메소드 호출 (조건. entity 객체를 넘겨줘야함)
    }

    public MemberDTO login(MemberDTO memberDTO) {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 함
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;

            } else {
                // 비밀번호 불일치(로그인 실패)
                throw new RuntimeException("패스워드가 일치하지 않습니다.");
            }
        }else {
            // 조회 결과가 없다
            throw new RuntimeException("가입되지 않은 회원입니다.");
        }
    }

    public String emailCheck(String memberEmail) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
        if (byMemberEmail.isPresent()) {
            // 조회 결과가 있다 -> 중복되어 사용할 수 없다.
            return null;
        } else {
            // 조회 결과가 없다 -> 사용할 수 있다.
            return "ok";
        }
    }


}

