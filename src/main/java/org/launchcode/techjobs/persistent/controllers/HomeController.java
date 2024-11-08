package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    // Task 3: Add employerRepository with AutoWired annotation
    @Autowired
    private EmployerRepository employerRepository;

    // Autowire jobRepository
    @Autowired
    private JobRepository jobRepository;

    // Task 4: Autowire SkillRepository
    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "MyJobs");
        model.addAttribute("jobs", jobRepository.findAll()); // Adds all the jobs to the model
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        // Task 3: Add employer data from employerRepository to the form template
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());

        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId,
                                    @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }

        // Find employer by ID and set it on newJob
        Employer employer = employerRepository.findById(employerId).orElse(null);
        if (employer != null) {
            newJob.setEmployer(employer);
        }

        // Find skills by IDs and set them on newJob
        List<Skill> skillList = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillList);

        // Save this new job
        jobRepository.save(newJob);

        return "redirect:/";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        return "view";
    }
}
