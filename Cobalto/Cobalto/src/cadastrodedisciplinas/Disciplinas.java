/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodedisciplinas;

/**
 *
 * @author felipe
 */
public interface Disciplinas {
    public void guarda(Disciplina d);
    public Disciplina recupera(String codigo);
}
