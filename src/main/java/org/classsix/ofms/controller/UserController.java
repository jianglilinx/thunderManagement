package org.classsix.ofms.controller;

import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.Admin;
import org.classsix.ofms.domin.PowerPlant;
import org.classsix.ofms.domin.User;
import org.classsix.ofms.domin.vo.UserVO;
import org.classsix.ofms.repository.AdminRepository;
import org.classsix.ofms.repository.PowerPlantRepository;
import org.classsix.ofms.repository.UserRepository;
import org.classsix.ofms.service.UserService;
import org.classsix.ofms.status.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PUT;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.classsix.ofms.common.Constant.CURRENT_USER;

/**
 * Created by jiang on 2017/5/3.
 * 面向运气，面向心情，面向Bug。
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    PowerPlantRepository powerPlantRepository;

    @ApiOperation(value = "登录", notes = "所有账号登陆登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名/工号", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "用户类型", dataType = "Int", allowableValues = "0为超级管理员，1为管理员或发布人员"),
            @ApiImplicitParam(name = "map", value = "{\"userName\" : \"2014060106\", \"password\" : \"123456\", \"type\" : 0}", required = true, dataType = "Json")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/session" , method = RequestMethod.POST)
    public ResponseMessage userLogin(@RequestBody Map map,HttpServletRequest request){
        UserStatus status = UserStatus.ERROR;
        try {
            int type = (Integer) map.get("type");
            User u = null;
            Admin admin = null;
            if (type == 0) {
                admin = adminRepository.findByUserNameAndPassword((String) map.get("userName"),(String) map.get("password"));
            } else {
                u = userService.confirmLogin((String) map.get("userName"),(String) map.get("password"));
            }
            if (u == null && admin == null)
                return new ResponseMessage(status);
            request.getSession().setAttribute(CURRENT_USER,u != null ? u : admin);
            status = UserStatus.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

    @ApiOperation(value = "用户退出", notes = "用户退出")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/session",method = RequestMethod.DELETE)
    public ResponseMessage userQuit(HttpServletRequest request){
        UserStatus status = UserStatus.ERROR;
        try {
            request.getSession().setAttribute(CURRENT_USER,null);
            status = UserStatus.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }



    @ApiOperation(value = "添加人员", notes = "添加人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "user对象", dataType = "Object"),
            @ApiImplicitParam(name = "map", value = "{\"userName\":\"123456\",\"password\":\"123\", \"realName\" : \"姜日天\",\"powerPlantId\" : 1,\"role\" : 1}", required = true, dataType = "Json")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ResponseMessage userRegist(@RequestBody User user){
        UserStatus status = userService.addUser(user);
        return new ResponseMessage(status);
    }

    @ApiOperation(value = "删除人员", notes = "删除人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要删除管理员的id", dataType = "Int"),
            @ApiImplicitParam(name = "map", value = "{\"id\" : 1}", required = true, dataType = "Json")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    public ResponseMessage userDelete(@RequestBody Map map){
        UserStatus status = UserStatus.ERROR;
        try {
            userRepository.delete((Integer) map.get("id"));
            status = UserStatus.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

    @ApiOperation(value = "根据角色查看所有管理员/发布人员/所有人", notes = "根据角色查看所有管理员/发布人员/所有人")
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "要查找的角色，-1为所有角色即所有人，0为管理员、1为发布人员", dataType = "Int"),
            @ApiImplicitParam(name = "map", value = "{\"id\" : 1}", required = true, dataType = "Json")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": [{\"isDelete\":0,\"realName\":\"姜日天\",\"id\":4,\"userName\":\"123456467\",\"password\":\"123\"，\"powerPlantId\" : 1,\"role\" : 1},{\"isDelete\":0,\"realName\":\"姜日天\",\"id\":5,\"userName\":\"123256467\",\"password\":\"123\"，\"powerPlantId\" : 1,\"role\" : 1}]}")})
    public ResponseMessage adminALL(int role){
        UserStatus status = UserStatus.ERROR;
        try {
            List<User> adminList = new ArrayList<>();
            List<UserVO> userVOList = new ArrayList<>();
            if (role == -1) {
                adminList = userRepository.findAll();
            } else {
                adminList = userRepository.findByRole(role);
            }
            for (User u : adminList) {
                userVOList.add(toUserVO(u));
            }
            return new ResponseMessage(UserStatus.SUCCESS, userVOList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

    @ApiOperation(value = "修改密码", notes = "用户修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String"),
            @ApiImplicitParam(name = "map", value = "{\"password\" : \"abc\"}", required = true, dataType = "Json")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/password",method = RequestMethod.PUT)
    public ResponseMessage userFile(@RequestBody Map map,HttpServletRequest request){
        ResponseMessage responseMessage = new ResponseMessage(UserStatus.ERROR);
        try {
            User user = (User) request.getSession().getAttribute("user");
            responseMessage = userService.updateUser(user.getId(),(String) map.get("password"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseMessage;
    }

//    @ApiOperation(value = "发送邮件", notes = "发送验证邮件")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "receiveMail", value = "收件地址", dataType = "String"),
//            @ApiImplicitParam(name = "map", value = "{'receiveMail' : 'jiang@123.com'}", required = true, dataType = "Json")
//    })
//    @RequestMapping("/usr/sendMail")
//    public ResponseMessage sendMail(@RequestBody Map map){
//        UserStatus userStatus = UserStatus.ERROR;
//        Mail mail = new Mail(mailSender,stringRedisTemplate);
//        try {
//           mail.sendVerfMail((String)map.get("receiveMail"));
//            userStatus = UserStatus.SUCCESS;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseMessage(userStatus);
//    }

    private UserVO toUserVO(User user) {
        PowerPlant powerPlant = powerPlantRepository.findOne(user.getPowerPlantId());
        return new UserVO(user.getId(),user.getUserName(),user.getPassword(),powerPlant,user.getRole(),user.getRealName(),user.getIsDelete());
    }



}
