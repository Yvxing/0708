package com.ujiuye.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.pojo.TbBrand;

import java.util.List;

public interface TestService {

    List<TbBrand>  getAll();

    List<TbBrand> getFromRedis(String key);

    List<TbBrand> pageHelper();

    PageInfo getPageInfo();

}
