package com.heysong;

import com.heysong.domain.TActivity;
import com.heysong.domain.TClue;
import com.heysong.domain.TDicValue;
import com.heysong.domain.TProduct;
import com.heysong.domain.TUser;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
@MapperScan(value = {"com.heysong.mapper"})
public class HeysongApplication implements CommandLineRunner {
    // 固定的
    public static final HashMap<String, List<TDicValue>> PropertyMapping = new HashMap<>();
    public static final List<TProduct> ProductMapping = new ArrayList<>();
    public static final List<TUser> UserMapping = new ArrayList<>();
    public static final List<TActivity> ActivityMapping = new ArrayList<>();

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(HeysongApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String str = "";
        for (int i = 0; i < args.length; i++) {
            str = str + args[i];
        }
        System.out.println("程序启动！！" + str);
    }
}
