package com.playground.member.member.dto;

import com.playground.member.member.entity.Member;
import lombok.*;

@ToString
@Getter @Setter
@NoArgsConstructor
public class MemberDTO {

    private Long member_id;

    private String email;

    private String password;

    private String role;

    private String name;

    private String nickname;

    private char sex;

    private String phone;

    private int grade;

    private int member_type;



    @Builder
    public MemberDTO(Long member_id, String email, String password, String role,
                     String name, String nickname, char sex, String phone, int grade, int member_type) {
        this.member_id = member_id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.nickname = nickname;
        this.sex = sex;
        this.phone = phone;
        this.grade = grade;
        this.member_type = member_type;

    }

    public Member toEntity() {
        return Member.builder()
                    .member_id(member_id)
                    .email(email)
                    .password(password)
                    .role(role)
                    .name(name)
                    .nickname(nickname)
                    .sex(sex)
                    .phone(phone)
                    .grade(grade)
                    .member_type(member_type)
                .build();
    }
}
