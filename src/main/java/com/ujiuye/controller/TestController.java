package com.ujiuye.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.pojo.TbBrand;
import com.ujiuye.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 访问测试
     * @return
     */
    @RequestMapping("/")
    public String test(){
        return "hello  word";
    }

    /**
     * 数据库测试
     * @return
     */
    @RequestMapping("/all")
    public List<TbBrand> getAll(){
        return testService.getAll();
    }

    /**
     * 将数据导入redis  这里导入的是整个集合
     * @return
     */
    @RequestMapping("/inRedis")
    public String putDataInRedis(){
        List<TbBrand> all = testService.getAll();
        redisTemplate.boundHashOps("springBootTest").put("jundao",all);
        return "index.html";        //注意  使用restController会让返回值都变为数值形式，使用一般的Controller注解才会有条抓页面效果
    }

    /**
     * redis获取测试
     * @return
     */
    @RequestMapping("/redisGet")
    public List<TbBrand> getFromRedis(){
        return testService.getFromRedis("jundao");
    }

    /**
     * activeMq消息队列的传送
     * @param msg
     */
    @RequestMapping("/send")
    public void mqSend(String msg){             //发送的内容  使用？  拼接到/send的后面
        jmsTemplate.convertAndSend("queue",msg);
    }

    /**
     * 分页测试
     * @return
     */
    @RequestMapping("/pageInfo")
    public List<TbBrand> pageInfo(){
        List<TbBrand> list = testService.pageHelper();
        return list;
    }


    /**
     * 经测试   该返回值里面的值包含了当前页的全部相关信息
     * {"total":22,"list":[{"id":1,"name":"联想","firstChar":"L"},{"id":2,"name":"华为","firstChar":"H"},
     * {"id":3,"name":"三星","firstChar":"S"},{"id":4,"name":"小米","firstChar":"X"},{"id":5,"name":"OPPO","firstChar":"O"}],
     * "pageNum":1,"pageSize":5,"size":5,"startRow":1,"endRow":5,"pages":5,"prePage":0,"nextPage":2,"isFirstPage":true
     * ,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4,5]
     * ,"navigateFirstPage":1,"navigateLastPage":5,"lastPage":5,"firstPage":1}
     *
     * {"name":"wcwcwc","color":"red","age":22,[{"id":1,"name":woq"},{"id":2,"name","wew"}],"navigateFirstPage":1}
     * @return
     */
    @RequestMapping("/info")
    public PageInfo showPageInfo(){
        return testService.getPageInfo();
    }
    
    @RequestMapping("/git")
    public String testGit(){
        return "update Success";
    }

}
