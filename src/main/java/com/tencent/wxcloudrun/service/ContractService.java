package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.Contract;

import java.util.Optional;
import java.util.List;

public interface ContractService {

  Optional<Contract> getContract(Integer id);

  Optional<Contract> getContractByCode(String code);

  int addOrUpdateContract(Contract contract);

  List<Contract> getAllList();

  void removeContract(Integer id);
}
