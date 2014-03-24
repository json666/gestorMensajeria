package bo.gob.aduana.sga.gestormensajeria.bean;

import java.io.Serializable;
import java.util.List;

public class MessageEmailBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<String> to;
	public List<String> toCc;
	public List<String> toCco;

	public String subject;

	public String content;
	
	private String status;

	public MessageEmailBean() {

	}

	private String nameTemplate;

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

	public String getNameTemplate() {
		return nameTemplate;
	}

	public void setNameTemplate(String nameTemplate) {
		this.nameTemplate = nameTemplate;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public List<String> getToCc() {
		return toCc;
	}

	public void setToCc(List<String> toCc) {
		this.toCc = toCc;
	}

	public List<String> getToCco() {
		return toCco;
	}

	public void setToCco(List<String> toCco) {
		this.toCco = toCco;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
