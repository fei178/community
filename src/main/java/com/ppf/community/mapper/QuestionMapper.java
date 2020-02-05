package com.ppf.community.mapper;

import com.ppf.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("INSERT INTO question(title,description,tag,creator,gmt_create,gmt_modified) VALUES(#{title},#{description},#{tag},#{creator},#{gmtCreate},#{gmtModified})")
    void create(Question question);
}
