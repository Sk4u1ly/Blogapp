package com.example.project;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SampleTest {
    PasswordEncoder encoder= new BCryptPasswordEncoder();
    public static void main(String[] args) {
        PasswordEncoder encoder= new BCryptPasswordEncoder();
        System.out.println(encoder.encode("post@12"));
    }
}
