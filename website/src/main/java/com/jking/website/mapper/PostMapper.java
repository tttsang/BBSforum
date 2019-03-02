package com.jking.website.mapper;

import com.jking.website.entity.Post;
//import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Param;
import java.util.ArrayList;
import java.util.List;

public interface PostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

    ArrayList<Post> index(@Param("page")int page);

    int count();

    Post showContent(@Param("uid")int uid);

    ArrayList<Post>findPost(@Param("title") String title,@Param("page")Integer page);

    int countSearch(@Param("title") String title);

    Post selectPoster(@Param("pid") Integer pid);
}