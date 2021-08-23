package com.example.test.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    model.addAttribute("describe", "ここでは新規投稿ができます");
    return "posts/index";
  }

  @GetMapping("/post")
  public String post(String inputText, Model model) {
    model.addAttribute("describe", "ここでは新規投稿ができます");
    model.addAttribute("inputText", inputText);
    return "posts/index";
  }
}