package org.classsix.ofms.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.common.processor.Processor;
import org.classsix.ofms.status.GeneralStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jiang on 2018/7/4.
 * Coding
 */
@RestController
public class ProcessorController {
    @ApiOperation(value = "生成锅炉温度图表", notes = "生成锅炉温度图表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\":0,\"msg\":\"SUCCESS\",\"body\":{\"series\":[9,31,76,89,83,97,34,59,16,34,26,48,11,83,37,36,37,25,66,38,20,93,12,43],\"category\":[\"00:00\",\"01:00\",\"02:00\",\"03:00\",\"04:00\",\"05:00\",\"06:00\",\"07:00\",\"08:00\",\"09:00\",\"10:00\",\"11:00\",\"12:00\",\"13:00\",\"14:00\",\"15:00\",\"16:00\",\"17:00\",\"18:00\",\"19:00\",\"20:00\",\"21:00\",\"22:00\",\"23:00\"]}}")})
    @RequestMapping(value = "/processor/temperature",method = RequestMethod.GET)
    public ResponseMessage temperatureProcessor(){
        Map<String,Object> map = new HashMap<>();
        try {
             map = Processor.processSteam(500,1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(GeneralStatus.SUCCESS , map);
    }

    @ApiOperation(value = "生成主蒸汽流量图表", notes = "生成主蒸汽流量图表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\":0,\"msg\":\"SUCCESS\",\"body\":{\"series\":[9,31,76,89,83,97,34,59,16,34,26,48,11,83,37,36,37,25,66,38,20,93,12,43],\"category\":[\"00:00\",\"01:00\",\"02:00\",\"03:00\",\"04:00\",\"05:00\",\"06:00\",\"07:00\",\"08:00\",\"09:00\",\"10:00\",\"11:00\",\"12:00\",\"13:00\",\"14:00\",\"15:00\",\"16:00\",\"17:00\",\"18:00\",\"19:00\",\"20:00\",\"21:00\",\"22:00\",\"23:00\"]}}")})
    @RequestMapping(value = "/processor/steam",method = RequestMethod.GET)
    public ResponseMessage steamProcessor(){
        Map<String,Object> map = new HashMap<>();
        try {
            map = Processor.processSteam(100,200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(GeneralStatus.SUCCESS , map);
    }

    @ApiOperation(value = "生成总燃料量图表", notes = "生成总燃料量图表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\":0,\"msg\":\"SUCCESS\",\"body\":{\"series\":[9,31,76,89,83,97,34,59,16,34,26,48,11,83,37,36,37,25,66,38,20,93,12,43],\"category\":[\"00:00\",\"01:00\",\"02:00\",\"03:00\",\"04:00\",\"05:00\",\"06:00\",\"07:00\",\"08:00\",\"09:00\",\"10:00\",\"11:00\",\"12:00\",\"13:00\",\"14:00\",\"15:00\",\"16:00\",\"17:00\",\"18:00\",\"19:00\",\"20:00\",\"21:00\",\"22:00\",\"23:00\"]}}")})
    @RequestMapping(value = "/processor/fuel",method = RequestMethod.GET)
    public ResponseMessage fuelProcessor(){
        Map<String,Object> map = new HashMap<>();
        try {
            map = Processor.processSteam(888,1988);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(GeneralStatus.SUCCESS , map);
    }
}
