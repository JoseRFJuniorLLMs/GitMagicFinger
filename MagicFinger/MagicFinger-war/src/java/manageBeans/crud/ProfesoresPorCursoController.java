package manageBeans.crud;

import entity.ProfesoresPorCurso;
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
import sessionBeans.ProfesoresPorCursoFacadeLocal;

@Named("profesoresPorCursoController")
@SessionScoped
public class ProfesoresPorCursoController implements Serializable {

    private ProfesoresPorCurso current;
    private DataModel items = null;
    @EJB
    private ProfesoresPorCursoFacadeLocal ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ProfesoresPorCursoController() {
    }

    public ProfesoresPorCurso getSelected() {
        if (current == null) {
            current = new ProfesoresPorCurso();
            current.setProfesoresPorCursoPK(new entity.ProfesoresPorCursoPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private ProfesoresPorCursoFacadeLocal getFacade() {
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

    public String prepareView(ProfesoresPorCurso vari) {
        current = vari;
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ProfesoresPorCurso();
        current.setProfesoresPorCursoPK(new entity.ProfesoresPorCursoPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getProfesoresPorCursoPK().setCurAsiIdAsignatura(current.getCurso().getCursoPK().getAsiIdAsignatura());
            current.getProfesoresPorCursoPK().setCurSemIdFecha(current.getCurso().getCursoPK().getSemIdFecha());
            current.getProfesoresPorCursoPK().setProIdProfesor(current.getProfesor().getIdProfesor());
            current.getProfesoresPorCursoPK().setCurTipIdTipoAsignatura(current.getCurso().getCursoPK().getTipIdTipoAsignatura());
            getFacade().create(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ProfesoresPorCurso creado", "Se ha creado una ProfesoresPorCurso correctamente"));
            return prepareList();
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ProfesoresPorCurso no creado", "Lo sentimos, intentelo mas tarde"));
            return null;
        }
    }

    public String prepareEdit(ProfesoresPorCurso var) {
        current = var;
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getProfesoresPorCursoPK().setCurAsiIdAsignatura(current.getCurso().getCursoPK().getAsiIdAsignatura());
            current.getProfesoresPorCursoPK().setCurSemIdFecha(current.getCurso().getCursoPK().getSemIdFecha());
            current.getProfesoresPorCursoPK().setProIdProfesor(current.getProfesor().getIdProfesor());
            current.getProfesoresPorCursoPK().setCurTipIdTipoAsignatura(current.getCurso().getCursoPK().getTipIdTipoAsignatura());
            getFacade().edit(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ProfesoresPorCurso actualizado", "Se ha actualizado correctamente"));
            return "View";
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ProfesoresPorCurso no actualizado", "Lo sentimos, intentelo mas tarde"));

            return null;
        }
    }

    public String destroy(ProfesoresPorCurso valor) {
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
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ProfesoresPorCurso eliminado", "Se ha eliminado una ProfesoresPorCurso"));
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ProfesoresPorCurso no eliminado", "Lo sentimos, intentelo mas tarde"));
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

    public ProfesoresPorCurso getProfesoresPorCurso(entity.ProfesoresPorCursoPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = ProfesoresPorCurso.class)
    public static class ProfesoresPorCursoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProfesoresPorCursoController controller = (ProfesoresPorCursoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "profesoresPorCursoController");
            return controller.getProfesoresPorCurso(getKey(value));
        }

        entity.ProfesoresPorCursoPK getKey(String value) {
            entity.ProfesoresPorCursoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entity.ProfesoresPorCursoPK();
            key.setCurAsiIdAsignatura(Integer.parseInt(values[0]));
            key.setCurTipIdTipoAsignatura(Integer.parseInt(values[1]));
            key.setCurSemIdFecha(Integer.parseInt(values[2]));
            key.setProIdProfesor(Integer.parseInt(values[3]));
            return key;
        }

        String getStringKey(entity.ProfesoresPorCursoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getCurAsiIdAsignatura());
            sb.append(SEPARATOR);
            sb.append(value.getCurTipIdTipoAsignatura());
            sb.append(SEPARATOR);
            sb.append(value.getCurSemIdFecha());
            sb.append(SEPARATOR);
            sb.append(value.getProIdProfesor());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ProfesoresPorCurso) {
                ProfesoresPorCurso o = (ProfesoresPorCurso) object;
                return getStringKey(o.getProfesoresPorCursoPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ProfesoresPorCurso.class.getName());
            }
        }
    }
}
