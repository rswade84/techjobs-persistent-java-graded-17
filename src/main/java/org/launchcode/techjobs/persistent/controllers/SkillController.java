// Manages skill CRUD operations
package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";
    }

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());
        return "skills/add";
    }

    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                      Errors errors, Model model) {

        if (errors.hasErrors()) { // Validation check
            return "skills/add";
        }

        skillRepository.save(newSkill); // Save if valid

        return "redirect:/skills"; // Redirect after
    }

    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {

        // Find id
        Optional<Skill> optSkill = skillRepository.findById(skillId);

        if (optSkill.isPresent()) { // If present
            Skill skill = optSkill.get(); // Get skill obj
            model.addAttribute("skill", skill); // Add to the model view
            return "skills/view";

        } else {

            return "redirect:/skills";
        }
    }
}