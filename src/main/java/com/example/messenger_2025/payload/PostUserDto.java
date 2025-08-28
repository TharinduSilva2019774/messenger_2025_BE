package com.example.messenger_2025.payload;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class PostUserDto {

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String clarkId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClarkId() {
        return clarkId;
    }

    public void setClarkId(String clarkId) {
        this.clarkId = clarkId;
    }
}
