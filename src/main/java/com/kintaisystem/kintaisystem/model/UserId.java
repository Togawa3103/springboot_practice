package com.kintaisystem.kintaisystem.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserId implements Serializable{
	public Long id;
	public String username;
	}
