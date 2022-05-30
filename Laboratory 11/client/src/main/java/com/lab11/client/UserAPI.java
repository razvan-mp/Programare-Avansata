package com.lab11.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UserAPI {

    private final String baseUrl = "http://localhost:8080/";

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    public void getUserList() {
        UriComponents uriBuilder = UriComponentsBuilder
                .fromUriString(baseUrl + "/users").build();
        System.out.println(restTemplate.exchange(uriBuilder.toUri(), HttpMethod.GET, null, Object.class).getBody());
    }

    public void addNewPerson(String name) {
        UriComponents uriBuilder = UriComponentsBuilder
                .fromUriString(baseUrl + "/users/add_user?name=" + name).build();
        restTemplate.exchange(uriBuilder.toUri(), HttpMethod.POST, null, String.class);
        System.out.println("User " + name + " has been added");
    }

    public void modifyPerson(String oldName, String newName) {
        UriComponents uriBuilder = UriComponentsBuilder
                .fromUriString(baseUrl + "/users/modify_user?old_name=" + oldName + "&new_name=" + newName).build();
        restTemplate.exchange(uriBuilder.toUri(), HttpMethod.PUT, null, String.class);
        System.out.println(oldName + " has been changed to " + newName);
    }

    public void insertFriendship(String firstPerson, String secondPerson) {
        UriComponents uriBuilder = UriComponentsBuilder
                .fromUriString(baseUrl + "/friendships/add_friendship?first_name=" + firstPerson + "&second_name=" + secondPerson).build();
        restTemplate.exchange(uriBuilder.toUri(), HttpMethod.POST, null, String.class);
        System.out.println(firstPerson + " is now friends with " + secondPerson);
    }


    public void readFriendships() {
        UriComponents uriBuilder = UriComponentsBuilder
                .fromUriString(baseUrl + "/friendships/read_friendships").build();
        System.out.println(restTemplate.exchange(uriBuilder.toUri(), HttpMethod.GET, null, String.class).getBody());
    }

    public void deleteUser(String name) {
        UriComponents uriBuilder = UriComponentsBuilder
                .fromUriString(baseUrl + "/users/delete_user?name=" + name).build();
        restTemplate.exchange(uriBuilder.toUri(), HttpMethod.DELETE, null, String.class);
        System.out.println("User " + name + " has been deleted");
    }
}
