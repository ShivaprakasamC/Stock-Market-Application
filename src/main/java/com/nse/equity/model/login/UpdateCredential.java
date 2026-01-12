package com.nse.equity.model.login;


import lombok.Data;

@Data
public class UpdateCredential {

    private Long id;
    private String loginName;
    private String loginPassword;
}