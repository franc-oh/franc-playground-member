package com.playground.member.member;


import com.playground.member.member.dto.MemberDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * 회원가입 테스트
     * @throws Exception
     */
    @Test
    @Transactional
    public void signin() throws Exception {
        String url = "http://localhost:" + port + "/api/v1/member";
        String email = "user9@naver.com";
        String password = "u123";
        String role = "USER";


        HttpEntity<MemberDTO> reqEntity = new HttpEntity<>(
                                            MemberDTO.builder()
                                                        .email(email)
                                                        .password(password)
                                                        .role(role)
                                                        .build());
        ResponseEntity<String> resEntity = restTemplate.exchange(url, HttpMethod.POST, reqEntity, String.class);

        assertThat(resEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(resEntity.getBody().contains(email), is(true));

    }

}