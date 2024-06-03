package com.binary;

import com.binary.entity.Member;
import com.binary.entity.Membership;
import com.binary.repository.MemberRepository;
import com.binary.repository.MembershipRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class GymApplicationTests {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MembershipRepository membershipRepository;

	@Test
	void contextLoads() {
		// Ensure the Spring context loads correctly
	}

	@Test
	void testCommandLineRunner() {
		// Test that members are saved correctly
		List<Member> members = (List<Member>) memberRepository.findAll();
		assertEquals(3, members.size());

		Member member1 = members.stream().filter(m -> m.getFirstName().equals("John")).findFirst().orElse(null);
		assertNotNull(member1);
		assertEquals("Doe", member1.getLastName());

		Member member2 = members.stream().filter(m -> m.getFirstName().equals("Jane")).findFirst().orElse(null);
		assertNotNull(member2);
		assertEquals("Doe", member2.getLastName());

		Member member3 = members.stream().filter(m -> m.getFirstName().equals("Jack")).findFirst().orElse(null);
		assertNotNull(member3);
		assertEquals("Smith", member3.getLastName());

		// Test that memberships are saved correctly
		List<Membership> memberships = (List<Membership>) membershipRepository.findAll();
		assertEquals(3, memberships.size());

		Membership membership1 = memberships.stream().filter(m -> m.getType().equals("Gold")).findFirst().orElse(null);
		assertNotNull(membership1);
		assertEquals(50.0, membership1.getPrice());

		Membership membership2 = memberships.stream().filter(m -> m.getType().equals("Silver")).findFirst().orElse(null);
		assertNotNull(membership2);
		assertEquals(30.0, membership2.getPrice());

		Membership membership3 = memberships.stream().filter(m -> m.getType().equals("Platinum")).findFirst().orElse(null);
		assertNotNull(membership3);
		assertEquals(70.0, membership3.getPrice());
	}
}
