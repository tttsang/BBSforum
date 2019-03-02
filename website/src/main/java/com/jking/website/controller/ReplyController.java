package com.jking.website.controller;

import com.jking.website.VO.ResultVO;
import com.jking.website.constant.UploadConstant;
import com.jking.website.entity.Reply;
import com.jking.website.mapper.ReplyMapper;
import com.jking.website.service.Impl.ReplyService;
import com.jking.website.utils.FileUtil;
import com.jking.website.utils.ResultVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping(value = "/reply")
public class ReplyController {
    @Autowired
    ReplyService replyService;
    @PostMapping(value = "/")
    public ResultVO answer(Reply reply, MultipartFile file)
    {
       if(!FileUtil.isNUll(file))
       {   FileUtil.isImage(file);
           String extension=FileUtil.getExtension(file);
           String fileName= UUID.randomUUID()+"."+extension;
           String filePath= UploadConstant.REPLY+fileName;
           FileUtil.saveFile(file,filePath);
           reply.setPictureUrl(UploadConstant.URL_REPLY+fileName);
       }

       replyService.answer(reply);
       return ResultVoUtil.succees();
    }

    @GetMapping(value = "/new/{page}")
    public ResultVO newReply(@RequestParam Integer pid,@PathVariable Integer page)

    {
        return  ResultVoUtil.succees(replyService.newReply(pid,page));
    }
}
