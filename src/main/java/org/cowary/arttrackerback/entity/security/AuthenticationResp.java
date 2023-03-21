package org.cowary.arttrackerback.entity.security;

import lombok.Data;

@Data
public class AuthenticationResp {

    private String token;

    public AuthenticationResp(String token) {
        this.token = token;
    }

}
