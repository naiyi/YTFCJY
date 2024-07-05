package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.ContractsMapper;
import com.tencent.wxcloudrun.model.Contract;
import com.tencent.wxcloudrun.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

  final ContractsMapper contractsMapper;

  public ContractServiceImpl(@Autowired ContractsMapper contractsMapper) {
    this.contractsMapper = contractsMapper;
  }

  @Override
  public Optional<Contract> getContract(Integer id) {
    return Optional.ofNullable(contractsMapper.getContract(id));
  }

  @Override
  public Optional<Contract> getContractByCode(String code) {
    return Optional.ofNullable(contractsMapper.getContractByCode(code));
  }

  @Override
  public int addOrUpdateContract(Contract contract) {
    return contractsMapper.insertOrUpdateContract(contract);
  }

  @Override
  public void removeContract(Integer id) {
    contractsMapper.removeContract(id);
  }

  @Override
  public List<Contract> getAllList(){
    return contractsMapper.getAllList();
  }
}
