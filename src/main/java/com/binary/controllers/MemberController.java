package com.binary.controllers;


import com.binary.entity.Member;
import com.binary.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    private MemberService memberService;
    //http://localhost:8080/api/v1/members/list
    @GetMapping("/list")
    public ResponseEntity<List<Member>> getAllMembers() {
        return new ResponseEntity<>(memberService.getAllMembers(), HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/members/create
    @PostMapping("/create")
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member createdMember = memberService.createMember(member);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/v1/members/update/1
    @PutMapping("/update/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable long id, @RequestBody Member updatedMember) {
        Member member = memberService.updateMember(id, updatedMember);
        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    ////http://localhost:8080/api/v1/members/delete/1
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteMember(@PathVariable long id) {
        long deletedId = memberService.deleteMember(id);
        if (deletedId != -1) {
            return new ResponseEntity<>(deletedId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable long id) {
        Member member = memberService.getMemberById(id);
        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}