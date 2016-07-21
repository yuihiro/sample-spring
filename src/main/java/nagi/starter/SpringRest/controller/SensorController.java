package nagi.starter.SpringRest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nagi.starter.SpringRest.service.SensorService;

@Api(value = "SensorController", description = "센서관련")
@RestController
@RequestMapping("/api/sensor")
public class SensorController {

	@Autowired
	public SensorService service;

	@ApiOperation(value = "센서목록 SELECT", httpMethod = "GET")
	@RequestMapping(value = "/sensor_list", method = RequestMethod.GET)
	public List selectSensorList() {
		return service.selectSensorList(-1);
	}

	@ApiOperation(value = "펌웨어목록 SELECT", httpMethod = "GET")
	@RequestMapping(value = "/firmware_list", method = RequestMethod.GET)
	public List list() {
		return service.selectFirmwareList(-1);
	}

	@ApiOperation(value = "센서정보 SELECT", httpMethod = "GET")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Map selectSensor(@PathVariable(value = "id") final long id) {
		return service.selectOne(id);
	}

	@RequestMapping(value = "/insert/{id}", method = RequestMethod.GET)
	public void insertSensor(@PathVariable(value = "id") final int id) throws Exception {
		service.insertSensor(id);
	}
}
