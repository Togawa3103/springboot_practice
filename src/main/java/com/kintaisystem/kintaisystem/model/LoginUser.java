package com.kintaisystem.kintaisystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import com.kintaisystem.kintaisystem.model.UserId;
/**
 * ログインユーザクラス。
 */
@Entity
@Data
@IdClass(value=UserId.class)
public class LoginUser {

  /** シーケンス名 */
  private static final String SEQUENCE_NAME = "user_id_seq";

  /** ID */
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
  @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  /** 名前 */
  @Id
  @NotBlank
  private String username;
  
  @NotBlank
  private String password;

}