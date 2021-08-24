package com.example.test.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostsController {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @GetMapping("/index")
  public String index(Model model) {
    String sql = "SELECT * FROM message_table ORDER BY id DESC";
    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
    model.addAttribute("dataList", list);
    model.addAttribute("describe", "ここでは投稿一覧が見れます");
    return "posts/index";
  }

  @GetMapping("/new")
  public String post(@ModelAttribute Form form, Model model) {
    model.addAttribute("describe", "ここでは新規投稿ができます");
    return "posts/new";
  }

  @PostMapping("/create")
  public String create(Form form, Model model) {
    String sql = "INSERT INTO message_table(message) VALUE (?)";
    jdbcTemplate.update(sql, form.getMessage());
    model.addAttribute("describe", "入力の保存が完了しました");
    return "posts/create";
  }

  @GetMapping("/edit/{id}")
  public String edit(@ModelAttribute Form form, @PathVariable int id, Model model) {
    String sql = "SELECT * FROM message_table WHERE id = " + id;
    Map<String, Object> map = jdbcTemplate.queryForMap(sql);
    form.setId((int)map.get("id"));
    form.setMessage((String)map.get("message"));
    model.addAttribute("describe", "ここでは投稿の編集ができます");
    return "/posts/edit";
  }

  @PostMapping("/edit/{id}")
  public String update(Form form, @PathVariable int id, Model model) {
    String sql = "UPDATE message_table SET message = ? WHERE id = " + id;
    jdbcTemplate.update(sql, form.getMessage());
    model.addAttribute("describe", "投稿の更新が完了しました");
    return "/posts/update";
  }
}