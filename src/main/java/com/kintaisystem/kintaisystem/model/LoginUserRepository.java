package com.kintaisystem.kintaisystem.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ログインユーザリポジトリクラス。
 */
public interface LoginUserRepository extends JpaRepository<LoginUser, UserId> {

	LoginUser findByUsername(String userName);

}
