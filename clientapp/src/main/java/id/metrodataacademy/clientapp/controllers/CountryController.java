package id.metrodataacademy.clientapp.controllers;

import lombok.AllArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// import id.metrodataacademy.clientapp.models.Country;
import id.metrodataacademy.clientapp.services.CountryService;
import id.metrodataacademy.clientapp.services.RegionService;

@Controller
@AllArgsConstructor
@RequestMapping("/country")
public class CountryController {

    private CountryService countryService;
    private RegionService regionService;

    @GetMapping
    public String getAll(Model model, Authentication auth) {
        model.addAttribute("countrys", countryService.getAll());
        model.addAttribute("isActive", "country");
        return "country/index";
    }

//     @GetMapping("/{id}")
//     public String getById(Model model, @PathVariable Integer id) {
//         model.addAttribute("country", countryService.getById(id));
//          model.addAttribute("isActive", "country");
//         return "country/detail";
//     }

//     @GetMapping("/create")
//     public String createView(Country country, Model model) {
//         model.addAttribute("regions", regionService.getAll());
//          model.addAttribute("isActive", "country");
//         return "country/create-view";
//     }

//     @PostMapping
//     public String create(Country country) {
//         countryService.create(country);
//         return "redirect:/country?create=true";
//     }

//     @GetMapping("/update/{id}")
//     public String updateView(
//          @PathVariable Integer id,
//          Country country,
//          Model model) {
//             model.addAttribute("country", countryService.getById(id));
//             model.addAttribute("regions", regionService.getAll());
//             model.addAttribute("isActive", "country");
//             return "country/update";
//     }

//     @PutMapping("/{id}")
//     public String update(@PathVariable Integer id, Country country) {
//         countryService.update(id, country);
//         return "redirect:/country?update=true";
//     }

//     @DeleteMapping("/{id}")
//     public String delete(@PathVariable Integer id) {
//         countryService.delete(id);
//         return "redirect:/country?delete=true";
//     }
}
