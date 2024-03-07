package com.kintaisystem.kintaisystem.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 使用者リポジトリクラス。
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
