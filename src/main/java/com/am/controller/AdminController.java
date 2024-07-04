package com.am.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.am.entity.Admin;
import com.am.entity.Ndata;
import com.am.entity.Station;
import com.am.entity.User;
import com.am.service.AdminService;
import com.am.service.NhistoryService;
import com.am.service.Nservice;
import com.am.service.StationService;
import com.am.service.UserService;
@Controller
public class AdminController {
	@Autowired
	private AdminService as;
	@Autowired
	private StationService ss;
	@Autowired
	private UserService userService;
	@Autowired
	private Nservice s;
	@Autowired
	private NhistoryService nhisSer;

	@GetMapping("/admin_login")
	public String adminLogin() {
		return "admin_login";
	}
	@GetMapping("/dashboard")
	public String dashboard() {
		return "adminDashboard";
	}
	@GetMapping("/addAdmin")
	public String addAdmin() {
		return "addAdmin";
	}
	@GetMapping("/viewAdmin")
	public String viewAdmin() {
		return "viewAdmin";
	}
	@GetMapping("/addUser")
	public String addUser() {
		return "addUser";
	}
	@GetMapping("/viewUser")
	public String viewUser() {
		return "viewUser";
	}
	@GetMapping("/addStation")
	public String addStation() {
		return "addStation";
	}
	@GetMapping("/viewStation")
	public String viewStation() {
		return "viewStation";
	}
	@GetMapping("/addNic")
	public String addNic() {
		return "adminAddNic";
	}
	@GetMapping("/viewNic")
	public String viewNic() {
		return "viewNic";
	}
	//nic
	@GetMapping("/allNics")
	public ResponseEntity<Object> house(Model m) {
		List<Ndata> ndata = s.getAllNdata();
		m.addAttribute("ndata", ndata);
		return new ResponseEntity<Object>(ndata, HttpStatus.OK); 
	}
	@PostMapping("/deleteNic/{sl_no}")
	public ResponseEntity<Object> deleteNic(@PathVariable int sl_no){
		s.deleteNNdataById(sl_no);	
		return new ResponseEntity<Object>("done", HttpStatus.OK);
	}
	@PostMapping("/getNicById/{sl_no}")
	public ResponseEntity<Object> getNicById(@PathVariable int sl_no,Model m){
		Ndata ndata=s.getNdataById(sl_no);
		m.addAttribute("ndata", ndata);	
		return new ResponseEntity<Object>(ndata, HttpStatus.OK);
	}
	@PostMapping("/updateNic")
	public ResponseEntity<Object> updateNdata(@ModelAttribute Ndata ndata) {
		Ndata existingndata = s.getNdataById(ndata.getSl_no());
		nhisSer.addHistory(existingndata.getUser_name(), existingndata.getStation().getStation_name(), existingndata.getParameter_name(), existingndata.getParameter_unit(), existingndata.getStatus());	

		existingndata.setSl_no(ndata.getSl_no());
		existingndata.setParameter_name(ndata.getParameter_name());
		existingndata.setParameter_unit(ndata.getParameter_unit());
		existingndata.setStation(ndata.getStation());
		existingndata.setStatus(ndata.getStatus());
		existingndata.setUser_name(ndata.getUser_name());
		s.updateNdata(existingndata);
		return new ResponseEntity<Object>("done", HttpStatus.OK);
	}
	@PostMapping("/saveNic")
	public String nicCon(@ModelAttribute Ndata n,RedirectAttributes redirAttrs) {
		int status=s.checkNicExist(n.getUser_name(), n.getStation().getStation_id());
		if(status==0) {
			s.addnic(n);
			redirAttrs.addFlashAttribute("message", "Nic Successfully Added!");

		}else {
			redirAttrs.addFlashAttribute("message", "Nic "+n.getUser_name()+" and "+n.getStation().getStation_name()+" Already Exist");

		}
		return "redirect:/addNic";
	}

