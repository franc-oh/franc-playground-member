package com.playground.member.member.entity;

import com.playground.member.member.dto.MemberDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@SequenceGenerator(
        name = "seq_member_id",
        sequenceName = "MEMBER_ID_SEQ",
        initialValue = 1000000001,
        allocationSize = 1
)

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_member_id")
    private Long member_id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    private String name;

    private String nickname;

    private char sex;

    private String phone;

    private int grade;

    private int member_type;



    @Builder
    public Member(Long member_id, String email, String password, String role,
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

    public MemberDTO toDTO() {
        return MemberDTO.builder()
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

    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
