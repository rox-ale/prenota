package it.alessiorossato.prenotaMensa.exception;

public class EccezioneGenericaException extends Exception
{
	private static final long serialVersionUID = 1L;

	private String messaggio;

	public EccezioneGenericaException()
	{
		super();
	}

	public EccezioneGenericaException(String Messaggio)
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
