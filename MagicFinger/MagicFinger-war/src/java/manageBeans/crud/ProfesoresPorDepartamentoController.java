package manageBeans.crud;

import entity.ProfesoresPorDepartamento;
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
import sessionBeans.ProfesoresPorDepartamentoFacadeLocal;

@Named("profesoresPorDepartamentoController")
@SessionScoped
public class ProfesoresPorDepartamentoController implements Serializable {

    private ProfesoresPorDepartamento current;
    private DataModel items = null;
    @EJB
    private ProfesoresPorDepartamentoFacadeLocal ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ProfesoresPorDepartamentoController() {
    }

    public ProfesoresPorDepartamento getSelected() {
        if (current == null) {
            current = new ProfesoresPorDepartamento();
            current.setProfesoresPorDepartamentoPK(new entity.ProfesoresPorDepartamentoPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private ProfesoresPorDepartamentoFacadeLocal getFacade() {
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

    public String prepareView(ProfesoresPorDepartamento vari) {
        current = vari;
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ProfesoresPorDepartamento();
        current.setProfesoresPorDepartamentoPK(new entity.ProfesoresPorDepartamentoPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getProfesoresPorDepartamentoPK().setProIdProfesor(current.getProfesor().getIdProfesor());
            current.getProfesoresPorDepartamentoPK().setDepIdDepartamento(current.getDepartamento().getIdDepartamento());
            getFacade().create(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ProfesoresPorDepartamento creado", "Se ha creado una ProfesoresPorDepartamento correctamente"));
            return prepareList();
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ProfesoresPorDepartamento no creado", "Lo sentimos, intentelo mas tarde"));
            return null;
        }
    }

    public String prepareEdit(ProfesoresPorDepartamento var) {
        current = var;
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getProfesoresPorDepartamentoPK().setProIdProfesor(current.getProfesor().getIdProfesor());
            current.getProfesoresPorDepartamentoPK().setDepIdDepartamento(current.getDepartamento().getIdDepartamento());
            getFacade().edit(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ProfesoresPorDepartamento actualizado", "Se ha actualizado correctamente"));
            return "View";
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ProfesoresPorDepartamento no actualizado", "Lo sentimos, intentelo mas tarde"));

            return null;
        }
    }

    public String destroy(ProfesoresPorDepartamento valor) {
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
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ProfesoresPorDepartamento eliminado", "Se ha eliminado una ProfesoresPorDepartamento"));
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ProfesoresPorDepartamento no eliminado", "Lo sentimos, intentelo mas tarde"));
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

    public ProfesoresPorDepartamento getProfesoresPorDepartamento(entity.ProfesoresPorDepartamentoPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = ProfesoresPorDepartamento.class)
    public static class ProfesoresPorDepartamentoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProfesoresPorDepartamentoController controller = (ProfesoresPorDepartamentoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "profesoresPorDepartamentoController");
            return controller.getProfesoresPorDepartamento(getKey(value));
        }

        entity.ProfesoresPorDepartamentoPK getKey(String value) {
            entity.ProfesoresPorDepartamentoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entity.ProfesoresPorDepartamentoPK();
            key.setProIdProfesor(Integer.parseInt(values[0]));
            key.setDepIdDepartamento(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(entity.ProfesoresPorDepartamentoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getProIdProfesor());
            sb.append(SEPARATOR);
            sb.append(value.getDepIdDepartamento());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ProfesoresPorDepartamento) {
                ProfesoresPorDepartamento o = (ProfesoresPorDepartamento) object;
                return getStringKey(o.getProfesoresPorDepartamentoPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ProfesoresPorDepartamento.class.getName());
            }
        }
    }
}
