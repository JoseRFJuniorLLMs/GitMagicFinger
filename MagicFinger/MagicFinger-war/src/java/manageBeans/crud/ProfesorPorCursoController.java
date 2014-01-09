package manageBeans.crud;

import entity.ProfesorPorCurso;
import manageBeans.crud.util.JsfUtil;
import manageBeans.crud.util.PaginationHelper;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import sessionBeans.ProfesorPorCursoFacadeLocal;

@Named("profesorPorCursoController")
@SessionScoped
public class ProfesorPorCursoController implements Serializable {

    private ProfesorPorCurso current;
    private DataModel items = null;
    @EJB
    private ProfesorPorCursoFacadeLocal ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ProfesorPorCursoController() {
    }

    public ProfesorPorCurso getSelected() {
        if (current == null) {
            current = new ProfesorPorCurso();
            current.setProfesorPorCursoPK(new entity.ProfesorPorCursoPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private ProfesorPorCursoFacadeLocal getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(200) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView(ProfesorPorCurso vari) {
        current = vari;
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ProfesorPorCurso();
        current.setProfesorPorCursoPK(new entity.ProfesorPorCursoPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getProfesorPorCursoPK().setNombreMalla(current.getCurso().getCursoPK().getNombreMalla());
            current.getProfesorPorCursoPK().setIdProfesor(current.getProfesor().getIdProfesor());
            current.getProfesorPorCursoPK().setIdTipoAsignatura(current.getCurso().getCursoPK().getIdTipoAsignatura());
            current.getProfesorPorCursoPK().setNombreFacultad(current.getCurso().getCursoPK().getNombreFacultad());
            current.getProfesorPorCursoPK().setNombreAsignatura(current.getCurso().getCursoPK().getNombreAsignatura());
            current.getProfesorPorCursoPK().setNombreCarrera(current.getCurso().getCursoPK().getNombreCarrera());
            current.getProfesorPorCursoPK().setIdUniversidad(current.getCurso().getCursoPK().getIdUniversidad());
            current.getProfesorPorCursoPK().setIdFecha(current.getCurso().getCursoPK().getIdFecha());
            current.getProfesorPorCursoPK().setNombreDepartamento(current.getCurso().getCursoPK().getNombreDepartamento());
            getFacade().create(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ProfesorPorCurso creado", "Se ha creado una ProfesorPorCurso correctamente"));
            return prepareList();
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ProfesorPorCurso no creado", "Lo sentimos, intentelo mas tarde"));
            return null;
        }
    }

    public String prepareEdit(ProfesorPorCurso var) {
        current = var;
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getProfesorPorCursoPK().setNombreMalla(current.getCurso().getCursoPK().getNombreMalla());
            current.getProfesorPorCursoPK().setIdProfesor(current.getProfesor().getIdProfesor());
            current.getProfesorPorCursoPK().setIdTipoAsignatura(current.getCurso().getCursoPK().getIdTipoAsignatura());
            current.getProfesorPorCursoPK().setNombreFacultad(current.getCurso().getCursoPK().getNombreFacultad());
            current.getProfesorPorCursoPK().setNombreAsignatura(current.getCurso().getCursoPK().getNombreAsignatura());
            current.getProfesorPorCursoPK().setNombreCarrera(current.getCurso().getCursoPK().getNombreCarrera());
            current.getProfesorPorCursoPK().setIdUniversidad(current.getCurso().getCursoPK().getIdUniversidad());
            current.getProfesorPorCursoPK().setIdFecha(current.getCurso().getCursoPK().getIdFecha());
            current.getProfesorPorCursoPK().setNombreDepartamento(current.getCurso().getCursoPK().getNombreDepartamento());
            getFacade().edit(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ProfesorPorCurso actualizado", "Se ha actualizado correctamente"));
            return "View";
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ProfesorPorCurso no actualizado", "Lo sentimos, intentelo mas tarde"));

            return null;
        }
    }

    public String destroy(ProfesorPorCurso valor) {
        current = valor;
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ProfesorPorCurso eliminado", "Se ha eliminado una ProfesorPorCurso"));
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ProfesorPorCurso no eliminado", "Lo sentimos, intentelo mas tarde"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public ProfesorPorCurso getProfesorPorCurso(entity.ProfesorPorCursoPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = ProfesorPorCurso.class)
    public static class ProfesorPorCursoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProfesorPorCursoController controller = (ProfesorPorCursoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "profesorPorCursoController");
            return controller.getProfesorPorCurso(getKey(value));
        }

        entity.ProfesorPorCursoPK getKey(String value) {
            entity.ProfesorPorCursoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entity.ProfesorPorCursoPK();
            key.setIdUniversidad(Integer.parseInt(values[0]));
            key.setNombreFacultad(values[1]);
            key.setNombreDepartamento(values[2]);
            key.setNombreCarrera(values[3]);
            key.setNombreMalla(values[4]);
            key.setNombreAsignatura(values[5]);
            key.setIdTipoAsignatura(Integer.parseInt(values[6]));
            key.setIdFecha(Integer.parseInt(values[7]));
            key.setIdProfesor(Integer.parseInt(values[8]));
            return key;
        }

        String getStringKey(entity.ProfesorPorCursoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdUniversidad());
            sb.append(SEPARATOR);
            sb.append(value.getNombreFacultad());
            sb.append(SEPARATOR);
            sb.append(value.getNombreDepartamento());
            sb.append(SEPARATOR);
            sb.append(value.getNombreCarrera());
            sb.append(SEPARATOR);
            sb.append(value.getNombreMalla());
            sb.append(SEPARATOR);
            sb.append(value.getNombreAsignatura());
            sb.append(SEPARATOR);
            sb.append(value.getIdTipoAsignatura());
            sb.append(SEPARATOR);
            sb.append(value.getIdFecha());
            sb.append(SEPARATOR);
            sb.append(value.getIdProfesor());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ProfesorPorCurso) {
                ProfesorPorCurso o = (ProfesorPorCurso) object;
                return getStringKey(o.getProfesorPorCursoPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ProfesorPorCurso.class.getName());
            }
        }
    }
}
