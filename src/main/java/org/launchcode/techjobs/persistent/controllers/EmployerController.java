package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/employers")
public class EmployerController {

    // Task 2: Add a private field of EmployerRepository type called employerRepository
    // Added @Autowired to handle dependency injection
    @Autowired
    private EmployerRepository employerRepository;

    // Task 2: Add an index method that responds at /employers with a list of all employers
    // Uses employers/index template and adds list of all employers to the model
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("title", "All Employers");
        model.addAttribute("employers", employerRepository.findAll());
        return "employers/index";
    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    // Task 2: Use employerRepository and the appropriate method to save a valid object
    // Added employerRepository.save() to save new employer when form is valid
    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                         Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Employer");
            return "employers/add";
        }
        employerRepository.save(newEmployer);
        return "redirect:/employers/";
    }

    // Task 2: Use employer object's id field to grab the correct information from employerRepository
    // Fixed Optional<Employer> to use employerRepository.findById()
    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {
        Optional optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employer);
            return "employers/view";
        } else {
            return "redirect:../";
        }
    }
}