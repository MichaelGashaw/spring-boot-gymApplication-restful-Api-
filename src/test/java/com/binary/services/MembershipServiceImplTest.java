package com.binary.services;

import com.binary.entity.Membership;
import com.binary.repository.MembershipRepository;
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

class MembershipServiceImplTest {

    @Mock
    private MembershipRepository membershipRepository;

    @InjectMocks
    private MembershipServiceImpl membershipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMemberships() {
        Membership membership1 = new Membership();
        Membership membership2 = new Membership();
        List<Membership> memberships = Arrays.asList(membership1, membership2);

        when(membershipRepository.findAll()).thenReturn(memberships);

        List<Membership> result = membershipService.getAllMemberships();
        assertEquals(2, result.size());
        verify(membershipRepository, times(1)).findAll();
    }

    @Test
    void testCreateMembership() {
        Membership membership = new Membership();
        when(membershipRepository.save(any(Membership.class))).thenReturn(membership);

        Membership result = membershipService.createMembership(membership);
        assertNotNull(result);
        verify(membershipRepository, times(1)).save(membership);
    }

    @Test
    void testUpdateMembership() {
        Membership existingMembership = new Membership();
        existingMembership.setId(1L);
        existingMembership.setType("Old Type");

        Membership updatedMembership = new Membership();
        updatedMembership.setType("New Type");
        updatedMembership.setPrice(100);
        updatedMembership.setDuration("1 Year");

        when(membershipRepository.findById(1L)).thenReturn(Optional.of(existingMembership));
        when(membershipRepository.save(any(Membership.class))).thenReturn(existingMembership);

        Membership result = membershipService.updateMembership(1L, updatedMembership);
        assertNotNull(result);
        assertEquals("New Type", result.getType());
        assertEquals(100, result.getPrice());
        assertEquals("1 Year", result.getDuration());
        verify(membershipRepository, times(1)).findById(1L);
        verify(membershipRepository, times(1)).save(existingMembership);
    }

    @Test
    void testDeleteMembership() {
        Membership membership = new Membership();
        membership.setId(1L);

        when(membershipRepository.findById(1L)).thenReturn(Optional.of(membership));

        long result = membershipService.deleteMembership(1L);
        assertEquals(1L, result);
        verify(membershipRepository, times(1)).findById(1L);
        verify(membershipRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetMembershipById() {
        Membership membership = new Membership();
        membership.setId(1L);

        when(membershipRepository.findById(1L)).thenReturn(Optional.of(membership));

        Membership result = membershipService.getMembershipById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(membershipRepository, times(1)).findById(1L);
    }
}
