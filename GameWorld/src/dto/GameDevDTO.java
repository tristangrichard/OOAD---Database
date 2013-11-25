package dto;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard
 *
 */
public class GameDevDTO
{
	private int Did;
	private int Gid;

	public GameDevDTO(int Did, int Gid)
	{
		this.Did = Did;
		this.Gid = Gid;
	}

	public int getDid(){return Did;}
	public void setDid(int Did){this.Did = Did;}

	public int getGid(){return Gid;}
	public void setGid(int Gid){this.Gid = Gid;}
}

