/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entity.Grupos;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

public class gruposDataModel extends ListDataModel<Grupos> implements SelectableDataModel<Grupos> {

    public gruposDataModel() {
    }

    public gruposDataModel(List<Grupos> data) {
        super(data);
    }

    @Override
    public Grupos getRowData(String rowKey) {
        List<Grupos> grupos = (List<Grupos>) getWrappedData();
        for (Grupos grupo : grupos) {
            if (grupo.toString().equals(rowKey)) {
                return grupo;
            }
        }
        return null;
    }

    @Override
    public Grupos getRowKey(Grupos gru) {
        return gru;
    }
    
}
