package com.r2s.service;

import com.r2s.model.ActionResult;

public interface AccountService {
    ActionResult getAccounts(Integer page, Integer size);
}
