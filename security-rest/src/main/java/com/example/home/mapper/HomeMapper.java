package com.example.home.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.home.Home;

@Mapper
public interface HomeMapper {
       public Home readHome(@Param("name") String name);
}