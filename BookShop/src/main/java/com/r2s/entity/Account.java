package com.r2s.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.springframework.security.authentication.AccountStatusUserDetailsChecker;

import com.r2s.entity.User;

import com.r2s.entity.Role;
import lombok.Data;

@Entity
@NoArgsConstructor
@Data
@Setter
@Getter
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "createdDate")
    private Date createdDate;
//
//    @Column(name = "user_id")
//    private Integer userId;
//
//    @Column(name = "role_id")
//    private Integer roleId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "account_role", joinColumns = {
            @JoinColumn(name = "account_id", referencedColumnName = "account_id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "role_id") })
    private Set<Role> roles;
}