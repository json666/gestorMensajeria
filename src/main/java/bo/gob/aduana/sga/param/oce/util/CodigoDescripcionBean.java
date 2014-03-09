package bo.gob.aduana.sga.param.oce.util;

public class CodigoDescripcionBean {
	private String codigo;
	private String descripcion;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String toString() {
		return "codigo: " + this.codigo + " descripcion: " + this.descripcion;
	}

}
