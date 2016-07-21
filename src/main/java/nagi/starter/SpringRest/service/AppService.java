package nagi.starter.SpringRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nagi.starter.SpringRest.repository.AppRepository;

@Service
public class AppService {

	@Autowired
	AppRepository repository;

}
