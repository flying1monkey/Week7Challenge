package edu.berliner.week7challenge.controllers;

import edu.berliner.week7challenge.models.*;
import edu.berliner.week7challenge.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Set;

@Controller
public class MainController
{
    @Autowired
    EducationRepository edRepo;
    @Autowired
    ExperienceRepository experienceRepository;
    @Autowired
    JobRepository jobRepo;
    @Autowired
    PersonUserRepository personUserRepository;
    @Autowired
    SkillRepository skillRepo;
    @Autowired
    RoleSecRepository roleRepo;



    @RequestMapping({"/home","/"})
    public String welcomePage(Model model, Principal principal)
    {
        setup();
        if(principal!=null)
        {
            model.addAttribute("currentuser", personUserRepository.findByUsername(principal.getName()));
        }
        else
        {
            model.addAttribute("currentuser", null);
        }
        return "home";
    }

    @RequestMapping("/aboutme")
    public String aboutMe(Model model, Principal principal)
    {
        PersonUser user = personUserRepository.findByUsername(principal.getName());
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
     * PersonUser pages
     *
     *********************************************/

    @PostMapping("/addperson")
    public String submitPerson(@Valid @ModelAttribute("newPerson")PersonUser person, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "addperson";
        }
        personUserRepository.save(person);
        return "submitperson";
    }

    @GetMapping("/editperson/{personId}")
    public String editPerson(@PathVariable("personId")long personId, Model model)
    {
        model.addAttribute("newuser", personUserRepository.findOne(personId));
        return "editperson";
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
        model.addAttribute("people", personUserRepository.findAll());
        model.addAttribute("currentPerson", null);
        model.addAttribute("newEducation", new Education());
        return "addeducation";
    }

    @PostMapping("/addeducation")
    public String submitEducationAskForPerson(@RequestParam("selectperson")long currentPersonId, @ModelAttribute("newEducation") Education edu, Model model)
    {
        PersonUser current = personUserRepository.findOne(currentPersonId);
        current.addEducationToPerson(edu);
        personUserRepository.save(current);

        model.addAttribute("currentPerson", current);

        return "submiteducation";
    }

    //Already know person
    @GetMapping("/addeducation/{personid}")
    public String addEducationSpecificPerson(@PathVariable("personid") long personid, Model model)
    {
        model.addAttribute("currentPerson", personUserRepository.findOne(personid));
        model.addAttribute("currentPersonId", personid);
        model.addAttribute("newEducation", new Education());
        return "addeducation2";
    }

    @PostMapping("/addeducation2")
    public String submitEducationSpecificPerson(@ModelAttribute("newEdu")Education edu, @RequestParam("personId") long currentId, Model model)
    {


        //get person from passed long and
        PersonUser current = personUserRepository.findOne(currentId);


        //add newly created education object to PersonUser's education set
        current.addEducationToPerson(edu);
        //Save person
        personUserRepository.save(current);

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
        model.addAttribute("people", personUserRepository.findAll());
        model.addAttribute("currentPerson", null);
        model.addAttribute("newexp", new Experience());
        return "addexp";
    }

    @PostMapping("/addexp")
    public String submitExpAskForPerson(@RequestParam("selectperson")long currentPersonId, @ModelAttribute("newExp") Experience experience, Model model)
    {
        PersonUser current = personUserRepository.findOne(currentPersonId);
        current.addExpToPerson(experience);
        personUserRepository.save(current);

        model.addAttribute("currentPerson", current);

        return "submitexp";
    }
    //Already know person
    @GetMapping("/addexp/{personid}")
    public String addExpSpecificPerson(@PathVariable("personid") long personid, Model model)
    {
        model.addAttribute("currentPerson", personUserRepository.findOne(personid));
        model.addAttribute("currentPersonId", personid);
        model.addAttribute("newExp", new Experience());
        return "addexp2";
    }
    @PostMapping("/addexp2")
    public String submitExpSpecificPerson(@ModelAttribute("newExp")Experience experience, @RequestParam("personId") long currentId, Model model)
    {


        //get person from passed long and
        PersonUser current = personUserRepository.findOne(currentId);
        //add newly created education object to PersonUser's education set
        current.addExpToPerson(experience);
        //Save person
        personUserRepository.save(current);

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
        model.addAttribute("people", personUserRepository.findAll());
        model.addAttribute("currentPerson", null);
        model.addAttribute("newSkill", new Skill());
        return "addskill";
    }

