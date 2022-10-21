package com.Servlet;

import com.Resp.BaseRespones;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public BaseRespones<Boolean> Login(@RequestBody String username,@RequestBody String password){
        if (username.equals("root")&&password.equals("123456")){
            return BaseRespones.success("登录成功");
        }else {
            return BaseRespones.failed("登录失败");
        }
    }
    @PostMapping("/regist")
    public BaseRespones<Boolean> regesist(HttpSession session,@RequestBody String username,@RequestBody String password,@RequestBody String mailCode){
        String code = (String) session.getAttribute("code");
        if (code!=null){
            if (code.equals(mailCode)){
                return BaseRespones.success("注册成功",true);
            }
        }
        return BaseRespones.failed("注册失败");
    }



}
