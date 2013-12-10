/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;
import entity.Curso;
import java.util.List;  
import javax.faces.model.ListDataModel; 
import org.primefaces.model.SelectableDataModel;  

public class asignaturaDataModel extends ListDataModel<Curso> implements SelectableDataModel<Curso>{

    public asignaturaDataModel() {
    }
    public asignaturaDataModel(List<Curso> data){
        super(data);
    }

       
        
    @Override  
    public Curso getRowData(String rowKey) {  
        List<Curso> cursos = (List<Curso>) getWrappedData();  
        for (Curso curso : cursos) {
            if(curso.toString().equals(rowKey) ){
                return curso;
            }
        }
        return null;
    }  
  
    @Override  
    public Object getRowKey(Curso car) {  
        return car;  
    } 
}
     