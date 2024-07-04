package com.am.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.am.entity.Nhistorydata;
import com.am.service.NhistoryService;

@Controller
public class GData {
	@Autowired
	private NhistoryService nHis;
	  @GetMapping("/gdata") 
	  public ResponseEntity<Object> house(Model m) 
	  { 
		  List<Nhistorydata> emp = nHis.getforGdata();
		  m.addAttribute("emp", emp); 
		  return new  ResponseEntity<Object>(emp, HttpStatus.OK);
	  }
	  
	@GetMapping("/chart")
		public String springMVC(ModelMap modelMap) {
			return "chart";
		}
}
