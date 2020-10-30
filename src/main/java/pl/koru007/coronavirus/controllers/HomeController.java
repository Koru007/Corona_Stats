package pl.koru007.coronavirus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.koru007.coronavirus.modules.LocationStats;
import pl.koru007.coronavirus.services.CoronaDataServer;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaDataServer coronaDataServer;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaDataServer.getAllStats();
        int totalCases = allStats.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        int totalNewCases = allStats.stream().mapToInt(LocationStats::getDiffFromPrevDay).sum();

        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }

}
