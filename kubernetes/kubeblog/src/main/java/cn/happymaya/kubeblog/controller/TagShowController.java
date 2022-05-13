package cn.happymaya.kubeblog.controller;

import cn.happymaya.kubeblog.po.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private cn.happymaya.kubeblog.service.ITagService ITagService;

    @Autowired
    private cn.happymaya.kubeblog.service.IBlogService IBlogService;

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model){

        List<Tag> tags = ITagService.listTagTop(10000);
        if(id == -1){
            id = tags.get(0).getId();
        }
        model.addAttribute("tags",tags);
        model.addAttribute("page", IBlogService.listBlog(id, pageable));
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
