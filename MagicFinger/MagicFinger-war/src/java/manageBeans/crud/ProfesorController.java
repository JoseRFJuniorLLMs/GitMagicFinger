package manageBeans.crud;

import entity.Departamento;
import entity.Profesor;
import entity.ProfesoresPorCurso;
import entity.ProfesoresPorDepartamento;
import entity.Universidad;
import entity.User;
import entity.Userrol;
import manageBeans.crud.util.JsfUtil;
import manageBeans.crud.util.PaginationHelper;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import manageBeans.LoginSessionMB;
import sessionBeans.DepartamentoFacadeLocal;
import sessionBeans.ProfesorFacadeLocal;
import sessionBeans.ProfesoresPorDepartamentoFacadeLocal;

@Named("profesorController")
@RequestScoped
public class ProfesorController implements Serializable {
    @EJB
    private DepartamentoFacadeLocal departamentoFacade;
    @EJB
    private ProfesoresPorDepartamentoFacadeLocal profesoresPorDepartamentoFacade;
    @Inject UserController usercontroller;
    private Profesor current;
    private DataModel items = null;
    @EJB
    private ProfesorFacadeLocal ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private List<Departamento> listDepartamento = new ArrayList<>();
    private List<String> listDepartamentoSelecionados = new ArrayList<>();
    @Inject
    LoginSessionMB session;
    @Inject
    ProfesoresPorDepartamentoController profesoresPorDepa;
    
    public ProfesorController() {
        
    }

    @PostConstruct
    public void init(){
        listDepartamento = departamentoFacade.BuscarPorIdUniversidad(session.getIdUniversidad());
    }
   
    public Profesor getSelected() {
        if (current == null) {
            current = new Profesor();
            selectedItemIndex = -1;
        }
        return current;
    }

    
    private ProfesorFacadeLocal getFacade() {
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

    public String prepareView(Profesor vari) {
        current = vari;
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Profesor();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            User user = new User();
            Userrol userrol = new Userrol("Profesor");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            user.setUsuario(current.getRut());
            user.setPassword(current.getApellidop());
            user.setUserrolName(userrol);
            usercontroller.setCurrent(user);
            if(usercontroller.create()!=null){
            getFacade().create(current);
            //EDITANDO
            Profesor profesor = getFacade().findAll().get(getFacade().count()-1);
            ProfesoresPorDepartamento prof = new ProfesoresPorDepartamento();
            prof.setProfesoresPorDepartamentoPK(new entity.ProfesoresPorDepartamentoPK());
            prof.setProfesor(profesor);
            for (String object : listDepartamentoSelecionados) {
                prof.setDepartamento(departamentoFacade.find(Integer.parseInt(object)));
                profesoresPorDepa.setCurrent(prof);
                profesoresPorDepa.create();
                System.out.println("Departamento: "+object);
            }
            User user2 = usercontroller.getFacade().findAll().get(usercontroller.getFacade().count()-1);
            profesor.setUseId(user2);
            user2.setProIdProfesor(profesor);
            getFacade().edit(profesor);
            usercontroller.getFacade().edit(user2);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profesor creado", "Se ha creado una Profesor correctamente"));
            return prepareList();
            
            }else{
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: El profesor ya existe en los registros",null ));
                return null;
            }
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Profesor no creado", "Lo sentimos, intentelo mas tarde"));
            return null;
        }
    }

    public String prepareEdit(Profesor var) {
        current = var;
      //  selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profesor actualizado", "Se ha actualizado correctamente"));
            return "View";
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Profesor no actualizado", "Lo sentimos, intentelo mas tarde"));

            return null;
        }
    }

    public String destroy(Profesor valor) {
        current = valor;
      //  selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
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
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profesor eliminado", "Se ha eliminado una Profesor"));
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Profesor no eliminado", "Lo sentimos, intentelo mas tarde"));
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
            List<ProfesoresPorDepartamento> profePorDepa = profesoresPorDepartamentoFacade.BuscarPorIdUniversidad(session.getIdUniversidad());
            List lista = new ArrayList();
            for (ProfesoresPorDepartamento object : profePorDepa) {
                if(object.getProfesor()!=null && !lista.contains(object.getProfesor())){
                    lista.add(object.getProfesor()); 
                }
            }
            items = new ListDataModel(lista);
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

    public Profesor getProfesor(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Profesor.class)
    public static class ProfesorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProfesorController controller = (ProfesorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "profesorController");
            return controller.getProfesor(getKey(value));
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
            if (object instanceof Profesor) {
                Profesor o = (Profesor) object;
                return getStringKey(o.getIdProfesor());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Profesor.class.getName());
            }
        }
    }

    public List<Departamento> getListDepartamento() {
        return listDepartamento;
    }

    public List<String> getListDepartamentoSelecionados() {
        return listDepartamentoSelecionados;
    }

    public void setListDepartamentoSelecionados(List<String> listDepartamentoSelecionados) {
        this.listDepartamentoSelecionados = listDepartamentoSelecionados;
    }
    
    
    
}
