package com.example.project;

import com.example.project.entites.Role;
import com.example.project.repositries.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper (){                      //  separate library for create object

		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		Role adminRole=new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);

		Role userRole=new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);
	}
}
