package com.kintaisystem.kintaisystem.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kintaisystem.kintaisystem.model.Employee;
import com.kintaisystem.kintaisystem.model.EmployeeRepository;
import com.kintaisystem.kintaisystem.model.WorkingHours;
import com.kintaisystem.kintaisystem.model.WorkingHoursRepository;

/**
 * 顧客画面のコントローラクラス。
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {

  /** 登録/更新/削除完了後のリダイレクト先URL */
  private static final String REDIRECT_URL = "redirect:/employees";

  /** HTMLパス */
  private static final String PATH_LIST = "employee/list";
  private static final String PATH_CREATE = "employee/create";
  private static final String PATH_UPDATE = "employee/update";

  /** Modelの属性名 */
  private static final String MODEL_ATTRIBUTE_NAME = "employee";

  /** 顧客リポジトリ */
  @Autowired
  private EmployeeRepository employeeRepository;
  
  @Autowired
  private WorkingHoursRepository workingHoursRepository;

  /**
   * 一覧画面を表示。
   *
   * @param model モデル
   * @return 遷移先
   */
  @GetMapping(value = "")
  public String list(Model model) {
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      //Principalからログインユーザの情報を取得
      String userName = auth.getName();
      model.addAttribute("userName", userName);
    model.addAttribute(MODEL_ATTRIBUTE_NAME, employeeRepository.findAll(Sort.by("id")));
    model.addAttribute("workinghours", workingHoursRepository.findAll(Sort.by("id")));
    return PATH_LIST;
  }

  /**
   * 登録画面を表示。
   *
   * @param model モデル
   * @return 遷移先
   */
  @GetMapping(value = "/create")
  public String create(Model model) {
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      //Principalからログインユーザの情報を取得
      String userName = auth.getName();
      model.addAttribute("userName", userName);
	 Employee employee =new Employee();
    model.addAttribute(MODEL_ATTRIBUTE_NAME, employee);
    WorkingHours workinghours = new WorkingHours();
    model.addAttribute("workinghours", workinghours);
    return PATH_CREATE;
  }

  /**
   * 登録を実行。
   *
   * @param employee 顧客画面入力値
   * @param result 入力チェック結果
   * @return 遷移先
   */
  @PostMapping(value = "/create")
  public String create(@Validated @ModelAttribute(MODEL_ATTRIBUTE_NAME) Employee employee,
      BindingResult result,@ModelAttribute("workingHours") WorkingHours workinghours) {

    if (result.hasErrors()) {
      return PATH_CREATE;
    }

    employeeRepository.save(employee);
    LocalDateTime date=LocalDateTime.now();
    DateTimeFormatter dtformat=DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    workinghours.setSakuseitime(date.toString());
    workinghours.setUpdatetime(date.toString());
    workinghours.setName(employee.getName());
    workinghours.setId(employee.getId());
    workingHoursRepository.save(workinghours);
    return REDIRECT_URL;
  }

  /**
   * 更新画面を表示。
   *
   * @param id 顧客ID
   * @param model モデル
   * @return 遷移先
   */
  @SuppressWarnings("deprecation")
@GetMapping(value = "/{id}/update")
  public String update(@PathVariable("id") Long id, Model model) {
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      //Principalからログインユーザの情報を取得
      String userName = auth.getName();
      model.addAttribute("userName", userName);
    model.addAttribute(MODEL_ATTRIBUTE_NAME, employeeRepository.getOne(id));
    model.addAttribute("workinghours", workingHoursRepository.getOne(id));
    return PATH_UPDATE;
  }

  /**
   * 更新を実行。
   *
   * @param employee 顧客画面入力値
   * @param result 入力チェック結果
   * @return 遷移先
   */
  @PostMapping(value = "/{id}/update")
  public String update(@PathVariable("id") Long id,@Validated @ModelAttribute(MODEL_ATTRIBUTE_NAME) Employee employee,
      BindingResult result,@ModelAttribute("workingHours") WorkingHours workinghours) {
	  

    if (result.hasErrors()) {
      return PATH_UPDATE;
    }

    employeeRepository.save(employee);
    LocalDateTime date=LocalDateTime.now();
    DateTimeFormatter dtformat=DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    workinghours.setSakuseitime(workingHoursRepository.getOne(id).getSakuseitime());
    workinghours.setUpdatetime(date.toString());
    workingHoursRepository.save(workinghours);
    workingHoursRepository.save(workinghours);
    return REDIRECT_URL;
  }

  /**
   * 削除を実行。
   *
   * @param id 顧客ID
   * @return 遷移先
   */
  @GetMapping(value = "/{id}/delete")
  public String list(@PathVariable("id") Long id) {
    employeeRepository.deleteById(id);
    workingHoursRepository.deleteById(id);
    return REDIRECT_URL;
  }
}
