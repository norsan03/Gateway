package es.uc3m.tiw.dominios;




public class Mensaje {
	

	private long id;

	private long IdEmisor;

	private long IdReceptor;

	private long IdProducto;

	private String mensaje;
	

	public Mensaje() {
	}
	
	public Mensaje(long id, long origenId, long destinoId, String mensaje, long producto, long IdProducto, long IdEmisor, long IdReceptor) {
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
	public long getIdEmisor() {
		return IdEmisor;
	}
	public void setIdEmisor(long IdEmisor) {
		this.IdEmisor = IdEmisor;
	}
	public long getIdReceptor() {
		return IdReceptor;
	}
	public void setIdReceptor(long IdReceptor) {
		this.IdReceptor = IdReceptor;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
