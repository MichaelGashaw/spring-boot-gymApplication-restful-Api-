package com.binary.services;

import com.binary.entity.Member;
import com.binary.exceptions.ResourceNotFoundException;
import com.binary.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> getAllMembers() {
        return (List<Member>) memberRepository.findAll();
    }

    @Override
    public Member createMember(Member member) {
        if (member == null) {
            return null;
        }
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(long id, Member updatedMember) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            if (updatedMember.getFirstName() != null) {
                member.setFirstName(updatedMember.getFirstName());
            }
            if (updatedMember.getLastName() != null) {
                member.setLastName(updatedMember.getLastName());
            }
            if (updatedMember.getEmail() != null) {
                member.setEmail(updatedMember.getEmail());
            }
            if (updatedMember.getPhone() != null) {
                member.setPhone(updatedMember.getPhone());
            }
            return memberRepository.save(member);
        }
        throw new ResourceNotFoundException("Member not found for this id :: " + id);
    }

    @Override
    public long deleteMember(long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            memberRepository.deleteById(id);
            return id;
        }
        throw new ResourceNotFoundException("Member not found for this id :: " + id);
    }

    @Override
    public Member getMemberById(long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return member.get();
        }
        throw new ResourceNotFoundException("Member not found for this id :: " + id);
    }
}
