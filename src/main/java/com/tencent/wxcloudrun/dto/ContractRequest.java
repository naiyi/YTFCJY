package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class ContractRequest {

  private String contract_no;

  private int contract_type;

  private String contract_date;

  private String seller;

  private String buyer;

  private String house_address;

  private String house_area;

  private String certificate_no;

  private String price;

  private String confirm_date;

  private String seq_code;

}
