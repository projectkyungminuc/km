package com.example.member.repository;

import com.example.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>
{
    Optional<MemberEntity> findByMemberName(String memberName);
    Optional<MemberEntity> findByMemberId(String memberId);
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
//MemberRepository.interface