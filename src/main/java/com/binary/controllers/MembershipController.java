package com.binary.controllers;

import com.binary.entity.Membership;
import com.binary.services.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/memberships")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;
    //http://localhost:8080/api/v1/memberships/list
    @GetMapping("/list")
    public ResponseEntity<List<Membership>> getAllMemberships() {
        return new ResponseEntity<>(membershipService.getAllMemberships(), HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/memberships/create
    @PostMapping("/create")
    public ResponseEntity<Membership> createMembership(@RequestBody Membership membership) {
        Membership createdMembership = membershipService.createMembership(membership);
        return new ResponseEntity<>(createdMembership, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/v1/memberships/update/id
    @PutMapping("/update/{id}")
    public ResponseEntity<Membership> updateMembership(@PathVariable long id, @RequestBody Membership updatedMembership) {
        Membership membership = membershipService.updateMembership(id, updatedMembership);
        if (membership != null) {
            return new ResponseEntity<>(membership, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //http://localhost:8080/api/v1/memberships/delete/1
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteMembership(@PathVariable long id) {
        long deletedId = membershipService.deleteMembership(id);
        if (deletedId != -1) {
            return new ResponseEntity<>(deletedId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membership> getMembershipById(@PathVariable long id) {
        Membership membership = membershipService.getMembershipById(id);
        if (membership != null) {
            return new ResponseEntity<>(membership, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}