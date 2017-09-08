package edu.berliner.week7challenge.controllers;

import edu.berliner.week7challenge.models.*;
import edu.berliner.week7challenge.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MainController
{
    @Autowired
    EducationRepository edRepo;
    @Autowired
    ExperienceRepository experienceRepository;
    @Autowired
    PersonRepository personRepo;
    @Autowired
    SkillRepository skillRepo;
    @Autowired
    RoleSecRepository roleRepo;
    @Autowired
    UserSecRepository userRepo;


    @RequestMapping({"/home","/"})
    public String welcomePage()
    {
        return "home";
    }

    @RequestMapping("/aboutme")
    public String aboutMe(Model model, Principal principal)
    {
        UserSec user = userRepo.findByUsername(principal.getName());
        model.addAttribute("currentuser", user);
        /*
         * UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         * System.out.println("User has authorities: " + userDetails.getAuthorities());
         */
        /*
         * GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getSecRoleName());
         */
        return "aboutme";
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
        //edRepo.save(edu);
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
        //edRepo.save(edu);
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
     * Experience pages
     *
     *********************************************/
    //Don't know person
    @GetMapping("/addexp")
    public String addExpAskForPerson(Model model)
    {
        model.addAttribute("people", personRepo.findAll());
        model.addAttribute("currentPerson", null);
        model.addAttribute("newexp", new Experience());
        return "addexp";
    }

    @PostMapping("/addexp")
    public String submitExpAskForPerson(@RequestParam("selectperson")long currentPersonId, @ModelAttribute("newExp") Experience experience, Model model)
    {
        experienceRepository.save(experience);
        Person current = personRepo.findOne(currentPersonId);
        current.addExpToPerson(experience);
        personRepo.save(current);

        model.addAttribute("currentPerson", current);

        return "submitexp";
    }
    //Already know person
    @GetMapping("/addexp/{personid}")
    public String addExpSpecificPerson(@PathVariable("personid") long personid, Model model)
    {
        model.addAttribute("currentPerson", personRepo.findOne(personid));
        model.addAttribute("currentPersonId", personid);
        model.addAttribute("newExp", new Experience());
        return "addexp2";
    }
    @PostMapping("/addexp2")
    public String submitExpSpecificPerson(@ModelAttribute("newExp")Experience experience, @RequestParam("personId") long currentId, Model model)
    {

        //save newly created education object
        experienceRepository.save(experience);
        //get person from passed long and
        Person current = personRepo.findOne(currentId);
        //add newly created education object to Person's education set
        current.addExpToPerson(experience);
        //Save person
        personRepo.save(current);

        //send education and person objects to submitexp
        model.addAttribute("currentPerson", current);
        model.addAttribute("currentExp", experience);
        return "submitexp";
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
    @GetMapping("/generate")
    public String generateGetPerson(Model model)
    {
        model.addAttribute("allPeople", personRepo.findAll());
        return "generate";
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

    @GetMapping("/signup")
    public String signup(Model model)
    {
        model.addAttribute("newuser", new UserSec());
        return "signup";
    }

    @PostMapping("/signup")
    public String addNewUser(@ModelAttribute("newuser") UserSec user, @RequestParam("selectrole") String role)
    {

        user.addSecRole(roleRepo.findBySecRoleName(role)); //addRole is equivalent to my addToCollection()
        userRepo.save(user);

        return "home";
    }
}
