package com.r2s.model;

import com.r2s.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountModel implements Serializable {
    private Integer id;
    private String userName;
    private Date createDate;
    private Boolean status;
    private UserModel user;

    public static AccountModel transform(Account entity) {
        return AccountModel.builder()
                .id(entity.getAccountId())
                .userName(entity.getUserName())
                .createDate(entity.getCreatedDate())
                .status(entity.getStatus())
                .user(UserModel.transform(entity.getUser()))
                .build();
    }

}