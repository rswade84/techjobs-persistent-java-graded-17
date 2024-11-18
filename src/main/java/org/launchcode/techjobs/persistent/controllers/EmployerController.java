// Manages employer CRUD operations
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

/**
 * Created by LaunchCode
 *  handles /employers, CRUD ops for empl obj
 */

@Controller
@RequestMapping("/employers")
public class EmployerController {

    // Task 2: Add a private field of EmployerRepository type called employerRepository
    @Autowired // Creates instance of class and handles dependency injection
    private EmployerRepository employerRepository;

    // Task 2: Add an index method that responds at /employers with a list of all employers
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("title", "All Employers");
        model.addAttribute("employers", employerRepository.findAll());
        return "employers/index";
    }

    // Creates new Employer obj and adds to view
    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    // Task 2: Use employerRepository and the appropriate method to save a valid object
    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) { // Validation check (adds to view)
            model.addAttribute("title", "Add Employer");
            return "employers/add";
        }
        employerRepository.save(newEmployer); // Save new employer when form is valid
        return "redirect:/employers/";
    }

    // Task 2: Use employer object's id field to grab the correct information from employerRepository
    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional optEmployer = employerRepository.findById(employerId); // Find Id

        if (optEmployer.isPresent()) { // If present
            Employer employer = (Employer) optEmployer.get(); // get the employer obj
            model.addAttribute("employer", employer); // add it to the view
            return "employers/view"; // show the page
        } else {
            return "redirect:../"; // redirect if not found
        }
    }
}