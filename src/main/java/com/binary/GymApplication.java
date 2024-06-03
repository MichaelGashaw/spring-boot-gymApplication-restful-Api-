package com.binary;

import com.binary.entity.Member;
import com.binary.entity.Membership;
import com.binary.repository.MemberRepository;
import com.binary.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class GymApplication implements CommandLineRunner {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MembershipRepository membershipRepository;


	public static void main(String[] args) {
		SpringApplication.run(GymApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception {
		Member member1 = new Member("John", "Doe", "john.doe@example.com", "123-456-7890");
		Member member2 = new Member("Jane", "Doe", "jane.doe@example.com", "098-765-4321");
		Member member3 = new Member("Jack", "Smith", "jack.smith@example.com", "555-555-5555");



		Membership membership1 = new Membership("Gold", 50.0, "Monthly", member1);
		Membership membership2 = new Membership("Silver", 30.0, "Monthly", member2);
		Membership membership3 = new Membership("Platinum", 70.0, "Monthly", member3);
		Membership membership4 = new Membership("Gold", 50.0, "Monthly", member1);


		memberRepository.save(member1);
		//System.out.println("memeber 1 is saved");
		memberRepository.saveAll(Arrays.asList(member2, member3));
		System.out.println("rest of the members is saved");

		membershipRepository.save(membership1);
		//System.out.println("membership1 is saved");
		membershipRepository.saveAll(Arrays.asList(membership2, membership3));

		memberRepository.findAll().forEach(member -> System.out.println(member));
		membershipRepository.findAll().forEach(membership -> System.out.println(membership));



	}
}
