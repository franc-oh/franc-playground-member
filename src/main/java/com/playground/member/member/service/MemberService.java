package com.playground.member.member.service;

import com.playground.member.member.dto.MemberDTO;
import com.playground.member.member.entity.Member;
import com.playground.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {

    final MemberRepository memberRepository;

    final PasswordEncoder passwordEncoder;


    /**
     * 유저 생성
     * @param reqMemberDTO
     * @return
     */
    public MemberDTO insertMember(MemberDTO reqMemberDTO) {

        String encodePassword = passwordEncoder.encode(reqMemberDTO.getPassword());
        reqMemberDTO.setPassword(encodePassword);

        Member member = memberRepository.save(reqMemberDTO.toEntity());

        log.info("insert member is {}", member.toString());

        return member.toDTO();
    }


    /**
     * 인증/인가 DB 회원 풀로 연동
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Member Not Found : " + username));

        return User.builder()
                    .username(member.getEmail())
                    .password(member.getPassword())
                    .roles(member.getRole())
                .build();
    }
}
