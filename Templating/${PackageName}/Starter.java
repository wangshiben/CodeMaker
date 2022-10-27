package ${PackageName};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"${PackageName}.Mapper"})
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }

}
