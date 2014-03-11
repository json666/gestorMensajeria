package bo.gob.aduana.sga.gestormensajeria.bean;

import java.io.Serializable;

public class MessageEmailBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MessageEmailBean(){
		
	}
	public String subject;

	public String content;

	public String to;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
