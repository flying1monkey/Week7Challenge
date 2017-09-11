package edu.berliner.week7challenge.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="USER_DATA")//name of table for this entity
public class PersonUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long personId;

    @Column(name="first_name")
    @NotEmpty
    private String personFirstName;

    @Column(name="last_name")
    @NotEmpty
    private String personLastName;

    @Column(name="email", nullable=false)
    @NotEmpty
    private String personEmail;

    @Column(name="username", nullable = false, unique = true)
    @NotEmpty
    private String username;

    @Column(name="password")
    @NotEmpty
    private String password;

    @Column(name="public")
    private boolean resumeIsPublic;

    //for "deletions"
    @Column(name="enabled")
    private boolean personIsEnabled;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Education> educationSet;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Experience> experienceSet;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Skill> skillSet;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "user_secUserId"), inverseJoinColumns = @JoinColumn(name = "role_secRoleId"))
    private Collection<RoleSec> secRoles;

    public PersonUser()
    {
        this.educationSet = new HashSet<Education>();
        this.experienceSet = new HashSet<Experience>();
        this.skillSet = new HashSet<Skill>();
        this.secRoles=new ArrayList<RoleSec>();
        this.personIsEnabled=true;
        this.resumeIsPublic=true;
    }
    public PersonUser(String personFirstName, String personLastName, String personEmail, String username, String password, boolean resumeIsPublic)
    {
        this.personFirstName=personFirstName;
        this.personLastName=personLastName;
        this.personEmail=personEmail;
        this.username=username;
        this.password=password;
        this.secRoles=new ArrayList<RoleSec>();
        this.personIsEnabled=true;
        this.resumeIsPublic=resumeIsPublic;
    }

    public void addEducationToPerson(Education edu)
    {
        this.educationSet.add(edu);
    }

    public void addExpToPerson(Experience experience)
    {
        this.experienceSet.add(experience);
    }

    public void addSkillToPerson(Skill skill)
    {
        this.skillSet.add(skill);
    }

    public void addSecRole(RoleSec role)
    {
        this.secRoles.add(role);
    }

    /************************
     * Getters and setters
     ************************/

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPersonIsEnabled() {
        return personIsEnabled;
    }

    public void setPersonIsEnabled(boolean personIsEnabled) {
        this.personIsEnabled = personIsEnabled;
    }

    public Set<Education> getEducationSet() {
        return educationSet;
    }

    public void setEducationSet(Set<Education> educationSet) {
        this.educationSet = educationSet;
    }

    public Set<Experience> getExperienceSet() {
        return experienceSet;
    }

    public void setExperienceSet(Set<Experience> experienceSet) {
        this.experienceSet = experienceSet;
    }

    public Set<Skill> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(Set<Skill> skillSet) {
        this.skillSet = skillSet;
    }

    public Collection<RoleSec> getSecRoles() {
        return secRoles;
    }

    public void setSecRoles(Collection<RoleSec> secRoles) {
        this.secRoles = secRoles;
    }
}
