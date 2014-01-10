/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.ProfesoresPorCurso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chevo
 */
@Local
public interface ProfesoresPorCursoFacadeLocal {

    void create(ProfesoresPorCurso profesoresPorCurso);

    void edit(ProfesoresPorCurso profesoresPorCurso);

    void remove(ProfesoresPorCurso profesoresPorCurso);

    ProfesoresPorCurso find(Object id);

    List<ProfesoresPorCurso> findAll();

    List<ProfesoresPorCurso> findRange(int[] range);

    int count();
    List BuscarPorIdUniversidad(int idUniversidad);
}
