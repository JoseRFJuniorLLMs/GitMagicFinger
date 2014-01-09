package manageBeans.crud;

import entity.Asignatura;
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
import sessionBeans.AsignaturaFacadeLocal;

@Named("asignaturaController")
@SessionScoped
public class AsignaturaController implements Serializable {

    private Asignatura current;
    private DataModel items = null;
    @EJB
    private AsignaturaFacadeLocal ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public AsignaturaController() {
    }

    public Asignatura getSelected() {
        if (current == null) {
            current = new Asignatura();
            current.setAsignaturaPK(new entity.AsignaturaPK());
            selectedItemIndex = -1;
        }
        
        
        return current;
    }

    private AsignaturaFacadeLocal getFacade() {
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

    public String prepareView(Asignatura vari) {
        current = vari;
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Asignatura();
        current.setAsignaturaPK(new entity.AsignaturaPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getAsignaturaPK().setIdUniversidad(current.getMalla().getMallaPK().getIdUniversidad());
            current.getAsignaturaPK().setNombreDepartamento(current.getMalla().getMallaPK().getNombreDepartamento());
            current.getAsignaturaPK().setNombreFacultad(current.getMalla().getMallaPK().getNombreFacultad());
            current.getAsignaturaPK().setNombreMalla(current.getMalla().getMallaPK().getNombreMalla());
            current.getAsignaturaPK().setNombreCarrera(current.getMalla().getMallaPK().getNombreCarrera());
            getFacade().create(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignatura creado", "Se ha creado una Asignatura correctamente"));
            return prepareList();
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Asignatura no creado", "Lo sentimos, intentelo mas tarde"));
            return null;
        }
    }

    public String prepareEdit(Asignatura var) {
        current = var;
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getAsignaturaPK().setIdUniversidad(current.getMalla().getMallaPK().getIdUniversidad());
            current.getAsignaturaPK().setNombreDepartamento(current.getMalla().getMallaPK().getNombreDepartamento());
            current.getAsignaturaPK().setNombreFacultad(current.getMalla().getMallaPK().getNombreFacultad());
            current.getAsignaturaPK().setNombreMalla(current.getMalla().getMallaPK().getNombreMalla());
            current.getAsignaturaPK().setNombreCarrera(current.getMalla().getMallaPK().getNombreCarrera());
            getFacade().edit(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignatura actualizado", "Se ha actualizado correctamente"));
            return "View";
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Asignatura no actualizado", "Lo sentimos, intentelo mas tarde"));

            return null;
        }
    }

    public String destroy(Asignatura valor) {
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
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignatura eliminado", "Se ha eliminado una Asignatura"));
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Asignatura no eliminado", "Lo sentimos, intentelo mas tarde"));
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
        return JsfUtil.getSelectItems(ejbFacade.BuscarPorUniversidad(1), true);
    }

    public Asignatura getAsignatura(entity.AsignaturaPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Asignatura.class)
    public static class AsignaturaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AsignaturaController controller = (AsignaturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "asignaturaController");
            return controller.getAsignatura(getKey(value));
        }

        entity.AsignaturaPK getKey(String value) {
            entity.AsignaturaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entity.AsignaturaPK();
            key.setIdUniversidad(Integer.parseInt(values[0]));
            key.setNombreFacultad(values[1]);
            key.setNombreDepartamento(values[2]);
            key.setNombreCarrera(values[3]);
            key.setNombreMalla(values[4]);
            key.setNombreAsignatura(values[5]);
            return key;
        }

        String getStringKey(entity.AsignaturaPK value) {
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
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Asignatura) {
                Asignatura o = (Asignatura) object;
                return getStringKey(o.getAsignaturaPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Asignatura.class.getName());
            }
        }
    }
}
