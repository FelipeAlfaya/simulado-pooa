package br.com.ucsal.simulado.service;

import java.util.List;

import br.com.ucsal.simulado.model.Papel;
import br.com.ucsal.simulado.persistencia.PapelPersistence;

public class PapelService {

	
    private PapelPersistence papelPersistence = new PapelPersistence();

    public List<Papel> listarPapeis() {
        return papelPersistence.listarPapeis();
    }

    public Papel obterPapelPorId(int id) {
        return papelPersistence.obterPapelPorId(id);
    }

}
