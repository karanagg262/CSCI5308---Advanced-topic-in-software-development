package com.triplify.app.model;

public interface IAuthenticationResult {
    public enum AuthenticationResult
    {
        SUCCESS,
        FAILURE
    }

    public AuthenticationResult login(String emailAddress, String password);

    public AuthenticationResult register(String username, String firstname, String lastname, String emailAddress, String password, String dob);

}
