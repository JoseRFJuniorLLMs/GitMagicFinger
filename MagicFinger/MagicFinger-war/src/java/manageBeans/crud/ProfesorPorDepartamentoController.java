package manageBeans.crud;

import entity.ProfesorPorDepartamento;
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
import sessionBeans.ProfesorPorDepartamentoFacadeLocal;

@Named("profesorPorDepartamentoController")
@SessionScoped
public class ProfesorPorDepartamentoController implements Serializable {

    private ProfesorPorDepartamento current;
    private DataModel items = null;
    @EJB
    private ProfesorPorDepartamentoFacadeLocal ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ProfesorPorDepartamentoController() {
    }

    public ProfesorPorDepartamento getSelected() {
        if (current == null) {
            current = new ProfesorPorDepartamento();
            current.setProfesorPorDepartamentoPK(new entity.ProfesorPorDepartamentoPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private ProfesorPorDepartamentoFacadeLocal getFacade() {
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

    public String prepareView(ProfesorPorDepartamento vari) {
        current = vari;
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ProfesorPorDepartamento();
        current.setProfesorPorDepartamentoPK(new entity.ProfesorPorDepartamentoPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getProfesorPorDepartamentoPK().setIdUniversidad(current.getDepartamento().getDepartamentoPK().getIdUniversidad());
            current.getProfesorPorDepartamentoPK().setIdProfesor(current.getProfesor().getIdProfesor());
            current.getProfesorPorDepartamentoPK().setNombreFacultad(current.getDepartamento().getDepartamentoPK().getNombreFacultad());
            current.getProfesorPorDepartamentoPK().setNombreDepartamento(current.getDepartamento().getDepartamentoPK().getNombreDepartamento());
            getFacade().create(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ProfesorPorDepartamento creado", "Se ha creado una ProfesorPorDepartamento correctamente"));
            return prepareList();
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ProfesorPorDepartamento no creado", "Lo sentimos, intentelo mas tarde"));
            return null;
        }
    }

    public String prepareEdit(ProfesorPorDepartamento var) {
        current = var;
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getProfesorPorDepartamentoPK().setIdUniversidad(current.getDepartamento().getDepartamentoPK().getIdUniversidad());
            current.getProfesorPorDepartamentoPK().setIdProfesor(current.getProfesor().getIdProfesor());
            current.getProfesorPorDepartamentoPK().setNombreFacultad(current.getDepartamento().getDepartamentoPK().getNombreFacultad());
            current.getProfesorPorDepartamentoPK().setNombreDepartamento(current.getDepartamento().getDepartamentoPK().getNombreDepartamento());
            getFacade().edit(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ProfesorPorDepartamento actualizado", "Se ha actualizado correctamente"));
            return "View";
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ProfesorPorDepartamento no actualizado", "Lo sentimos, intentelo mas tarde"));

            return null;
        }
    }

    public String destroy(ProfesorPorDepartamento valor) {
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
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ProfesorPorDepartamento eliminado", "Se ha eliminado una ProfesorPorDepartamento"));
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ProfesorPorDepartamento no eliminado", "Lo sentimos, intentelo mas tarde"));
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

    public ProfesorPorDepartamento getProfesorPorDepartamento(entity.ProfesorPorDepartamentoPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = ProfesorPorDepartamento.class)
    public static class ProfesorPorDepartamentoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProfesorPorDepartamentoController controller = (ProfesorPorDepartamentoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "profesorPorDepartamentoController");
            return controller.getProfesorPorDepartamento(getKey(value));
        }

        entity.ProfesorPorDepartamentoPK getKey(String value) {
            entity.ProfesorPorDepartamentoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entity.ProfesorPorDepartamentoPK();
            key.setIdProfesor(Integer.parseInt(values[0]));
            key.setIdUniversidad(Integer.parseInt(values[1]));
            key.setNombreFacultad(values[2]);
            key.setNombreDepartamento(values[3]);
            return key;
        }

        String getStringKey(entity.ProfesorPorDepartamentoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdProfesor());
            sb.append(SEPARATOR);
            sb.append(value.getIdUniversidad());
            sb.append(SEPARATOR);
            sb.append(value.getNombreFacultad());
            sb.append(SEPARATOR);
            sb.append(value.getNombreDepartamento());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ProfesorPorDepartamento) {
                ProfesorPorDepartamento o = (ProfesorPorDepartamento) object;
                return getStringKey(o.getProfesorPorDepartamentoPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ProfesorPorDepartamento.class.getName());
            }
        }
    }
}
