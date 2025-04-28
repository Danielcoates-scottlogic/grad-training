package com.example.friendface;

import java.util.List;

public class ReturnUsersDto {
    private List<String> usernames;

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }
}
