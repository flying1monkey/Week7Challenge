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
    public String forceLogin()
    {
        return "login";
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

    @GetMapping("/addperson/{personId}")
    public String addPerson(@PathVariable("personId")long personId, Model model)
    {

        model.addAttribute("newPerson", personRepo.findOne(personId));
        return "addperson";
    }

    /*********************************************
     *
     * Education pages
     *
     *********************************************/
    //Don't know person
    @GetMapping("/addeducation")
    public String addEducationAskForPerson(Model model)
    {
        model.addAttribute("people", personRepo.findAll());
        model.addAttribute("currentPerson", null);
        model.addAttribute("newEducation", new Education());
        return "addeducation";
    }

    @PostMapping("/addeducation")
    public String submitEducationAskForPerson(@RequestParam("selectperson")long currentPersonId, @ModelAttribute("newEducation") Education edu, Model model)
    {
        edRepo.save(edu);
        Person current = personRepo.findOne(currentPersonId);
        current.addEducationToPerson(edu);
        personRepo.save(current);

        model.addAttribute("currentPerson", current);

        return "submiteducation";
    }

    //Already know person
    @GetMapping("/addeducation/{personid}")
    public String addEducationSpecificPerson(@PathVariable("personid") long personid, Model model)
    {
        model.addAttribute("currentPerson", personRepo.findOne(personid));
        model.addAttribute("currentPersonId", personid);
        model.addAttribute("newEducation", new Education());
        return "addeducation2";
    }

    @PostMapping("/addeducation2")
    public String submitEducationSpecificPerson(@ModelAttribute("newEdu")Education edu, @RequestParam("personId") long currentId, Model model)
    {

        //save newly created education object
        edRepo.save(edu);
        //get person from passed long and
        Person current = personRepo.findOne(currentId);
        //add newly created education object to Person's education set
        current.addEducationToPerson(edu);
        //Save person
        personRepo.save(current);

        //send education and person objects to submiteducation
        model.addAttribute("currentPerson", current);
        model.addAttribute("newEducation", edu);
        return "submiteducation";
    }


    /*********************************************
     *
     * Job pages
     *
     *********************************************/
    //Don't know person
    @GetMapping("/addjob")
    public String addJobAskForPerson(Model model)
    {
        model.addAttribute("people", personRepo.findAll());
        model.addAttribute("currentPerson", null);
        model.addAttribute("newJob", new Job());
        return "addjob";
    }

    @PostMapping("/addjob")
    public String submitJobAskForPerson(@RequestParam("selectperson")long currentPersonId, @ModelAttribute("newJob") Job job, Model model)
    {
        jobRepo.save(job);
        Person current = personRepo.findOne(currentPersonId);
        current.addJobToPerson(job);
        personRepo.save(current);

        model.addAttribute("currentPerson", current);

        return "submitjob";
    }
    //Already know person
    @GetMapping("/addjob/{personid}")
    public String addJobSpecificPerson(@PathVariable("personid") long personid, Model model)
    {
        model.addAttribute("currentPerson", personRepo.findOne(personid));
        model.addAttribute("currentPersonId", personid);
        model.addAttribute("newJob", new Job());
        return "addjob2";
    }
    @PostMapping("/addjob2")
    public String submitJobSpecificPerson(@ModelAttribute("newJob")Job job, @RequestParam("personId") long currentId, Model model)
    {

        //save newly created education object
        jobRepo.save(job);
        //get person from passed long and
        Person current = personRepo.findOne(currentId);
        //add newly created education object to Person's education set
        current.addJobToPerson(job);
        //Save person
        personRepo.save(current);

        //send education and person objects to submitjob
        model.addAttribute("currentPerson", current);
        model.addAttribute("currentJob", job);
        return "submitjob";
    }
    /*********************************************
     *
     * Skill pages
     *
     *********************************************/
    //Don't know person
    @GetMapping("/addskill")
    public String addSkillAskForPerson(Model model)
    {
        model.addAttribute("people", personRepo.findAll());
        model.addAttribute("currentPerson", null);
        model.addAttribute("newSkill", new Skill());
        return "addskill";
    }

    @PostMapping("/addskill")
    public String submitSkillAskForPerson(@RequestParam("selectperson")long currentPersonId, @ModelAttribute("newSkill")Skill skill, Model model)
    {
        skillRepo.save(skill);
        Person current = personRepo.findOne(currentPersonId);
        current.addSkillToPerson(skill);
        personRepo.save(current);

        model.addAttribute("currentPerson", current);

        return "submitskill";
    }
    //Already know person
    @GetMapping("/addskill/{personid}")
    public String addSkillSpecificPerson(@PathVariable("personid") long personid, Model model)
    {
        model.addAttribute("currentPerson", personRepo.findOne(personid));
        model.addAttribute("currentPersonId", personid);
        model.addAttribute("newSkill", new Skill());
        return "addskill2";
    }
    @PostMapping("/addskill2")
    public String submitSkillSpecificPerson(@ModelAttribute("newSkill")Skill skill, @RequestParam("personId") long currentId, Model model)
    {

        //save newly created education object
        skillRepo.save(skill);
        //get person from passed long and
        Person current = personRepo.findOne(currentId);
        //add newly created education object to Person's education set
        current.addSkillToPerson(skill);
        //Save person
        personRepo.save(current);

        //send skill and person objects to submitskill
        model.addAttribute("currentPerson", current);
        model.addAttribute("currentSkill", skill);
        return "submitskill";
    }

    /*********************************************
     *
     * Resume/final pages
     *
     *********************************************/
    @RequestMapping("/generateresume/{personid}")
    public String generate(@PathVariable("personid")long personId, Model model)
    {
        model.addAttribute("person", personRepo.findOne(personId));
        return "generateresume";
    }

    @RequestMapping("/listperson")
    public String listPeople(Model model)
    {
        model.addAttribute("allPeople", personRepo.findAll());
        return "showpeople";
    }

    /*********************************************
     *
     * Security
     *
     *********************************************/
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }
}
