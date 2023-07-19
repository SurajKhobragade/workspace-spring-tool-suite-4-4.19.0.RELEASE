//package org.sr.runner;
//
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.sr.entity.CUSTOM_USER;
//import org.sr.entity.Role;
//import org.sr.repo.UserRepository;
//
//@Component
//public class TestRunner implements CommandLineRunner {
//
//	@Autowired
//	private UserRepository repo;
//
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//
//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("TestRunner.run()");
//		Set<Role> roleSet1 = Set.of(new Role("ROLE_ADMIN"));
//		Set<Role> roleSet2 = Set.of(new Role("ROLE_ADMIN"), new Role("ROLE_USER"));
//
//		CUSTOM_USER user1 = new CUSTOM_USER("John", passwordEncoder.encode("1234"), roleSet1);
//		CUSTOM_USER user2 = new CUSTOM_USER("John1", passwordEncoder.encode("12345"), roleSet2);
//
//		repo.saveAll(List.of(user1, user2));
//	}
//
//}
