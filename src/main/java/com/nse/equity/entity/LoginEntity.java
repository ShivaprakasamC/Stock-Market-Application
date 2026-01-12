package com.nse.equity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Builder
@Table(name = "credential_details", schema = "nse_db")
@NoArgsConstructor
@AllArgsConstructor
public class LoginEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_name",length = 100)
    private String loginName;

    @Column(name = "login_password", length = 100)
    private String loginPassword;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_timestamp")
    private Timestamp createdTimestamp;

    @Column(name = "updated_timestamp")
    private Timestamp updatedTimestamp;
}