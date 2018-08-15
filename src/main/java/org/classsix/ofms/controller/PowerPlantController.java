package org.classsix.ofms.controller;

import io.swagger.annotations.*;
import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.PowerPlant;
import org.classsix.ofms.repository.PowerPlantRepository;
import org.classsix.ofms.status.GeneralStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiang on 2018/6/8.
 * Coding
 */
@RestController
public class PowerPlantController {
    @Autowired
    private PowerPlantRepository powerPlantRepository;

    @ApiOperation(value = "添加电厂", notes = "添加电厂")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PowerPlant", value = "powerplant", dataType = "Object"),
            @ApiImplicitParam(name = "map", value = "{\"plantName\":\"西南发电站\", \"plantType\" : 1}", allowableValues = "plantType是发电厂类型，0为火力，1为水力，2为核能，3为风力",required = true, dataType = "Json")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/plant",method = RequestMethod.POST)
    public ResponseMessage plantAdd(@RequestBody PowerPlant powerPlant){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            powerPlantRepository.save(powerPlant);
            PowerPlant now = powerPlantRepository.findByPlantName(powerPlant.getPlantName());
            now.setNumber(100000 + now.getId());
            powerPlantRepository.save(now);
            status = GeneralStatus.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

    @ApiOperation(value = "根据id删除电厂", notes = "根据id删除电厂")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "路径变量，加在url上/plant/{id}", dataType = "Int"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/plant/{id}",method = RequestMethod.DELETE)
    public ResponseMessage plantDelete(@PathVariable Integer id){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            powerPlantRepository.delete(id);
            status = GeneralStatus.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

    @ApiOperation(value = "编辑电厂信息", notes = "编辑电厂信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PowerPlant", value = "powerplant", dataType = "Object"),
            @ApiImplicitParam(name = "map", value = "{\"id\":1,\"plantName\":\"西南发电站\", \"plantType\" : 1}", allowableValues = "plantType是发电厂类型，0为火力，1为水力，2为核能，3为风力",required = true, dataType = "Json")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\": 0,\"msg\": \"SUCCESS\",\"body\": null}")})
    @RequestMapping(value = "/plant",method = RequestMethod.PUT)
    public ResponseMessage plantUpdate(@RequestBody PowerPlant powerPlant){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            powerPlantRepository.save(powerPlant);
            status = GeneralStatus.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

    @ApiOperation(value = "根据id查看电厂", notes = "根据id查看电厂")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "路径变量，加在url上/plant/{id}", dataType = "Int"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\":0,\"msg\":\"SUCCESS\",\"body\":{\"plantName\":\"西南发电站\", \"plantType\" : 1,\"number\" : 100001}}")})
    @RequestMapping(value = "/plant/{id}",method = RequestMethod.GET)
    public ResponseMessage plantInfo(@PathVariable Integer id){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            PowerPlant powerPlant = powerPlantRepository.findOne(id);
            status = GeneralStatus.SUCCESS;
            return new ResponseMessage(status , powerPlant);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }


    @ApiOperation(value = "查看所有电厂", notes = "查看所有电厂")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\"code\":0,\"msg\":\"SUCCESS\",\"body\":{\"plantName\":\"西南发电站\", \"plantType\" : 1,,\"number\" : 100001}}")})
    @RequestMapping(value = "/plant",method = RequestMethod.GET)
    public ResponseMessage plantALLInfo(){
        GeneralStatus status = GeneralStatus.ERROR;
        try {
            List<PowerPlant> powerPlants = powerPlantRepository.findAll();
            status = GeneralStatus.SUCCESS;
            return new ResponseMessage(status , powerPlants);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }

}
