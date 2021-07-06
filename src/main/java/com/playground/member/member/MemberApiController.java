package com.playground.member.member;

import com.playground.member.member.dto.MemberDTO;
import com.playground.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

    final MemberService memberService;

    @PostMapping("/api/v1/member")
    public MemberDTO insertMember(@RequestBody MemberDTO reqMemberDTO) {

        log.info("signIn info {}", reqMemberDTO.toString());

        return memberService.insertMember(reqMemberDTO);
    }

}
