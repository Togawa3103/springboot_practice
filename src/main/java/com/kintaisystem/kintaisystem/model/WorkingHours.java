package com.kintaisystem.kintaisystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 顧客クラス。
 */
@Entity
@Data
public class WorkingHours {

  /** ID */
  @Id
  private Long id;
 
  /** 名前 */
  @NotBlank
  private String name;
  
  @NotBlank
  private String day;
  
  @NotBlank
  private String starttime;

  @NotBlank
  private String endtime;
  
  @NotBlank
  private String sakuseitime;

  @NotBlank
  private String updatetime;

}