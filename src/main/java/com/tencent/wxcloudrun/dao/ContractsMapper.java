package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Contract;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ContractsMapper {

  Contract getContract(@Param("id") Integer id);

  Contract getContractByCode(@Param("seq_code") String seq_code);

  List<Contract> getAllList();

  int insertOrUpdateContract(Contract contract);

  void removeContract(@Param("id") Integer id);
}
