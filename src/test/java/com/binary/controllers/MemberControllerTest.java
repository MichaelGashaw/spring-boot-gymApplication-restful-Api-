package com.binary.controllers;

import com.binary.entity.Member;
import com.binary.services.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberService memberService;

    @Test
    @DisplayName("member controller get all success")
    public void memberController_getAllMembers_success() {
        List<Member> expectedMembers = new ArrayList<>();
        Member member = new Member();
        member.setId(1L);
        member.setFirstName("John");
        member.setLastName("Doe");
        expectedMembers.add(member);

        Mockito.when(memberService.getAllMembers()).thenReturn(expectedMembers);

        ResponseEntity<List<Member>> result = memberController.getAllMembers();

        Assertions.assertEquals(1, result.getBody().size());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @DisplayName("member controller get by ID success case")
    public void memberController_getMemberById_success() {
        Member member = new Member();
        member.setId(2L);
        long expectedId = 2L;

        Mockito.when(memberService.getMemberById(expectedId)).thenReturn(member);

        ResponseEntity<Member> result = memberController.getMemberById(expectedId);

        Assertions.assertEquals(expectedId, result.getBody().getId());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @DisplayName("member controller create member success")
    public void memberController_createMember_success() {
        // Arrange
        Member memberBeforeCreated = new Member();
        memberBeforeCreated.setFirstName("Mike");
        memberBeforeCreated.setLastName("Doe");

        Member memberCreated = new Member();
        memberCreated.setId(3L);
        memberCreated.setFirstName("Mike");
        memberCreated.setLastName("Doe");

        Mockito.when(memberService.createMember(any(Member.class))).thenReturn(memberCreated);

        // Act
        ResponseEntity<Member> result = memberController.createMember(memberBeforeCreated);

        // Assert
        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertEquals(memberCreated.getFirstName(), result.getBody().getFirstName());
        Assertions.assertEquals(memberCreated.getLastName(), result.getBody().getLastName());
        Assertions.assertNotNull(result.getBody().getId());
        Mockito.verify(memberService, Mockito.times(1)).createMember(any(Member.class));
    }

    @Test
    @DisplayName("member controller update member success")
    public void memberController_updateMember_success() {
        Member memberBeforeUpdated = new Member();
        memberBeforeUpdated.setFirstName("John");
        memberBeforeUpdated.setLastName("Bob");

        long memberIdThatNeedsToBeUpdated = 45L;

        Member memberUpdated = new Member();
        memberUpdated.setId(memberIdThatNeedsToBeUpdated);
        memberUpdated.setFirstName("BOB");
        memberUpdated.setLastName("Smith");

        Mockito.when(memberService.updateMember(Mockito.eq(memberIdThatNeedsToBeUpdated), any(Member.class))).thenReturn(memberUpdated);

        ResponseEntity<Member> result = memberController.updateMember(memberIdThatNeedsToBeUpdated, memberBeforeUpdated);

        Assertions.assertEquals(memberUpdated.getFirstName(), result.getBody().getFirstName());
        Assertions.assertEquals(memberUpdated.getLastName(), result.getBody().getLastName());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @DisplayName("member controller delete member success")
    public void memberController_deleteMember_success() {
        long memberIdThatNeedsToBeDeleted = 45L;

        Mockito.when(memberService.deleteMember(memberIdThatNeedsToBeDeleted)).thenReturn(memberIdThatNeedsToBeDeleted);

        ResponseEntity<Long> result = memberController.deleteMember(memberIdThatNeedsToBeDeleted);

        Assertions.assertEquals(memberIdThatNeedsToBeDeleted, result.getBody());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @DisplayName("member controller get by ID not found case")
    public void memberController_getMemberById_notFound() {
        long memberId = 2L;

        Mockito.when(memberService.getMemberById(memberId)).thenReturn(null);

        ResponseEntity<Member> result = memberController.getMemberById(memberId);

        Assertions.assertEquals(NOT_FOUND, result.getStatusCode());
    }

    @Test
    @DisplayName("member controller update member not found case")
    public void memberController_updateMember_notFound() {
        Member memberToUpdate = new Member();
        memberToUpdate.setFirstName("Jane");
        memberToUpdate.setLastName("j");

        long memberIdThatNeedsToBeUpdated = 45L;

        Mockito.when(memberService.updateMember(Mockito.eq(memberIdThatNeedsToBeUpdated), any(Member.class))).thenReturn(null);

        ResponseEntity<Member> result = memberController.updateMember(memberIdThatNeedsToBeUpdated, memberToUpdate);

        Assertions.assertEquals(NOT_FOUND, result.getStatusCode());
    }

    @Test
    @DisplayName("member controller delete member not found case")
    public void memberController_deleteMember_notFound() {
        long memberIdThatNeedsToBeDeleted = 45L;

        Mockito.when(memberService.deleteMember(memberIdThatNeedsToBeDeleted)).thenReturn(-1L);

        ResponseEntity<Long> result = memberController.deleteMember(memberIdThatNeedsToBeDeleted);

        Assertions.assertEquals(NOT_FOUND, result.getStatusCode());
    }
}

