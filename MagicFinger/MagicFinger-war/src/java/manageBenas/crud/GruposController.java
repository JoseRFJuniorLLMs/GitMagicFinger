package manageBenas.crud;

import entity.Grupos;
import manageBenas.crud.util.JsfUtil;
import manageBenas.crud.util.PaginationHelper;

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
import sessionBeans.GruposFacadeLocal;

@Named("gruposController")
@SessionScoped
public class GruposController implements Serializable {

    private Grupos current;
    private DataModel items = null;
    @EJB
    private GruposFacadeLocal ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public GruposController() {
    }

    public Grupos getSelected() {
        if (current == null) {
            current = new Grupos();
            current.setGruposPK(new entity.GruposPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private GruposFacadeLocal getFacade() {
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

    public String prepareView() {
        current = (Grupos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Grupos();
        current.setGruposPK(new entity.GruposPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getGruposPK().setAlumnosDelCursoId2(current.getAlumnosDelCurso().getAlumnosDelCursoPK().getCursoId2());
            current.getGruposPK().setCursoId2(current.getCurso().getCursoPK().getAsignaturaId());
            current.getGruposPK().setAlumnosDelCursoId(current.getAlumnosDelCurso().getAlumnosDelCursoPK().getAlumnoId());
            current.getGruposPK().setAlumnosDelCursoId3(current.getAlumnosDelCurso().getAlumnosDelCursoPK().getCursoId());
            current.getGruposPK().setCursoId(current.getCurso().getCursoPK().getTipoAsignaturaId());
            getFacade().create(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupos creado", "Se ha creado una Grupos correctamente"));
            return prepareList();
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Grupos no creado", "Lo sentimos, intentelo mas tarde"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Grupos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getGruposPK().setAlumnosDelCursoId2(current.getAlumnosDelCurso().getAlumnosDelCursoPK().getCursoId2());
            current.getGruposPK().setCursoId2(current.getCurso().getCursoPK().getAsignaturaId());
            current.getGruposPK().setAlumnosDelCursoId(current.getAlumnosDelCurso().getAlumnosDelCursoPK().getAlumnoId());
            current.getGruposPK().setAlumnosDelCursoId3(current.getAlumnosDelCurso().getAlumnosDelCursoPK().getCursoId());
            current.getGruposPK().setCursoId(current.getCurso().getCursoPK().getTipoAsignaturaId());
            getFacade().edit(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupos actualizado", "Se ha actualizado correctamente"));
            return "View";
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Grupos no actualizado", "Lo sentimos, intentelo mas tarde"));

            return null;
        }
    }

    public String destroy() {
        current = (Grupos) getItems().getRowData();
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
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupos eliminado", "Se ha eliminado una Grupos"));
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Grupos no eliminado", "Lo sentimos, intentelo mas tarde"));
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

    public Grupos getGrupos(entity.GruposPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Grupos.class)
    public static class GruposControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GruposController controller = (GruposController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "gruposController");
            return controller.getGrupos(getKey(value));
        }

        entity.GruposPK getKey(String value) {
            entity.GruposPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entity.GruposPK();
            key.setCursoId2(Integer.parseInt(values[0]));
            key.setCursoId(Integer.parseInt(values[1]));
            key.setAlumnosDelCursoId2(Integer.parseInt(values[2]));
            key.setAlumnosDelCursoId3(Integer.parseInt(values[3]));
            key.setAlumnosDelCursoId(Integer.parseInt(values[4]));
            return key;
        }

        String getStringKey(entity.GruposPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getCursoId2());
            sb.append(SEPARATOR);
            sb.append(value.getCursoId());
            sb.append(SEPARATOR);
            sb.append(value.getAlumnosDelCursoId2());
            sb.append(SEPARATOR);
            sb.append(value.getAlumnosDelCursoId3());
            sb.append(SEPARATOR);
            sb.append(value.getAlumnosDelCursoId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Grupos) {
                Grupos o = (Grupos) object;
                return getStringKey(o.getGruposPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Grupos.class.getName());
            }
        }
    }
}
