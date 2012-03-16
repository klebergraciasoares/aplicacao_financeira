package financeiro.web.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import financeiro.categoria.Categoria;
import financeiro.categoria.CategoriaRN;

@FacesConverter(forClass=Categoria.class)
public class CategoriaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		CategoriaRN categoriaRN = null;
		Integer codigo = 0;
		if (value != null && value.trim().length() > 0) {
			codigo = Integer.parseInt(value);
			try {
				categoriaRN = new CategoriaRN();
				 
				//c.setCodigo(codigo);
				
				return categoriaRN.carregar(codigo);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ConverterException(
						"Não foi possível encontrar a categoria de código "
								+ value + "." + e.getMessage());
			}
		}
		return null;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Categoria categoria = (Categoria) value;
			return categoria.getCodigo().toString();
		}
		return "";
	}

}
