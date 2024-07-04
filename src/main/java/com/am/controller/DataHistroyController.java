package com.am.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.am.service.NhistoryService;
@Controller
public class DataHistroyController {
	
	@Autowired
	private NhistoryService h2;
	@GetMapping("/addhistory")
	public String addhistoryForm(Model m) {
		
		return "add_history";
	}
	
}