	//Admin
	@PostMapping("/saveAdmin")
	public String saveAdmin(@ModelAttribute Admin admin,RedirectAttributes redirAttrs) {

		Admin admin1=as.createAdmin(admin);
		if(admin1!=null){
			redirAttrs.addFlashAttribute("message", "Register Successfully");
		}else {
			redirAttrs.addFlashAttribute("message", "Something wrong on server");
		}

		return "redirect:/addAdmin";
	}
	@GetMapping("/allAdmin") 
	public ResponseEntity<Object> getAllAdmin(Model m) {			  
		List<Admin> admin =as.getAllAdmin(); 
		m.addAttribute("admin", admin); 
		return new ResponseEntity<Object>(admin, HttpStatus.OK); 
	}
	@PostMapping("/deleteAdmin/{admin_id}")
	public ResponseEntity<Object> deleteAdmin(@PathVariable int admin_id){
		as.deleteAdminById(admin_id);		
		return new ResponseEntity<Object>("done", HttpStatus.OK);
	}
	@PostMapping("/getAdminById/{admin_id}")
	public ResponseEntity<Object> getAdminById(@PathVariable int admin_id,Model m){
		Admin admin=as.getAdminById(admin_id);	
		m.addAttribute("admin", admin);	
		return new ResponseEntity<Object>(admin, HttpStatus.OK);
	}
	@PostMapping("/updateAdmin")
	public ResponseEntity<Object> updaetAdmin(@ModelAttribute Admin admin){		
		as.updateAdmin(admin);
		return new ResponseEntity<Object>("done", HttpStatus.OK);
	}


	//station
	@PostMapping("/saveStation")
	public ResponseEntity<Object> saveStation(@ModelAttribute Station station){
		int status=ss.saveStation(station);		
		return new ResponseEntity<Object>(status, HttpStatus.OK);
	}
	@PostMapping("/deleteStation/{station_id}")
	public ResponseEntity<Object> deleteStation(@PathVariable int station_id){
		ss.deleteStationById(station_id);		
		return new ResponseEntity<Object>("done", HttpStatus.OK);
	}
	@PostMapping("/getStationById/{station_id}")
	public ResponseEntity<Object> getStationById(@PathVariable int station_id,Model m){
		Station station=ss.getStationById(station_id);	
		m.addAttribute("station", station);	
		return new ResponseEntity<Object>(station, HttpStatus.OK);
	}
	@PostMapping("/updateStation")
	public ResponseEntity<Object> updaetStation(@ModelAttribute Station station){
		//int station_id=station.getStation_id();
		ss.updateStation(station);
		return new ResponseEntity<Object>("done", HttpStatus.OK);
	}



	//user
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user,RedirectAttributes redirAttrs) {

		boolean isEmailExist=userService.checkEmail(user.getEmail());
		if(isEmailExist) {
			redirAttrs.addFlashAttribute("message", "Email Already Exist");
		}else {
			User user1=userService.createUser(user);
			if(user1!=null){
				redirAttrs.addFlashAttribute("message", "Register Successfully");
			}else {
				redirAttrs.addFlashAttribute("message", "Something wrong on server");
			}
		}
		return "redirect:/addUser";
	}
	@GetMapping("/allUsers") 
	public ResponseEntity<Object> getAllStation(Model m) {			  
		List<User> user =userService.getAllUser(); 
		m.addAttribute("user", user); 
		return new ResponseEntity<Object>(user, HttpStatus.OK); 
	}
	@PostMapping("/deleteUser/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id){
		userService.deleteUserById(id);		
		return new ResponseEntity<Object>("done", HttpStatus.OK);
	}
	@PostMapping("/getUserById/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable int id,Model m){
		User user=userService.getUserById(id);	
		m.addAttribute("user", user);	
		return new ResponseEntity<Object>(user, HttpStatus.OK);
	}
	@PostMapping("/updateUser")
	public ResponseEntity<Object> updaetUser(@ModelAttribute User user){		
		userService.updateUser(user);
		return new ResponseEntity<Object>("done", HttpStatus.OK);
	}

	@PostMapping("/adminlogin")	
	public ResponseEntity<Object> adminLogin(@ModelAttribute Admin admin,HttpSession session) {
		int status=as.checkAdminExist(admin.getAdmin_email(), admin.getAdmin_password());
		if(status!=0) {
			session.setAttribute("msg", "Welcome "+admin.getAdmin_email());
		}	
		return new ResponseEntity<Object>(status, HttpStatus.OK);
	}
	@GetMapping("/destroyAdmin")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/admin_login";
	}
}
