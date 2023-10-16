import subsistema.crm.CrmService;
import subsistema2.cep.CepApi;

package Facade;

/**
 *
 * @author lucio
 */
public class Facade {

	public void migrarCliente(String nome, String cep) {
		String cidade = CepApi.getInstancia().recuperarCidade(cep);
		String estado = CepApi.getInstancia().recuperarEstado(cep);
		
		CrmService.gravarCliente(nome, cep, cidade, estado);
	}
}