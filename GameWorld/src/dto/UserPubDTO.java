package dto;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard
 *
 */
public class UserPubDTO
{
	private String email;
	private int Pid;

	public UserPubDTO(String email, int Pid)
	{
		this.email = email;
		this.Pid = Pid;
	}
	public UserPubDTO(){}

	public String getEmail(){return email;}
	public void setEmail(String email){this.email = email;}

	public int getPid(){return Pid;}
	public void setPid(int Pid){this.Pid = Pid;}
}

