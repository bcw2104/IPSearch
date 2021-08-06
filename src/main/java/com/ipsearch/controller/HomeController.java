package com.ipsearch.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipsearch.object.DomainData;
import com.ipsearch.object.GeoData;
import com.ipsearch.service.SearchService;
import com.ipsearch.service.MapService;

@Controller
public class HomeController {
	@Autowired
	private SearchService searchService;
	@Autowired
	private MapService mapService;

	@GetMapping("/")
	public String home(@RequestParam(name = "search",required = false) String query,Model model,HttpServletRequest request) throws Exception {
		String clientIp = searchService.getRequestIp(request);
		String msg = null;

		if(query == null) {
			query = clientIp;
		}

		query = query.toLowerCase();

		if(searchService.checkIPv4Validation(query)) {
			GeoData geoData = searchService.getIpData(query);

			int returnCode = geoData.getReturnCode();

			if(returnCode == 0) {
				model.addAttribute("returnType", "ip");
				model.addAttribute("returnData", geoData);
				msg = geoData.print();
			}
			else {
				msg = searchService.returnMsg(returnCode);
			}
		}
		else if(searchService.checkKORDomain(query)){
			DomainData domainData = searchService.getDomainData(query.replace("http://", "").replace("https://", "").replace("www.", ""));

			int returnCode = domainData.getReturnCode();

			if(returnCode == 0) {
				model.addAttribute("returnType", "domain");
				model.addAttribute("returnData", domainData);
				msg = domainData.print();
			}
			else {
				msg = domainData.getErrorMsg();
			}
		}
		else {
			msg = searchService.returnMsg(404);
		}

		model.addAttribute("query", query);
		model.addAttribute("returnContent", msg);
		model.addAttribute("clientId", mapService.getMapClientId());
		model.addAttribute("clientIp", clientIp);

		return "home";
	}
}
