package org.classsix.ofms.controller;

import io.swagger.annotations.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.User;
import org.classsix.ofms.domin.WarningGroup;
import org.classsix.ofms.domin.vo.WarningGroupVO;
import org.classsix.ofms.repository.UserRepository;
import org.classsix.ofms.repository.WarningGroupRepository;
import org.classsix.ofms.status.GeneralStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.classsix.ofms.common.Constant.CURRENT_USER;

/**
 * Created by Jiang on 2018/5/28.
 * Coding
 */
@RestController
public class WarningGroupController {
    @Autowired
    private WarningGroupRepository warningGroupRepository;
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "添加报警组", notes = "添加报警组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "warningGroup", value = "warningGroup对象", dataType = "Object"),
            @ApiImplicitParam(name = "map", value = "{\"groupName\":\"西南组\", \"groupAdminId\" : \"1,2,3,4,5\"}", allowableValues = "groupName是报警组组名，groupAdminId是所有该组的id，用,分割",required = true, dataType = "Json")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/group",method = RequestMethod.POST)
    public ResponseMessage warningGroupAdd(@RequestBody WarningGroup warningGroup){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            warningGroupRepository.save(warningGroup);
            warningGroup = warningGroupRepository.findByGroupName(warningGroup.getGroupName());
            for (String idStr : warningGroup.getGroupAdminId().split(",")) {
                User u = userRepository.findOne(Integer.parseInt(idStr));
                if (u.getGroupIds() == null || u.getGroupIds().equals("")) {
                    u.setGroupIds(String.valueOf(warningGroup.getId()));
                } else {
                    u.setGroupIds(u.getGroupIds() + "," + warningGroup.getId());
                }
                userRepository.save(u);
            }
            status = GeneralStatus.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

    @ApiOperation(value = "编辑报警组", notes = "编辑报警组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "warningGroup", value = "warningGroup对象", dataType = "Object"),
            @ApiImplicitParam(name = "map", value = "{\"id\" : 1 ,\"groupName\":\"西南组\", \"groupAdminId\" : \"1,2,3,4,5\"}", allowableValues = "groupName是报警组组名，groupAdminId是所有该组的id，用,分割",required = true, dataType = "Json")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/group",method = RequestMethod.PUT)
    public ResponseMessage warningGroupUpdate(@RequestBody WarningGroup warningGroup){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            warningGroupRepository.save(warningGroup);
            for (String idStr : warningGroup.getGroupAdminId().split(",")) {
                User u = userRepository.findOne(Integer.parseInt(idStr));
                if (u.getGroupIds() == null || u.getGroupIds().equals("")) {
                    u.setGroupIds(String.valueOf(warningGroup.getId()));
                } else {
                    u.setGroupIds(u.getGroupIds() + "," + warningGroup.getId());
                }
            }
            status = GeneralStatus.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

    @ApiOperation(value = "根据id查看报警组", notes = "根据id报警组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "路径变量，加在url上/group/{id}", dataType = "Int"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\":0,\"msg\":\"SUCCESS\",\"body\":{\"id\":1,\"groupName\":\"西南组\",\"groupAdminId\":\"1,2,3,4,5\"}}")})
    @RequestMapping(value = "/group/{id}",method = RequestMethod.GET)
    public ResponseMessage warningGroupInfo(@PathVariable Integer id){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            WarningGroup warningGroup = warningGroupRepository.findOne(id);
            status = GeneralStatus.SUCCESS;
            return new ResponseMessage(status , toWaringGroupVO(warningGroup));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }


    @ApiOperation(value = "查看所有报警组", notes = "查看所有报警组")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\":0,\"msg\":\"SUCCESS\",\"body\":[{\"id\":1,\"groupName\":\"西南组\",\"groupAdminId\":\"1,2,3,4,5\"},{\"id\":2,\"groupName\":\"西北组\",\"groupAdminId\":\"6,7,8\"},{\"id\":3,\"groupName\":\"西南组\"}]}")})
    @RequestMapping(value = "/group",method = RequestMethod.GET)
    public ResponseMessage warningGroupALLInfo(){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            List<WarningGroup> warningGroups = warningGroupRepository.findAll();
            status = GeneralStatus.SUCCESS;
            List<WarningGroupVO> warningGroupVOs = new ArrayList<>();
            for (WarningGroup warningGroup : warningGroups) {
                warningGroupVOs.add(toWaringGroupVO(warningGroup));
            }
            return new ResponseMessage(status , warningGroupVOs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

    @ApiOperation(value = "查看登陆用户所属所有报警组", notes = "查看登陆用户所属所有报警组")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\":0,\"msg\":\"SUCCESS\",\"body\":[{\"id\":1,\"groupName\":\"西南组\",\"groupAdminId\":\"1,2,3,4,5\"},{\"id\":2,\"groupName\":\"西北组\",\"groupAdminId\":\"6,7,8\"},{\"id\":3,\"groupName\":\"西南组\"}]}")})
    @RequestMapping(value = "/user/group",method = RequestMethod.GET)
    public ResponseMessage userWarningGroupALLInfo(HttpServletRequest request){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            List<WarningGroup> warningGroups = new ArrayList<>();
            User u = (User) request.getSession().getAttribute(CURRENT_USER);
            for (String idStr : u.getGroupIds().split(",")) {
                WarningGroup warningGroup = warningGroupRepository.findOne(Integer.parseInt(idStr));
                warningGroups.add(warningGroup);
            }
            status = GeneralStatus.SUCCESS;
            List<WarningGroupVO> warningGroupVOs = new ArrayList<>();
            for (WarningGroup warningGroup : warningGroups) {
                warningGroupVOs.add(toWaringGroupVO(warningGroup));
            }
            return new ResponseMessage(status , warningGroupVOs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }


    @ApiOperation(value = "根据id删除报警组", notes = "根据id删除报警组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "路径变量，加在url上/group/{id}", dataType = "Int"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/group/{id}",method = RequestMethod.DELETE)
    public ResponseMessage warningGroupDelete(@PathVariable Integer id){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            warningGroupRepository.delete(id);
            status = GeneralStatus.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

    private WarningGroupVO toWaringGroupVO(WarningGroup warningGroup) {
        WarningGroupVO warningGroupVO = new WarningGroupVO();
        warningGroupVO.setId(warningGroup.getId());
        warningGroupVO.setGroupName(warningGroup.getGroupName());
        List<User> adminList =  new ArrayList<>();
        for (String idStr : warningGroup.getGroupAdminId().split(",")) {
            User u = userRepository.findOne(Integer.parseInt(idStr));
            adminList.add(u);
        }
        warningGroupVO.setGroupAdminList(adminList);
        return warningGroupVO;
    }
}
