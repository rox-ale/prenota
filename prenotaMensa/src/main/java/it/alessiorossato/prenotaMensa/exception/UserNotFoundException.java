package it.alessiorossato.prenotaMensa.exception;

public class UserNotFoundException extends Exception
{
	private static final long serialVersionUID = 1L;

	private String messaggio;

	public UserNotFoundException()
	{
		super();
	}

	public UserNotFoundException(String Messaggio)
	{
		super(Messaggio);
		this.messaggio = Messaggio;
	}

	public String getMessaggio()
	{
		return messaggio;
	}

	public void setMessaggio(String messaggio)
	{
		this.messaggio = messaggio;
	}

}
