package manageBeans.crud;

import entity.AlumnosDelCurso;
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
import sessionBeans.AlumnosDelCursoFacadeLocal;

@Named("alumnosDelCursoController")
@SessionScoped
public class AlumnosDelCursoController implements Serializable {

    private AlumnosDelCurso current;
    private DataModel items = null;
    @EJB
    private AlumnosDelCursoFacadeLocal ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public AlumnosDelCursoController() {
    }

    public AlumnosDelCurso getSelected() {
        if (current == null) {
            current = new AlumnosDelCurso();
            current.setAlumnosDelCursoPK(new entity.AlumnosDelCursoPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private AlumnosDelCursoFacadeLocal getFacade() {
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

    public String prepareView(AlumnosDelCurso vari) {
        current = vari;
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new AlumnosDelCurso();
        current.setAlumnosDelCursoPK(new entity.AlumnosDelCursoPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getAlumnosDelCursoPK().setNombreMalla(current.getCurso().getCursoPK().getNombreMalla());
            current.getAlumnosDelCursoPK().setNombreFacultad(current.getCurso().getCursoPK().getNombreFacultad());
            current.getAlumnosDelCursoPK().setIdAlumno(current.getAlumno().getIdAlumno());
            current.getAlumnosDelCursoPK().setNombreCarrera(current.getCurso().getCursoPK().getNombreCarrera());
            current.getAlumnosDelCursoPK().setNombreDepartamento(current.getCurso().getCursoPK().getNombreDepartamento());
            current.getAlumnosDelCursoPK().setIdFecha(current.getCurso().getCursoPK().getIdFecha());
            current.getAlumnosDelCursoPK().setNombreAsignatura(current.getCurso().getCursoPK().getNombreAsignatura());
            current.getAlumnosDelCursoPK().setIdUniversidad(current.getCurso().getCursoPK().getIdUniversidad());
            current.getAlumnosDelCursoPK().setIdTipoAsignatura(current.getCurso().getCursoPK().getIdTipoAsignatura());
            getFacade().create(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AlumnosDelCurso creado", "Se ha creado una AlumnosDelCurso correctamente"));
            return prepareList();
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: AlumnosDelCurso no creado", "Lo sentimos, intentelo mas tarde"));
            return null;
        }
    }

    public String prepareEdit(AlumnosDelCurso var) {
        current = var;
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getAlumnosDelCursoPK().setNombreMalla(current.getCurso().getCursoPK().getNombreMalla());
            current.getAlumnosDelCursoPK().setNombreFacultad(current.getCurso().getCursoPK().getNombreFacultad());
            current.getAlumnosDelCursoPK().setIdAlumno(current.getAlumno().getIdAlumno());
            current.getAlumnosDelCursoPK().setNombreCarrera(current.getCurso().getCursoPK().getNombreCarrera());
            current.getAlumnosDelCursoPK().setNombreDepartamento(current.getCurso().getCursoPK().getNombreDepartamento());
            current.getAlumnosDelCursoPK().setIdFecha(current.getCurso().getCursoPK().getIdFecha());
            current.getAlumnosDelCursoPK().setNombreAsignatura(current.getCurso().getCursoPK().getNombreAsignatura());
            current.getAlumnosDelCursoPK().setIdUniversidad(current.getCurso().getCursoPK().getIdUniversidad());
            current.getAlumnosDelCursoPK().setIdTipoAsignatura(current.getCurso().getCursoPK().getIdTipoAsignatura());
            getFacade().edit(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AlumnosDelCurso actualizado", "Se ha actualizado correctamente"));
            return "View";
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: AlumnosDelCurso no actualizado", "Lo sentimos, intentelo mas tarde"));

            return null;
        }
    }

    public String destroy(AlumnosDelCurso valor) {
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
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AlumnosDelCurso eliminado", "Se ha eliminado una AlumnosDelCurso"));
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: AlumnosDelCurso no eliminado", "Lo sentimos, intentelo mas tarde"));
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

    public AlumnosDelCurso getAlumnosDelCurso(entity.AlumnosDelCursoPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = AlumnosDelCurso.class)
    public static class AlumnosDelCursoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AlumnosDelCursoController controller = (AlumnosDelCursoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "alumnosDelCursoController");
            return controller.getAlumnosDelCurso(getKey(value));
        }

        entity.AlumnosDelCursoPK getKey(String value) {
            entity.AlumnosDelCursoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entity.AlumnosDelCursoPK();
            key.setIdUniversidad(Integer.parseInt(values[0]));
            key.setNombreFacultad(values[1]);
            key.setNombreDepartamento(values[2]);
            key.setNombreCarrera(values[3]);
            key.setNombreMalla(values[4]);
            key.setNombreAsignatura(values[5]);
            key.setIdTipoAsignatura(Integer.parseInt(values[6]));
            key.setIdFecha(Integer.parseInt(values[7]));
            key.setIdAlumno(Integer.parseInt(values[8]));
            return key;
        }

        String getStringKey(entity.AlumnosDelCursoPK value) {
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
            sb.append(value.getIdAlumno());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof AlumnosDelCurso) {
                AlumnosDelCurso o = (AlumnosDelCurso) object;
                return getStringKey(o.getAlumnosDelCursoPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + AlumnosDelCurso.class.getName());
            }
        }
    }
}
