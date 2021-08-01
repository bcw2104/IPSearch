package com.ipsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipsearch.service.IpService;

@Controller
public class HomeController {
	@Autowired
	private IpService ipService;

	@GetMapping("/")
	public String home(@RequestParam(name = "search",required = false) String ip,Model model) throws Exception {

		if(ip != null) {
			final String ipData = ipService.getIpData(ip);

			model.addAttribute("ipData", ipData);
		}

		return "home";
	}
}
