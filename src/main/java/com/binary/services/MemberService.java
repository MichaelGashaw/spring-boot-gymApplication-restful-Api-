package com.binary.services;

import com.binary.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MemberService {

    public List<Member> getAllMembers();
    Member createMember(Member member);
    Member updateMember(long id, Member member);
    long deleteMember(long id);
    Member getMemberById(long id);
}
