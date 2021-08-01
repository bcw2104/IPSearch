package com.ipsearch.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipsearch.object.GeoData;
import com.ipsearch.service.IpService;

@Controller
public class HomeController {
	@Autowired
	private IpService ipService;

	@GetMapping("/")
	public String home(@RequestParam(name = "search",required = false) String ip,Model model,HttpServletRequest request) throws Exception {
		GeoData geoData = null;

		if(ip == null) {
			ip = ipService.getRequestIp(request);
		}

		geoData = ipService.getIpData(ip);
		model.addAttribute("geoData", geoData);

		return "home";
	}
}
