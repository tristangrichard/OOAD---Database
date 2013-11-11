package dto;

public class RankDTO
{
	private String gName;
	private int count;
	public RankDTO(String gName, int count)
	{
		this.count = count;
		this.gName = gName;
	}
	int getCount(){return count;}
	String getGname(){return gName;}
}
