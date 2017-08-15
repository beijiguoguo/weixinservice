package com.guoguo.mvc.dao;

import com.guoguo.mvc.model.Tet;

public interface TetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tet record);

    int insertSelective(Tet record);

    Tet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tet record);

    int updateByPrimaryKey(Tet record);
}