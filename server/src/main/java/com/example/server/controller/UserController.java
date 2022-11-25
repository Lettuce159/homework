package com.example.server.controller;
import com.example.server.domain.User;
import com.example.server.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/dologin")
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("username:{}",username);
        log.info( "password:{}",password );
        User user = userMapper.selectUser(username, password);
        if(user != null){
            return "login successfully";
        }else{
            return "login failed";
        }
    }

    @RequestMapping("/doregister")
    public String register(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkPass = request.getParameter("checkPass");
        /*log.info("username:{}",username);
        log.info( "password:{}",password );
        log.info( "checkPass:{}", checkPass );*/
        User user = userMapper.selectUsername(username);
        if(user == null && password.equals(checkPass)){
            userMapper.saveUser(username,password);
            return "register successfully";
        }else{
            return "register failed";
        }
    }

    @RequestMapping("/dochange")
    public String change(HttpServletRequest request){
        String username = request.getParameter("username");
        String newPassword= request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
       log.info("username:{}",username);
        log.info( "newPassword:{}",newPassword);
        log.info( "confirmPassword:{}", confirmPassword );
        User user = userMapper.selectUsername(username);
        if(/*user!=null&&*/newPassword.equals(confirmPassword)){
            userMapper.updatePassword(username,newPassword);
            return "register successfully";
        }else{
            return "register failed";
        }
    }


}
