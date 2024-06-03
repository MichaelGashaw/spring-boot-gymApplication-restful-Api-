package com.binary.services;


import com.binary.entity.Membership;

import java.util.List;

public interface MembershipService {
    List<Membership> getAllMemberships();

    Membership createMembership(Membership membership);

    Membership updateMembership(long id, Membership updatedMembership);

    long deleteMembership(long id);

    Membership getMembershipById(long id);
}
