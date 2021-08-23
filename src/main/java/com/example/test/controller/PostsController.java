package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostsController {
  @GetMapping("/index")
  public String index(Model model) {
    model.addAttribute("describe", "ここでは新規投稿ができます");
    return "posts/index";
  }
}
