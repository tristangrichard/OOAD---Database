package dto;

import java.util.Date;

public class ProduktBatchDTO  {
	private int pbId;                     // i omraadet 1-99999999
	private int status;					// 0: ikke paabegyndt, 1: under produktion, 2: afsluttet
	private int receptId;
	private Date start;
	private Date slut;
	public ProduktBatchDTO(){

	}
	public ProduktBatchDTO(int pbId, int status, int receptId, Date start, Date slut) {
		this.pbId = pbId;
		this.status = status;
		this.receptId = receptId;
		this.start = start;
		this.slut = slut;
	}

	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getSlut() {
		return slut;
	}
	public void setSlut(Date slut) {
		this.slut = slut;
	}
	public int getPbId() { return pbId; }
	public void setPbId(int pbId) { this.pbId = pbId; }
	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }
	public int getReceptId() { return receptId; }
	public void setReceptId(int receptId) { this.receptId = receptId; }
	public String toString() { return pbId + "\t" + status + "\t" + receptId; }
}