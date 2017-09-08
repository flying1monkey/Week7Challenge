package edu.berliner.week7challenge.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="USER_DATA")//name of table for this entity
public class UserSec
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long secUserId;

	@Column(name="email", nullable=false)
	private String email;

	@Column(name="username", nullable = false, unique = true)
	private String username;

	@Column(name="password")
	private String password;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="enabled")
	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "user_secUserId"), inverseJoinColumns = @JoinColumn(name = "role_secRoleId"))
	private Collection<RoleSec> secRoles;

	public UserSec()
	{
		this.secRoles=new ArrayList<RoleSec>();
	}
	public void addSecRole(RoleSec role)
	{

		this.secRoles.add(role);
	}

//    Getters and setters


	public long getSecUserId()
	{
		return secUserId;
	}

	public void setSecUserId(long secUserId)
	{
		this.secUserId = secUserId;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	public Collection<RoleSec> getSecRoles()
	{
		return secRoles;
	}

	public void setSecRoles(Collection<RoleSec> secRoles)
	{
		this.secRoles = secRoles;
	}
}
