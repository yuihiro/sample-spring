package nagi.starter.SpringRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nagi.starter.SpringRest.service.ApService;

@Api(value = "ApController", description = "AP관련")
@RestController
@RequestMapping("/api/ap")
public class ApController {

	@Autowired
	public ApService service;

	@ApiOperation(value = "AP목록 SELECT", httpMethod = "GET")
	@RequestMapping("/ap_list")
	public List selectApList() {
		return service.selectApList(-1);
	}
}
