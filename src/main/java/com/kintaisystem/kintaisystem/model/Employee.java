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
public class Employee {

  /** シーケンス名 */
  private static final String SEQUENCE_NAME = "employee_id_seq";

  /** ID */
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
  @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  /** 名前 */
  @NotBlank
  private String name;
}