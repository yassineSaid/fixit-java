package Entities;

/**
 * @author lenovo
 *
 */
public class Avis 
{
	private int id;
	private String description;
	private int note;
	private String satisfaction;
	private int idUser;
	
	
	public int getId()
	{
		return id;
	}
	
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	
	public String getDescription() 
	{
		return description;
	}
	
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	
	public int getNote() 
	{
		return note;
	}
	
	
	public void setNote(int note) 
	{
		this.note = note;
	}
	
	
	public String getSatisfaction() 
	{
		return satisfaction;
	}
	
	
	public void setSatisfaction(String satisfaction) 
	{
		this.satisfaction = satisfaction;
	}
	
	
	public int getIdUser()
	{
		return idUser;
	}
	
	
	public void setIdUser(int idUser)
	{
		this.idUser = idUser;
	}
}
