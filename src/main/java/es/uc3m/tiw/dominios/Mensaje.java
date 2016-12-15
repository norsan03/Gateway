package es.uc3m.tiw.dominios;

public class Mensaje {
	
	private long id;
	private String IdEmisor;
	private String IdReceptor;
	private long IdProducto;
	private String mensaje;

	public Mensaje() {
	}
	
	public Mensaje(long id, String mensaje, long producto, long IdProducto, String IdEmisor, String IdReceptor) {
		super();
		this.id = id;
		this.IdEmisor = IdEmisor;
		this.IdReceptor= IdReceptor;
		this.mensaje = mensaje;
		this.IdProducto = IdProducto;
	}
	
	public long getIdproducto() {
		return IdProducto;
	}
	public void setIdProducto(long IdProducto) {
		this.IdProducto = IdProducto;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIdEmisor() {
		return IdEmisor;
	}
	public void setIdEmisor(String IdEmisor) {
		this.IdEmisor = IdEmisor;
	}
	public String getIdReceptor() {
		return IdReceptor;
	}
	public void setIdReceptor(String IdReceptor) {
		this.IdReceptor = IdReceptor;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
