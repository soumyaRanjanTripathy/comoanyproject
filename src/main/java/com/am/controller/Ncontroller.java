package com.am.controller;

import java.util.ArrayList;
//import java.util.list;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.am.entity.Ndata;
import com.am.entity.Station;
import com.am.service.NhistoryService;
import com.am.service.Nservice;
import com.am.service.StationService;

@Controller
public class Ncontroller {
	@Autowired
	private NhistoryService nhisSer;
	@Autowired
	private Nservice s;
	@Autowired
	private StationService stationService;
	@GetMapping("/nic")
	public String house(Model m) {
		List<Ndata> emp = s.getAllNdata();
		m.addAttribute("emp", emp);
		return "home";
	}
	@PostMapping("/checkAvilablity") 
	  public String checkAvilablity(@ModelAttribute Ndata n,Model m) {	
		int station_id=stationService.getStationIdByName(n.getStation_name());
		Ndata emp=s.findSpecificNic(n.getDate(), n.getTodate(), n.getUser_name(), station_id);
		m.addAttribute("emp", emp); 		
	    return "add_history"; 
	  }
	
	  @GetMapping("/getAllnic") 
	  public ResponseEntity<Object> getAllNic(Model m) {			  
			List<String> unameList = s.getNicUser(); 
			m.addAttribute("uname", unameList); 
	     return new ResponseEntity<Object>(unameList, HttpStatus.OK); 
	  }
	  @GetMapping("/allstation") 
	  public ResponseEntity<Object> getAllStation(Model m) {			  
			List<Station> station =stationService.getAllStation(); 
			m.addAttribute("station", station); 
	     return new ResponseEntity<Object>(station, HttpStatus.OK); 
	  }
	 
	@PostMapping("/Con")
	public String nicCon(@ModelAttribute Ndata n,RedirectAttributes redirAttrs) {
		int status=s.checkNicExist(n.getUser_name(), n.getStation().getStation_id());
		if(status==0) {
			s.addnic(n);
			redirAttrs.addFlashAttribute("message", "Nic Successfully Added!");
			
		}else {
			redirAttrs.addFlashAttribute("message", "Nic "+n.getUser_name()+" and "+n.getStation().getStation_name()+" Already Exist");
			
		}
		return "redirect:/nic";
	}

	@GetMapping("/nic/edit/{id}")
	public String editNdata(@PathVariable int id, Model model) {

		model.addAttribute("ndata", s.getNdataById(id));
		return "edit_nic";
	}

	@PostMapping("/nic/update/{id}")
	public String updateNdata(@PathVariable int id, @ModelAttribute("ndata") Ndata ndata, Model model) {
		Ndata existingndata = s.getNdataById(id);
		nhisSer.addHistory(existingndata.getUser_name(), existingndata.getStation().getStation_name(), existingndata.getParameter_name(), existingndata.getParameter_unit(), existingndata.getStatus());	
		
		existingndata.setSl_no(id);
		existingndata.setParameter_name(ndata.getParameter_name());
		existingndata.setParameter_unit(ndata.getParameter_unit());
		existingndata.setStation(ndata.getStation());
		existingndata.setStatus(ndata.getStatus());
		existingndata.setUser_name(ndata.getUser_name());
		s.updateNdata(existingndata);
		return "redirect:/nic";
	}

	@GetMapping("/nic/delete/{id}")
	public String deleteNic(@PathVariable int id) {
		s.deleteNNdataById(id);
		return "redirect:/nic";
	}	
	@GetMapping("/stationByUserName/{uname}")
	public ResponseEntity<Object> getId(@PathVariable String uname) {
		List<Integer> sIdList=s.getStationId(uname);
		int y=sIdList.size();		
		List<String> newList=new ArrayList<String>();
		for(int i=0;i<y;i++) {
			
			int x=sIdList.get(i);
			String st=stationService.getStationNameById(x);
			newList.add(st);
			System.out.println(newList.toString());
		}
		
		return new ResponseEntity<Object>(newList, HttpStatus.OK);
	}
}
