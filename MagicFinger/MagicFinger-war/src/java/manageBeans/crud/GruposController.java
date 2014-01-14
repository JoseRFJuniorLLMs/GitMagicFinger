package manageBeans.crud;

import entity.AlumnosDelCurso;
import entity.Grupos;
import java.io.IOException;
import manageBeans.crud.util.JsfUtil;
import manageBeans.crud.util.PaginationHelper;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import manageBeans.GrupoConversation;
import manageBeans.LoginSessionMB;
import sessionBeans.GruposFacadeLocal;

@Named("gruposController")
@RequestScoped
public class GruposController implements Serializable {

    private Grupos current;
    private DataModel items = null;
    @EJB
    private GruposFacadeLocal ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @Inject
    private LoginSessionMB session;
    @Inject
    private GrupoConversation conversationGrupo;
    public GruposController() {
    }

    public Grupos getSelected() {
        if (current == null) {
            current = new Grupos();
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

    public String prepareView(Grupos vari) {
        current = vari;
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    public void redireccionar(String pagina){
        System.out.println("intenta redireccionar");
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
       try {
           System.out.println("va a redireccionar ctm");
           externalContext.redirect(externalContext.getRequestContextPath() + pagina);
           
       }
       catch (IOException e) {
           System.out.println("error redirect");
           System.out.println(e.getMessage());
       }
    }
    public void envioDatosGrupo(){
        System.out.println("antes convesation");
        conversationGrupo.beginConversation();
        System.out.println("despues conversation");
        redireccionar("/faces/profesor/grupos/grupos.xhtml?cid=".concat(this.conversationGrupo.getConversation().getId()));
        System.out.println("redirigir");
    }
    public String prepareCreate() {
        current = new Grupos();
        selectedItemIndex = -1;
        return "Create";
    }

    public void create() {
        try {
            current.setCurso(session.getCurso());
            current.setAlumnosDelCursoList(new ArrayList<AlumnosDelCurso>());
            getFacade().create(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupos creado", "Se ha creado una Grupos correctamente"));
            System.out.println("antes envio");
            envioDatosGrupo();
            System.out.println("despues de envio");
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Grupos no creado", "Lo sentimos, inténtelo más tarde"));
        }
    }

    public String prepareEdit(Grupos var) {
        current = var;
       // selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public void update() {
        try {
            getFacade().edit(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupos actualizado", "Se ha actualizado correctamente"));
            envioDatosGrupo();
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Grupos no actualizado", "Lo sentimos, intentelo mas tarde"));

        }
    }

    public void destroy(Grupos valor) {
        current = valor;
       // selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        envioDatosGrupo();
    }

    public void destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            envioDatosGrupo();
        } else {
            // all items were removed - go back to list
            recreateModel();
            envioDatosGrupo();
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

    public Grupos getGrupos(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Grupos.class)
    public static class GruposControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GruposController controller = (GruposController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "gruposController");
            return controller.getGrupos(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Grupos) {
                Grupos o = (Grupos) object;
                return getStringKey(o.getIdGrupo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Grupos.class.getName());
            }
        }
    }
}
