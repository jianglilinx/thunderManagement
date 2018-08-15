package org.classsix.ofms.controller;

import io.swagger.annotations.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.User;
import org.classsix.ofms.domin.Warning;
import org.classsix.ofms.repository.WarningGroupRepository;
import org.classsix.ofms.repository.WarningRepository;
import org.classsix.ofms.status.GeneralStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.classsix.ofms.common.Constant.CURRENT_USER;

/**
 * Created by Jiang on 2018/6/9.
 * Coding
 */
@RestController
public class WarningController {

    @Autowired
    private WarningRepository warningRepository;

    @ApiOperation(value = "添加报警", notes = "添加报警")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Warning", value = "Warning", dataType = "Object"),
            @ApiImplicitParam(name = "map", value = "{\"warningName\":\"用电报警\", \"maxValue\" : 100,\"minValue\" : 1,\"groupId\" : 1}", allowableValues = "groupId为用户所选报警组的id",required = true, dataType = "Json")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/warning",method = RequestMethod.POST)
    public ResponseMessage warningAdd(@RequestBody Warning warning){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            warningRepository.save(warning);
            status = GeneralStatus.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

    @ApiOperation(value = "根据id删除报警", notes = "根据id删除报警")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "路径变量，加在url上/warning/{id}", dataType = "Int"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/warning/{id}",method = RequestMethod.DELETE)
    public ResponseMessage warningDelete(@PathVariable Integer id){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            warningRepository.delete(id);
            status = GeneralStatus.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

    @ApiOperation(value = "编辑报警信息", notes = "编辑报警信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Warning", value = "Warning", dataType = "Object"),
            @ApiImplicitParam(name = "map", value = "{\"id\" : 1,\"warningName\":\"用电报警\", \"maxValue\" : 100,\"minValue\" : 1,\"groupId\" : 1}", allowableValues = "groupId为用户所选报警组的id",required = true, dataType = "Json")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/warning",method = RequestMethod.PUT)
    public ResponseMessage warningUpdate(@RequestBody Warning warning){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            warningRepository.save(warning);
            status = GeneralStatus.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

    @ApiOperation(value = "根据id查看报警信息", notes = "根据id查看报警信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "路径变量，加在url上/indicators/{id}", dataType = "Int"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\":0,\"msg\":\"SUCCESS\",\"body\":{\"warningName\":\"用电报警\", \"maxValue\" : 100,\"minValue\" : 1,\"groupId\" : 1}}}")})
    @RequestMapping(value = "/warning/{id}",method = RequestMethod.GET)
    public ResponseMessage warningInfo(@PathVariable Integer id){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            Warning warning = warningRepository.findOne(id);
            status = GeneralStatus.SUCCESS;
            return new ResponseMessage(status , warning);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }


    @ApiOperation(value = "查看当前用户所有报警组的报警信息", notes = "查看当前用户所有报警组的报警信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\":0,\"msg\":\"SUCCESS\",\"body\":{\"warningName\":\"用电报警\", \"maxValue\" : 100,\"minValue\" : 1,\"groupId\" : 1}}")})
    @RequestMapping(value = "/warning",method = RequestMethod.GET)
    public ResponseMessage iwarningALLInfo(HttpServletRequest request){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            User u = (User) request.getSession().getAttribute(CURRENT_USER);
            List<Warning> warningList = new ArrayList<>();
            for (String groupIdStr : u.getGroupIds().split(",")) {
                List<Warning> warnings = warningRepository.findByGroupId(Integer.parseInt(groupIdStr));
                warningList.addAll(warnings);
            }
            status = GeneralStatus.SUCCESS;
            return new ResponseMessage(status , warningList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }
}
