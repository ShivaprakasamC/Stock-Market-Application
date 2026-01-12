package com.nse.equity.model.login;

import lombok.Data;

@Data
public class UserCredential {

    private String loginName;
    private String loginPassword;
}