    @PostMapping("/addskill")
    public String submitSkillAskForPerson(@RequestParam("selectperson")long currentPersonId, @ModelAttribute("newSkill")Skill skill, Model model)
    {
        PersonUser current = personUserRepository.findOne(currentPersonId);
        current.addSkillToPerson(skill);
        personUserRepository.save(current);

        model.addAttribute("currentPerson", current);

        return "submitskill";
    }

    //Already know person
    @GetMapping("/addskill/{personid}")
    public String addSkillSpecificPerson(@PathVariable("personid") long personid, Model model)
    {
        model.addAttribute("currentPerson", personUserRepository.findOne(personid));
        model.addAttribute("currentPersonId", personid);
        model.addAttribute("newSkill", new Skill());
        return "addskill2";
    }

    @PostMapping("/addskill2")
    public String submitSkillSpecificPerson(@ModelAttribute("newSkill")Skill skill, @RequestParam("personId") long currentId, Model model)
    {

        //get person from passed long and
        PersonUser current = personUserRepository.findOne(currentId);
        //add newly created education object to PersonUser's education set
        current.addSkillToPerson(skill);
        //Save person
        personUserRepository.save(current);

        //send skill and person objects to submitskill
        model.addAttribute("currentPerson", current);
        model.addAttribute("currentSkill", skill);
        return "submitskill";
    }
    /*********************************************
     *
     * Job posting pages
     *
     *********************************************/
    @GetMapping("/addjob")
    public String addJob(Model model)
    {
        model.addAttribute("newJob", new Job());
        return "addjob";
    }

    @PostMapping("/addjob")
    public String submitJob(@ModelAttribute("newJob") Job job)
    {
        jobRepo.save(job);
        return "submitjob";
    }

    @RequestMapping("/showjobs")
    public String showJobs(Model model)
    {
        model.addAttribute("Jobs", jobRepo.findAll());
        return "showjobs";
    }

    @RequestMapping("/submitjob/{jobId}")
    public String detailJob(@PathVariable("jobId")long jobId, Model model)
    {
        model.addAttribute("newJob", jobRepo.findOne(jobId));
        return "submitjob";
    }
    /*********************************************
     *
     * Resume/final pages
     *
     *********************************************/
    @RequestMapping("/generateresume/{personid}")
    public String generate(@PathVariable("personid")long personId, Model model)
    {
        model.addAttribute("person", personUserRepository.findOne(personId));
        return "generateresume";
    }
    @GetMapping("/generate")
    public String generateGetPerson(Model model)
    {
        model.addAttribute("allPeople", personUserRepository.findAll());
        return "generate";
    }

    @RequestMapping("/listperson")
    public String listPeople(Model model)
    {
        model.addAttribute("allPeople", personUserRepository.findAll());
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
        model.addAttribute("newuser", new PersonUser());
        return "signup";
    }

    @PostMapping("/signup")
    public String addNewUser(@Valid @ModelAttribute("newuser") PersonUser user, @RequestParam("selectrole") String role, Model model, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "signup";
        }
        user.addSecRole(roleRepo.findBySecRoleName(role));
        personUserRepository.save(user);

