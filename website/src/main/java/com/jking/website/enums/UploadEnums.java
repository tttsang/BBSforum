package com.jking.website.enums;

public interface UploadEnums {
    BaseEnums ONLY_PICTURE = new BaseEnums(1, "只能上传图片");

    BaseEnums ONLY_DOC = new BaseEnums(2, "只能上传Word文档");

    BaseEnums NOT_EXTENSION = new BaseEnums(3, "该文件没有后缀名");

    BaseEnums FILE_NOT_FOUND = new BaseEnums(4, "文件不存在");

    BaseEnums FILE_IS_NULL = new BaseEnums(5, "上传的文件不能为空");
}
