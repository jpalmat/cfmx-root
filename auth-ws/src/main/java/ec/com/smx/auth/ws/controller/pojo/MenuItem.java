package ec.com.smx.auth.ws.controller.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author acachiguango
 *
 */
public class MenuItem {
	
	private String id;
	private String title;
	private String type;
	private Boolean autorizado;
	private String href;
	private String glyphicon;
	private String estiloPanel;
	private Integer ordenMenu;
	private String descripcion;
	private List<MenuItem> menuItems = new ArrayList<>();
	private List<MenuItem> menuItemsFun = new ArrayList<>();
	private Boolean favorito;
	private String codigoVentana;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(Boolean autorizado) {
		this.autorizado = autorizado;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getGlyphicon() {
		return glyphicon;
	}

	public void setGlyphicon(String glyphicon) {
		this.glyphicon = glyphicon;
	}

	public String getEstiloPanel() {
		return estiloPanel;
	}

	public void setEstiloPanel(String estiloPanel) {
		this.estiloPanel = estiloPanel;
	}

	public Integer getOrdenMenu() {
		return ordenMenu;
	}

	public void setOrdenMenu(Integer ordenMenu) {
		this.ordenMenu = ordenMenu;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public List<MenuItem> getMenuItemsFun() {
		return menuItemsFun;
	}

	public void setMenuItemsFun(List<MenuItem> menuItemsFun) {
		this.menuItemsFun = menuItemsFun;
	}

	public Boolean getFavorito() {
		return favorito;
	}

	public void setFavorito(Boolean favorito) {
		this.favorito = favorito;
	}

	public String getCodigoVentana() {
		return codigoVentana;
	}

	public void setCodigoVentana(String codigoVentana) {
		this.codigoVentana = codigoVentana;
	}
}
