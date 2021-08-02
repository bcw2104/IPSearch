package com.ipsearch.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipsearch.object.GeoData;
import com.ipsearch.service.IpService;
import com.ipsearch.service.MapService;

@Controller
public class HomeController {
	@Autowired
	private IpService ipService;
	@Autowired
	private MapService mapService;

	@GetMapping("/")
	public String home(@RequestParam(name = "search",required = false) String ip,Model model,HttpServletRequest request) throws Exception {
		GeoData geoData = null;

		String clientIp = ipService.getRequestIp(request);

		if(ip == null) {
			ip = clientIp;
		}

		geoData = ipService.getIpData(ip);

		int returnCode = geoData.getReturnCode();

		if(returnCode == 0) {
			model.addAttribute("geoData", geoData);
			model.addAttribute("clientId", mapService.getMapClientId());
			model.addAttribute("searchIp", ip);
		}
		else {
			model.addAttribute("errorMsg",ipService.returnMsg(returnCode));
		}

		model.addAttribute("clientIp", clientIp);

		return "home";
	}
}
