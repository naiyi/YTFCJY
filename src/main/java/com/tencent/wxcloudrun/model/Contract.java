package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Contract implements Serializable {

  private int id;

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

  private LocalDateTime create_time;

  private String seq_code;
}
