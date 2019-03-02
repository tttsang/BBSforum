package com.jking.website.controller;

import com.jking.website.VO.ResultVO;
import com.jking.website.constant.UploadConstant;
import com.jking.website.entity.Post;
import com.jking.website.entity.Reply;
import com.jking.website.service.Impl.PostService;
import com.jking.website.service.Impl.ReplyService;
import com.jking.website.utils.FileUtil;
import com.jking.website.utils.ResultVoUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.ServerEndpoint;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private ReplyService replyService;

    @GetMapping(value = "/index/{page}")
    public ResultVO index(@PathVariable Integer page)
    {
        return ResultVoUtil.succees(postService.index(page));
    }

    @GetMapping(value = "/content/{page}")
    public ResultVO content(@RequestParam Integer id,@PathVariable Integer page)
    {   System.out.println("id"+id+"page"+page);
        Map<String,Object> content=new LinkedHashMap<>();
        Post post=postService.content(id);
        //System.out.println(post);
        Map<String,Object> replies=replyService.reply(id,page);
        content.put("post",post);
        content.put("reply",replies);
        return ResultVoUtil.succees(content);
    }

    @PostMapping(value = "/search/{page}")
    public ResultVO search(@RequestParam String title, @PathVariable Integer page)
    {   String titles=title.trim();
        System.out.println(titles+"aaa");

        return  ResultVoUtil.succees(postService.Search(titles,page));
    }

    @PostMapping(value = "/")
    public  ResultVO upload(MultipartFile file,Post post,@RequestParam String phonenum)
    {   System.out.println(post);
        if(!FileUtil.isNUll(file))
        {   FileUtil.isImage(file);
            String extension=FileUtil.getExtension(file);
            String fileName= UUID.randomUUID()+"."+extension;
            String filePath=UploadConstant.POST+fileName;
            FileUtil.saveFile(file,filePath);
            post.setPictureUrl(UploadConstant.URL_POST+fileName);
        }

        postService.post(post,phonenum);
        return ResultVoUtil.succees();
    }

}
