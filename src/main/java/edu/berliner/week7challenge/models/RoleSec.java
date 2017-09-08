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
	private Collection<UserSec> secUsers;

	public RoleSec()
	{
		this.secUsers=new ArrayList<UserSec>();
	}

	public void addSecUser(UserSec secUser)
	{
		this.secUsers.add(secUser);
	}

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

	public Collection<UserSec> getSecUsers()
	{
		return secUsers;
	}

	public void setSecUsers(Collection<UserSec> secUsers)
	{
		this.secUsers = secUsers;
	}
}