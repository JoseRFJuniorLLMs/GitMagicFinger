/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.ProfesoresPorDepartamento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface ProfesoresPorDepartamentoFacadeLocal {

    void create(ProfesoresPorDepartamento profesoresPorDepartamento);

    void edit(ProfesoresPorDepartamento profesoresPorDepartamento);

    void remove(ProfesoresPorDepartamento profesoresPorDepartamento);

    ProfesoresPorDepartamento find(Object id);

    List<ProfesoresPorDepartamento> findAll();

    List<ProfesoresPorDepartamento> findRange(int[] range);

    int count();
    
    List BuscarPorIdUniversidad(int idUniversidad);
}
