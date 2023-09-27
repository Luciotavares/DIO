/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrodedisciplinas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipe
 */
public class DisciplinasComputacao implements Disciplinas {
    private List<Disciplina> repositorio;
    
    public DisciplinasComputacao() {
        repositorio = new ArrayList<>();
    }
    
    @Override
    public void guarda(Disciplina d) {
        repositorio.add(d);
    }

    @Override
    public Disciplina recupera(String codigo) {
        for (Disciplina d : repositorio)
            if (d.pegaCodigo().equals(codigo))
                return d;
        return null;
    }
    
    @Override
    public String toString() {
        String str = "### Disciplinas da Computação:\n";
        for(Disciplina d : repositorio)
            str += d + "\n";
        return str;
    }
}
