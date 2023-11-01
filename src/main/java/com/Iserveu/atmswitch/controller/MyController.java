package com.Iserveu.atmswitch.controller;

import java.io.IOException;

import org.jpos.iso.ISOException;
import org.jpos.util.NameRegistrar.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Iserveu.atmswitch.myservice.MyService;

@RestController
public class MyController {
	
	@Autowired
	private MyService service;
	
	@GetMapping("/test")
	public String test() throws ISOException, IOException, NotFoundException {
		return this.service.response();
	}
}
