package com.tencent.wxcloudrun.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tencent.wxcloudrun.model.Contract;
import com.tencent.wxcloudrun.service.ContractService;

/**
 * index控制器
 */
@Controller

public class TemplateController {


    final ContractService contractService;
    final Logger logger;

    public TemplateController(@Autowired ContractService contractService) {
        this.contractService = contractService;
        this.logger = LoggerFactory.getLogger(ContractController.class);
    }

    // 打开二维码解析的页面
  @GetMapping(value = "/CMS/CLF/WXHTDetail/{code}")
  public String showQRCodePage(@PathVariable("code") String code, Model model) {
    logger.info("/CMS/CLF/WXHTDetail/ get request: {}",code);

    String[] codes = code.split("-");
    if(codes.length == 2){
      Optional<Contract> contract = contractService.getContractByCode(codes[1]);

      if(contract.isPresent()){
        logger.info("/CMS/CLF/WXHTDetail/ get contract: {}",contract.get().getContract_no());

        model.addAttribute("contract", contract.get());
        model.addAttribute("exist", true);

        // 还需要返回服务器当前时间，格式2024/7/2 15:33:15
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d HH:mm:ss");
        String formattedDateTime = zonedDateTime.format(formatter);
        model.addAttribute("nowTime",formattedDateTime);

      }else{  // 无法找到对应记录的地址
          model.addAttribute("exist", false);
      }
    }else{
      model.addAttribute("exist", false);
    }
    return "index";
  }

}