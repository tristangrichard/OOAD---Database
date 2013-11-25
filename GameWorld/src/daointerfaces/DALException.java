package daointerfaces;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard, Mikkel Barfred, Thordur Birgisson, Mathias Jeppesen
 *
 */
public class DALException extends Exception
{
	private static final long serialVersionUID = -5490114628089339322L;
	public DALException(String message) { super(message); }    
	public DALException(Exception e) { super(e); }
}
