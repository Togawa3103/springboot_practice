package com.kintaisystem.kintaisystem.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * index画面のコントローラクラス。
 */
@Controller
@RequestMapping("/")
public class IndexController {

  /**
   * index画面
   *
   * @param model モデル
   * @return 遷移先
   */
  @RequestMapping("index")
  public String index(Model model) {
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      //Principalからログインユーザの情報を取得
      String userName = auth.getName();
      model.addAttribute("userName", userName);
    return "index";
  }
}
