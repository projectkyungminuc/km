package com.example.member.dto;

import com.example.member.entity.MemberEntity;
import com.example.member.repository.MemberRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//lombok dependency추가
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO { //회원 정보를 필드로 정의
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberPassword2;
    private String memberName;
    private String memberId;
    private String memberPhoneNumber;


    //lombok 어노테이션으로 getter,setter,생성자,toString 메서드 생략 가능

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberPassword2(memberEntity.getMemberPassword2());
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setMemberPhoneNumber(memberEntity.getMemberPhoneNumber());

        return memberDTO;
    }

}
