package com.ppf.community.controller;

import com.ppf.community.mapper.QuestionMapper;
import com.ppf.community.model.Question;
import com.ppf.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "tag") String tag,
            HttpServletRequest request,
            Model model
    ) {
        if (title == null && title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null && description == "") {
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }
        if (tag == null && tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtModified());
        questionMapper.create(question);
        return "redirect:/";
    }
}
