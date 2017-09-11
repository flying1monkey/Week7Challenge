package edu.berliner.week7challenge.models;


import javax.persistence.*;
import java.util.Collection;
import java.util.ArrayList;

@Entity
public class RoleSec
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long secRoleId;

	@Column(unique = true)
	private String secRoleName;

	@ManyToMany(mappedBy = "secRoles")
	private Collection<PersonUser> secUsers;

	//Constructors
	public RoleSec()
	{
		this.secUsers=new ArrayList<PersonUser>();
	}

	public RoleSec(String role)
	{
		this.secRoleName=role;
		this.secUsers=new ArrayList<PersonUser>();
	}

	//Methods
	public void addSecUser(PersonUser secUser)
	{
		this.secUsers.add(secUser);
	}

	//Getters/Setters
	public long getSecRoleId()
	{
		return secRoleId;
	}

	public void setSecRoleId(long secRoleId)
	{
		this.secRoleId = secRoleId;
	}

	public String getSecRoleName()
	{
		return secRoleName;
	}

	public void setSecRoleName(String secRoleName)
	{
		this.secRoleName = secRoleName;
	}

	public Collection<PersonUser> getSecUsers()
	{
		return secUsers;
	}

	public void setSecUsers(Collection<PersonUser> secUsers)
	{
		this.secUsers = secUsers;
	}
}