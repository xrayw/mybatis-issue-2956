package org.example.mapper;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.entity.User;
import org.example.enums.GcType;

@Mapper
public interface UserMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user (uu, `type`) values (#{user.uu}, #{user.type})")
    int insert(@Param("user") User user);

    @Select("select * from user where `type`=#{req.gcType}")
    User selectByRequestParam(@Param("req") RequestParam req);


    @Getter
    @Setter
    public static class RequestParam {
        private GcType gcType;
    }
}
