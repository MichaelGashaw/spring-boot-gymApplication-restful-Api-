package com.binary.controllers;

import com.binary.entity.Membership;
import com.binary.services.MembershipService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
public class MembershipControllerTest {

    @InjectMocks
    private MembershipController membershipController;

    @Mock
    private MembershipService membershipService;

    @Test
    @DisplayName("membership controller get all success")
    public void membershipController_getAllMemberships_success() {
        List<Membership> expectedMemberships = new ArrayList<>();
        Membership membership = new Membership();
        membership.setId(1L);
        membership.setType("Premium");
        expectedMemberships.add(membership);

        Mockito.when(membershipService.getAllMemberships()).thenReturn(expectedMemberships);

        ResponseEntity<List<Membership>> result = membershipController.getAllMemberships();

        Assertions.assertEquals(1, result.getBody().size());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @DisplayName("membership controller get by ID success case")
    public void membershipController_getMembershipById_success() {
        Membership membership = new Membership();
        membership.setId(2L);
        long expectedId = 2L;

        Mockito.when(membershipService.getMembershipById(expectedId)).thenReturn(membership);

        ResponseEntity<Membership> result = membershipController.getMembershipById(expectedId);

        Assertions.assertEquals(expectedId, result.getBody().getId());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @DisplayName("membership controller create membership success")
    public void membershipController_createMembership_success() {
        Membership membershipBeforeCreated = new Membership();
        membershipBeforeCreated.setType("Basic");

        Membership membershipCreated = new Membership();
        membershipCreated.setId(3L);
        membershipCreated.setType("Basic");

        Mockito.when(membershipService.createMembership(any(Membership.class))).thenReturn(membershipCreated);

        ResponseEntity<Membership> result = membershipController.createMembership(membershipBeforeCreated);

        Assertions.assertEquals(membershipBeforeCreated.getType(), result.getBody().getType());
        Assertions.assertNotNull(result.getBody().getId());
        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    @DisplayName("membership controller update membership success")
    public void membershipController_updateMembership_success() {
        Membership membershipBeforeUpdated = new Membership();
        membershipBeforeUpdated.setType("Basic");

        long membershipIdThatNeedsToBeUpdated = 45L;

        Membership membershipUpdated = new Membership();
        membershipUpdated.setId(membershipIdThatNeedsToBeUpdated);
        membershipUpdated.setType("Premium");

        Mockito.when(membershipService.updateMembership(eq(membershipIdThatNeedsToBeUpdated), any(Membership.class))).thenReturn(membershipUpdated);

        ResponseEntity<Membership> result = membershipController.updateMembership(membershipIdThatNeedsToBeUpdated, membershipBeforeUpdated);

        Assertions.assertEquals(membershipUpdated.getType(), result.getBody().getType());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @DisplayName("membership controller delete membership success")
    public void membershipController_deleteMembership_success() {
        long membershipIdThatNeedsToBeDeleted = 45L;

        Mockito.when(membershipService.deleteMembership(membershipIdThatNeedsToBeDeleted)).thenReturn(membershipIdThatNeedsToBeDeleted);

        ResponseEntity<Long> result = membershipController.deleteMembership(membershipIdThatNeedsToBeDeleted);

        Assertions.assertEquals(membershipIdThatNeedsToBeDeleted, result.getBody());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    @DisplayName("membership controller get by ID not found case")
    public void membershipController_getMembershipById_notFound() {
        long membershipId = 2L;

        Mockito.when(membershipService.getMembershipById(membershipId)).thenReturn(null);

        ResponseEntity<Membership> result = membershipController.getMembershipById(membershipId);

        Assertions.assertEquals(NOT_FOUND, result.getStatusCode());
    }

    @Test
    @DisplayName("membership controller update membership not found case")
    public void membershipController_updateMembership_notFound() {
        Membership membershipToUpdate = new Membership();
        membershipToUpdate.setType("Basic");

        long membershipIdThatNeedsToBeUpdated = 45L;

        Mockito.when(membershipService.updateMembership(eq(membershipIdThatNeedsToBeUpdated), any(Membership.class))).thenReturn(null);

        ResponseEntity<Membership> result = membershipController.updateMembership(membershipIdThatNeedsToBeUpdated, membershipToUpdate);

        Assertions.assertEquals(NOT_FOUND, result.getStatusCode());
    }

    @Test
    @DisplayName("membership controller delete membership not found case")
    public void membershipController_deleteMembership_notFound() {
        long membershipIdThatNeedsToBeDeleted = 45L;

        Mockito.when(membershipService.deleteMembership(membershipIdThatNeedsToBeDeleted)).thenReturn(-1L);

        ResponseEntity<Long> result = membershipController.deleteMembership(membershipIdThatNeedsToBeDeleted);

        Assertions.assertEquals(NOT_FOUND, result.getStatusCode());
    }
}


