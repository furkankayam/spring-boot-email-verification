package com.furkankayam.util.mapper;

import com.furkankayam.dto.AccountRequestDto;
import com.furkankayam.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AccountMapper {

    public abstract Account toAccount(AccountRequestDto dto);
}
