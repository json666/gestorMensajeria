package bo.gob.aduana.sga.gestormensajeria.model;

import java.io.Serializable;




public class Opcion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String link;
	private String textLink;
	
	public Opcion(String link, String textLink) {
		this.link = link;
		this.textLink = textLink;
	}
	
	public Opcion(){
		
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTextLink() {
		return textLink;
	}
	public void setTextLink(String textLink) {
		this.textLink = textLink;
	}

}
