package com.r2s.dto;

import com.r2s.model.AccountModel;
import lombok.Data;

import java.util.List;

@Data
public class AccountOutDto {
    private List<AccountModel> accounts;
    private Integer total;
}