        model.addAttribute("currentuser",user);
        return "aboutme";
    }
    /*********************************************
     *
     * Search
     *
     *********************************************/
    @GetMapping("/search")
    public String search(Model model)
    {
        model.addAttribute("searchjob",new Job());
        model.addAttribute("searchpeople",new PersonUser());
        model.addAttribute("searchschools",new Education());
        return "search";
    }

    @PostMapping("/searchdesc")
    public String searchJobs(@ModelAttribute("searchjob") Job job, Model model)
    {
        model.addAttribute("Jobs", jobRepo.findAllByJobDescriptionContains(job.getJobDescription()));
        return "showjobs";
    }
    @PostMapping("/searchco")
    public String searchCompany(@ModelAttribute("searchjob") Job job, Model model)
    {
        model.addAttribute("Jobs", jobRepo.findAllByJobEmployerContains(job.getJobEmployer()));
        return "showjobs";
    }
    @PostMapping("/searchname")
    public String searchCompany(@ModelAttribute("searchpeople") PersonUser person, Model model)
    {
        ArrayList<PersonUser> firstnames=new ArrayList<PersonUser>();
        ArrayList<PersonUser> lastnames=new ArrayList<PersonUser>();
        firstnames=personUserRepository.findAllByPersonFirstNameContains(person.getPersonFirstName());
        lastnames=personUserRepository.findAllByPersonLastNameContains(person.getPersonFirstName());
        for(PersonUser tempperson:lastnames)
        {
            firstnames.add(tempperson);
        }

        model.addAttribute("allPeople", lastnames);
        return "showpeople";
    }
    @PostMapping("/searchschool")
    public String searchSchool(@ModelAttribute("searchschools") Education edu, Model model)
    {
        //NOT WORKING
        ArrayList<Education> tempEdu = edRepo.findAllByEducationSchoolName(edu.getEducationSchoolName());
        ArrayList<PersonUser> people=new ArrayList<PersonUser>();

        for(Education ed:tempEdu)
        {
           //find person that the ed item belongs to

        }
        model.addAttribute("allPeople", personUserRepository.findAll());
        return "showpeople";
    }
    @RequestMapping("/myjobs")
    public String myJobs(@ModelAttribute ("search") Job job, Model model, Principal principal)
    {
//        NOT WORKING
        PersonUser current = personUserRepository.findByUsername(principal.getName());
        Set<Skill> tempSkill = current.getSkillSet();

        ArrayList<PersonUser> test = personUserRepository.findAllByEducationSetContains("English");

        model.addAttribute("Jobs", jobRepo.findAll());
        return "showJobs";
    }
    /*********************************************
     *
     * Setup
     *
     *********************************************/
    public void setup()
    {
        if(roleRepo.count()==0)
        {
            RoleSec admin=new RoleSec("ADMIN");
            RoleSec jobseeker=new RoleSec("JOBSEEKER");
            RoleSec recruiter=new RoleSec("RECRUITER");
            roleRepo.save(admin);
            roleRepo.save(jobseeker);
            roleRepo.save(recruiter);
            System.out.println("Added roles");
        }
        if (personUserRepository.count()==0)
        {
            PersonUser admin = new PersonUser("AdminF", "AdminL", "admin@admin.com", "admin","password", false);
            admin.addSecRole(roleRepo.findBySecRoleName("ADMIN"));
            admin.addSecRole(roleRepo.findBySecRoleName("JOBSEEKER"));
            admin.addSecRole(roleRepo.findBySecRoleName("RECRUITER"));
            personUserRepository.save(admin);
            System.out.println("Added admin");
        }
    }
    @GetMapping("/addseeker")
    public String addJobseeker()
    {
        if(personUserRepository.findByUsername("jane")==null)
        {
            PersonUser jobSeeker = new PersonUser("Jane", "Doe", "jane@doe.com", "jane", "password", true);
            jobSeeker.addSecRole(roleRepo.findBySecRoleName("JOBSEEKER"));
            personUserRepository.save(jobSeeker);
        }
        return "redirect:/";
    }
    @GetMapping("/addrecruiter")
    public String addRecruiter()
    {
        if(personUserRepository.findByUsername("john")==null)
        {
            PersonUser recruiter = new PersonUser("John", "Doe", "john@doe.com", "john", "password", true);
            recruiter.addSecRole(roleRepo.findBySecRoleName("RECRUITER"));
            personUserRepository.save(recruiter);
        }
        return "redirect:/";
    }
}
