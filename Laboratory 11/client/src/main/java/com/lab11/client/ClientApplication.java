package com.lab11.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		UserAPI userAPI = new UserAPI();
		userAPI.getUserList();
		userAPI.addNewPerson("Gigi");
		userAPI.getUserList();
		userAPI.modifyPerson("Gigi", "Gheorghe");
		userAPI.insertFriendship("Laur", "Gheorghe");
		userAPI.getUserList();
		userAPI.readFriendships();
		userAPI.deleteUser("Gheorghe");
		userAPI.getUserList();
	}
}
