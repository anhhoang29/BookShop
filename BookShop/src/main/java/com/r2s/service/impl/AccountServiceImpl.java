package com.r2s.service.impl;

import com.r2s.dto.AccountOutDto;
import com.r2s.entity.Account;
import com.r2s.model.AccountModel;
import com.r2s.model.ActionResult;
import com.r2s.repository.AccountReponsitory;
import com.r2s.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountReponsitory accountReponsitory;

    @Override
    public ActionResult getAccounts(Integer page, Integer size) {
        ActionResult result = new ActionResult();
        Page<Account> pageResult = accountReponsitory.findAll(PageRequest.of(page - 1, size));
        List<AccountModel> accountModels = pageResult.get().map(AccountModel::transform).collect(Collectors.toList());

        AccountOutDto outDto = new AccountOutDto();
        outDto.setAccounts(accountModels);
        outDto.setTotal(pageResult.getNumberOfElements());

        result.setData(outDto);
        return result;

    }
}
