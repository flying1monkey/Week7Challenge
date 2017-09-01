package edu.berliner.week6challenge.controllers;

import edu.berliner.week6challenge.models.Education;
import edu.berliner.week6challenge.models.Job;
import edu.berliner.week6challenge.models.Person;
import edu.berliner.week6challenge.models.Skill;
import edu.berliner.week6challenge.repositories.EducationRepository;
import edu.berliner.week6challenge.repositories.JobRepository;
import edu.berliner.week6challenge.repositories.PersonRepository;
import edu.berliner.week6challenge.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController
{
    @Autowired
    EducationRepository edRepo;
    @Autowired
    JobRepository jobRepo;
    @Autowired
    PersonRepository personRepo;
    @Autowired
    SkillRepository skillRepo;


    @RequestMapping("/")
    public String welcomePage()
    {
        return "home";
    }
    @RequestMapping("/home")
    public String welcomePageAgain()
    {
        return "home";
    }

    /*********************************************
     *
     * Person pages
     *
     *********************************************/
    @GetMapping("/addperson")
    public String addPerson(Model model)
    {
        model.addAttribute("newPerson", new Person());
        return "addperson";
    }

    @PostMapping("/addperson")
    public String submitEducation(@Valid @ModelAttribute("newPerson")Person person, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "addperson";
        }
        personRepo.save(person);
        return "submitperson";
    }

    /*********************************************
     *
     * Education pages
     *
     *********************************************/
    @GetMapping("/addeducation/{personid}")
    public String addEducation(@PathVariable("personid") long personid, Model model)
    {
        Education eduObj = new Education(personRepo.findOne(personid));
        model.addAttribute("newEdu", eduObj);
        System.out.println(eduObj.getEducationPerson().getPersonId());
        return "addeducation";
    }

    @PostMapping("/addeducation")
    public String submitEducation(@Valid @ModelAttribute("newEdu")Education edu, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "addeducation";
        }
        edRepo.save(edu);
        //System.out.println(edu.getEducationPerson().getPersonId());

        return "submiteducation";
    }
    /*********************************************
     *
     * Job pages
     *
     *********************************************/
    @GetMapping("/addjob")
    public String addJob(Model model)
    {
        model.addAttribute("newJob", new Job());
        return "addjob";
    }

    /*********************************************
     *
     * Skill pages
     *
     *********************************************/
    @GetMapping("/addskill")
    public String addSkill(Model model)
    {
        model.addAttribute("newSkill", new Skill());
        return "addskill";
    }

    /*********************************************
     *
     * Resume/final pages
     *
     *********************************************/
}
