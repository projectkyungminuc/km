package com.example.member.service;

import com.example.member.dto.MemberDTO;
import com.example.member.entity.MemberEntity;
import com.example.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service//스프링이 관리해주는 객체 == 스프링 빈
@RequiredArgsConstructor //controller와 같이. final 멤버변수 생성자 만드는 역할
public class MemberService {

    private final MemberRepository memberRepository; // 먼저 jpa, mysql dependency 추가

    public String save(MemberDTO memberDTO) {
        /*// repsitory의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        //Repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)*/

        if(memberDTO.getMemberPassword().equals(memberDTO.getMemberPassword2())) { // 입력된 비밀번호와 비밀번호 확인을 비교
            System.out.println("비밀번호 확인 통과");

            Optional<MemberEntity> byMemberName = memberRepository.findByMemberName(memberDTO.getMemberName());
            Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberDTO.getMemberId());
            Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
            if(byMemberName.isEmpty() & byMemberId.isEmpty() & byMemberEmail.isEmpty()){
                // 중복되는 이메일 조회 결과가 없다
                MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
                memberRepository.save(memberEntity);
                System.out.println("조회 결과 없음");
                return "login";
            } else {
                System.out.println("조회 결과 존재");
                // 조회 결과가 있다
                return "save";
            }
        } // 비밀번호확인
        else {
            System.out.println("비밀번호 오류");
            return "save";
        }
    }

    public MemberDTO login(MemberDTO memberDTO){ //entity객체는 service에서만
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(byMemberEmail.isPresent()){
            // 조회 결과가 있다
            MemberEntity memberEntity = byMemberEmail.get(); // Optional에서 꺼냄
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                //비밀번호 일치
                //entity -> dto 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                //비밀번호 불일치
                return null;
            }
        } else {
            // 조회 결과가 없다
            return null;
        }
    }

}