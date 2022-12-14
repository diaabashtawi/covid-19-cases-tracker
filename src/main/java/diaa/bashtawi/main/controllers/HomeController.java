package diaa.bashtawi.main.controllers;

import diaa.bashtawi.main.models.LocationStats;
import diaa.bashtawi.main.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){

        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        model.addAttribute("LocationStats", coronaVirusDataService.getAllStats());
        model.addAttribute("totalReportedCases", totalReportedCases);

        return "home";
    }
}
