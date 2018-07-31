package com.brunno.SpringBase.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Authority implements GrantedAuthority{
    private String authorityName;

    @Override
    public String getAuthority() {
        return authorityName;
    }
}

