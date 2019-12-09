package com.ujiuye;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;


                            //该类的放置位置必须在所有类之前，可以有同级的类和它并列，但是要保证不能和controller等包内类放的等级一样
                            //否则会出现访问不到的情况
@org.springframework.boot.autoconfigure.SpringBootApplication                   //需要申明s是springBootApplication
@MapperScan("com.ujiuye.dao")                           //加载mapper接口
public class SpringBootApplication {
                                                                                //springBoot的程序运行入口，所有的服务和嵌入组件都通过这样默认加载
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class,args);
    }

}
