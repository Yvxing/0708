package com.ujiuye.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.dao.TbBrandMapper;
import com.ujiuye.pojo.TbBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImple implements TestService{
                                            //这里可以直接使用包中导入的类，spring创建过程自动完成
    @Autowired
    private TbBrandMapper tbBrandMapper;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 数据库整合
     * 内容的获取
     * @return
     */
    @Override
    public List<TbBrand> getAll() {
        return tbBrandMapper.selectByExample(null);
    }

    /**
     * 整合redis
     * 从redis中取数据
     * @param key
     * @return
     */
    @Override
    public List<TbBrand> getFromRedis(String key) {
        List<TbBrand> list = (List<TbBrand>) redisTemplate.boundHashOps("springBootTest").get(key);
        return list;
    }

    /**
     * 分页测试
     *                          //这里的返回数据源   不论是使用list  还是转换后的转换后的list1  内容都是一样的
     * @return
     */
    @Override
    public List<TbBrand> pageHelper() {
        PageHelper.startPage(2,5);
        List<TbBrand> list = tbBrandMapper.selectByExample(null);
        PageInfo<TbBrand> tbBrandPageInfo = new PageInfo<>(list);
        List<TbBrand> list1 = tbBrandPageInfo.getList();
        return list1;
    }

    /**
     * 分页返回值测试
     * @return
     */
    @Override
    public PageInfo getPageInfo() {
        PageHelper.startPage(1,5);
        List<TbBrand> list = tbBrandMapper.selectByExample(null);
        PageInfo<TbBrand> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
