package com.binary.services;

import com.binary.entity.Membership;
import com.binary.exceptions.ResourceNotFoundException;
import com.binary.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipServiceImpl implements MembershipService {
    @Autowired
    private MembershipRepository membershipRepository;

    @Override
    public List<Membership> getAllMemberships() {
        return (List<Membership>) membershipRepository.findAll();
    }

    @Override
    public Membership createMembership(Membership membership) {
        if (membership == null) {
            return null;
        }
        return membershipRepository.save(membership);
    }

    @Override
    public Membership updateMembership(long id, Membership updatedMembership) {
        Optional<Membership> optionalMembership = membershipRepository.findById(id);
        if (optionalMembership.isPresent()) {
            Membership membership = optionalMembership.get();
            if (updatedMembership.getType() != null) {
                membership.setType(updatedMembership.getType());
            }
            if (updatedMembership.getPrice() > 0) {
                membership.setPrice(updatedMembership.getPrice());
            }
            if (updatedMembership.getDuration() != null) {
                membership.setDuration(updatedMembership.getDuration());
            }
            return membershipRepository.save(membership);
        }
        throw new ResourceNotFoundException("Membership not found for this id :: " + id);
    }

    @Override
    public long deleteMembership(long id) {
        Optional<Membership> optionalMembership = membershipRepository.findById(id);
        if (optionalMembership.isPresent()) {
            membershipRepository.deleteById(id);
            return id;
        }
        throw new ResourceNotFoundException("Membership not found for this id :: " + id);
    }

    @Override
    public Membership getMembershipById(long id) {
        Optional<Membership> membership = membershipRepository.findById(id);
        if (membership.isPresent()) {
            return membership.get();
        }
        throw new ResourceNotFoundException("Membership not found for this id :: " + id);
    }
}
