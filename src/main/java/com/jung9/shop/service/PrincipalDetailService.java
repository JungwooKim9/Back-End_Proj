package com.jung9.shop.service;

import com.jung9.shop.entity.MemberEntity;
import com.jung9.shop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    private MemberRepository memberRepository;

    @Autowired
    public PrincipalDetailService(MemberRepository usmemberRepositoryerRepository) {
        this.memberRepository = memberRepository;
    }

    //스프링이 로그인 요청을 가로챌때 username, password변수 2개를 가로채는데
    //password 부분 처리는 알아서처리,
    //username이 DB에 있는지 확인해줘야함
    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        // DB에서 사용자를 찾습니다. 사용자가 존재하지 않을 경우 예외를 던집니다.
        MemberEntity member = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다: " + memberEmail));

        // Spring Security에서 사용할 UserDetails 객체를 생성합니다.
        // 여기서는 사용자 정보와 권한을 설정합니다. 사용자 정보가 더 복잡한 경우 UserDetails 구현체를 직접 만들 수 있습니다.
        UserDetails userDetails = User.builder()
                .username(member.getMemberEmail()) // 사용자명은 DB에서 가져온 이메일을 사용합니다.
                .password(member.getMemberPassword()) // 비밀번호는 DB에서 가져온 비밀번호를 사용합니다.
                .roles("USER") // 사용자의 권한을 설정합니다. 필요에 따라 더 복잡한 권한 설정 가능.
                .build();

        return userDetails;
    }


}