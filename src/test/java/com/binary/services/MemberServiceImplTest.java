package com.binary.services;

import com.binary.entity.Member;
import com.binary.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMembers() {
        Member member1 = new Member();
        Member member2 = new Member();
        List<Member> members = Arrays.asList(member1, member2);

        when(memberRepository.findAll()).thenReturn(members);

        List<Member> result = memberService.getAllMembers();
        assertEquals(2, result.size());
        verify(memberRepository, times(1)).findAll();
    }

    @Test
    void testCreateMember() {
        Member member = new Member();
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        Member result = memberService.createMember(member);
        assertNotNull(result);
        verify(memberRepository, times(1)).save(member);
    }

    @Test
    void testUpdateMember() {
        Member existingMember = new Member();
        existingMember.setId(1L);
        existingMember.setFirstName("Old Name");

        Member updatedMember = new Member();
        updatedMember.setFirstName("New Name");

        when(memberRepository.findById(1L)).thenReturn(Optional.of(existingMember));
        when(memberRepository.save(any(Member.class))).thenReturn(existingMember);

        Member result = memberService.updateMember(1L, updatedMember);
        assertNotNull(result);
        assertEquals("New Name", result.getFirstName());
        verify(memberRepository, times(1)).findById(1L);
        verify(memberRepository, times(1)).save(existingMember);
    }

    @Test
    void testDeleteMember() {
        Member member = new Member();
        member.setId(1L);

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        long result = memberService.deleteMember(1L);
        assertEquals(1L, result);
        verify(memberRepository, times(1)).findById(1L);
        verify(memberRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetMemberById() {
        Member member = new Member();
        member.setId(1L);

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        Member result = memberService.getMemberById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(memberRepository, times(1)).findById(1L);
    }
}
