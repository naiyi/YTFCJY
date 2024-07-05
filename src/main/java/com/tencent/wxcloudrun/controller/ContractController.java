package com.tencent.wxcloudrun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import com.tencent.wxcloudrun.QRCodeUtil;
import com.tencent.wxcloudrun.RandomStringUtil;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.ContractRequest;
import com.tencent.wxcloudrun.model.Contract;
import com.tencent.wxcloudrun.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;

/**
 * counter控制器
 */
@RestController

public class ContractController {

  final ContractService contractService;
  final Logger logger;

  public ContractController(@Autowired ContractService contractService) {
    this.contractService = contractService;
    this.logger = LoggerFactory.getLogger(ContractController.class);
  }

  /**
   * 获取所有记录
   * @return API response json
   */
  @PostMapping(value = "/api/getAll")
  ApiResponse getAllList() {
    logger.info("/api/getAll post request");

    List<Contract> list = contractService.getAllList();
    return ApiResponse.ok(list);
  }


  /**
   * 新增记录
   * @param request {@link ContractRequest}
   * @return API response json
   */
  @PostMapping(value = "/api/addOrUpdate")
  ApiResponse addOrUpdate(HttpServletRequest httpRequest, @RequestBody ContractRequest request) {
    logger.info("/api/addOrUpdate post request, getContract_no: {}", request.getContract_no());

    Contract contract = new Contract();
    contract.setContract_no(request.getContract_no());
    contract.setContract_type(request.getContract_type());
    contract.setContract_date(request.getContract_date());
    contract.setSeller(request.getSeller());
    contract.setBuyer(request.getBuyer());
    contract.setHouse_address(request.getHouse_address());
    contract.setHouse_area(request.getHouse_area());
    contract.setPrice(request.getPrice());
    contract.setCertificate_no(request.getCertificate_no());
    contract.setConfirm_date(request.getConfirm_date());
    if (request.getSeq_code()!=null) {
      contract.setSeq_code(request.getSeq_code());
    }else{
      contract.setSeq_code(RandomStringUtil.generateRandomString(15));
    }
    int num = contractService.addOrUpdateContract(contract);
    int insertedId = contract.getId();
    HashMap<String,Integer> result = new HashMap<>();
    result.put("num", num);
    result.put("insertedId", insertedId);

    // 生成二维码并保存
    // logger.info("url:{}",httpRequest.getRequestURI());
    // logger.info("url:{}",httpRequest.getRequestURL().toString());

    // String imgName = contract.getContract_date().replaceAll("-", "")+"_"+contract.getSeq_code();
    // String qrCodeURL = "http://"+httpRequest.getServerName()+":"+httpRequest.getServerPort()+"/CMS/"+imgName;
    // QRCodeUtil.createQRCodeToFile(qrCodeURL,null, imgName);

    return ApiResponse.ok(result);
  }

  // @PostMapping(value = "/api/update")
  // ApiResponse update(@RequestBody CounterRequest request) {
  //   logger.info("/api/count post request, getContract_no: {}", request.getContract_no());

  //   Optional<Counter> curCounter = counterService.getCounter(1);
  //   if (request.getContract_no().equals("inc")) {
  //     Integer count = 1;
  //     if (curCounter.isPresent()) {
  //       count += curCounter.get().getCount();
  //     }
  //     Counter counter = new Counter();
  //     counter.setId(1);
  //     counter.setCount(count);
  //     counterService.upsertCount(counter);
  //     return ApiResponse.ok(count);
  //   } else if (request.getContract_no().equals("clear")) {
  //     if (!curCounter.isPresent()) {
  //       return ApiResponse.ok(0);
  //     }
  //     counterService.clearCount(1);
  //     return ApiResponse.ok(0);
  //   } else {
  //     return ApiResponse.error("参数action错误");
  //   }
  // }
  
}