package org.classsix.ofms.controller;

import io.swagger.annotations.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.Indicators;
import org.classsix.ofms.domin.User;
import org.classsix.ofms.repository.IndicatorsRepository;
import org.classsix.ofms.status.GeneralStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.classsix.ofms.common.Constant.CURRENT_USER;

/**
 * Created by Jiang on 2018/6/9.
 * Coding
 */
@RestController
public class IndicatorsController {

    @Autowired
    private IndicatorsRepository indicatorsRepository;

    @ApiOperation(value = "添加指标", notes = "添加指标")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Indicators", value = "powerplant", dataType = "Object"),
            @ApiImplicitParam(name = "map", value = "{\"indicatorsName\":\"消耗\", \"indicatorsNum\" : 1,\"indicatorsType\" : 1}", allowableValues = "indicatorsType是指标单位，数字代表待定",required = true, dataType = "Json")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/indicators",method = RequestMethod.POST)
    public ResponseMessage indicatorsAdd(@RequestBody Indicators indicators,HttpServletRequest request){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            User u = (User) request.getSession().getAttribute(CURRENT_USER);
            indicators.setPowerPlantId(u.getPowerPlantId());
            indicatorsRepository.save(indicators);
            status = GeneralStatus.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

    @ApiOperation(value = "根据id删除指标", notes = "根据id删除指标")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "路径变量，加在url上/indicators/{id}", dataType = "Int"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/indicators/{id}",method = RequestMethod.DELETE)
    public ResponseMessage indicatorsDelete(@PathVariable Integer id){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            indicatorsRepository.delete(id);
            status = GeneralStatus.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

    @ApiOperation(value = "编辑指标信息", notes = "编辑指标信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Indicators", value = "Indicators", dataType = "Object"),
            @ApiImplicitParam(name = "map", value = "{\"id\":1,\"indicatorsName\":\"消耗\", \"indicatorsNum\" : 1,\"indicatorsType\" : 1}", allowableValues = "indicatorsType是指标单位，数字代表待定",required = true, dataType = "Json")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/indicators",method = RequestMethod.PUT)
    public ResponseMessage indicatorsUpdate(@RequestBody Indicators indicators,HttpServletRequest request){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            User u = (User) request.getSession().getAttribute(CURRENT_USER);
            indicators.setPowerPlantId(u.getPowerPlantId());
            indicatorsRepository.save(indicators);
            status = GeneralStatus.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

    @ApiOperation(value = "根据id查看指标", notes = "根据id查看指标")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "路径变量，加在url上/indicators/{id}", dataType = "Int"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\":0,\"msg\":\"SUCCESS\",\"body\":{\"indicatorsName\":\"消耗\", \"indicatorsNum\" : 1,\"indicatorsType\" : 1}}")})
    @RequestMapping(value = "/indicators/{id}",method = RequestMethod.GET)
    public ResponseMessage indicatorsInfo(@PathVariable Integer id){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            Indicators indicators = indicatorsRepository.findOne(id);
            status = GeneralStatus.SUCCESS;
            return new ResponseMessage(status , indicators);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }


    @ApiOperation(value = "查看当前用户所在电厂下所有指标", notes = "查看当前用户所在电厂下所有指标")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\":0,\"msg\":\"SUCCESS\",\"body\":{\"indicatorsName\":\"消耗\", \"indicatorsNum\" : 1,\"indicatorsType\" : 1}}")})
    @RequestMapping(value = "/indicators",method = RequestMethod.GET)
    public ResponseMessage indicatorsALLInfo(HttpServletRequest request){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            User u = (User) request.getSession().getAttribute(CURRENT_USER);
            List<Indicators> indicatorsList = indicatorsRepository.findByPowerPlantId(u.getPowerPlantId());
            status = GeneralStatus.SUCCESS;
            return new ResponseMessage(status , indicatorsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }
}
