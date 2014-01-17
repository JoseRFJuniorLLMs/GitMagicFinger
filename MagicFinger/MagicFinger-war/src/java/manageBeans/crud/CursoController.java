package manageBeans.crud;

import entity.Alumno;
import entity.AlumnosDelCurso;
import entity.Curso;
import entity.Profesor;
import entity.ProfesoresPorCurso;
import entity.ProfesoresPorDepartamento;
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
import sessionBeans.AlumnoFacadeLocal;
import sessionBeans.CursoFacadeLocal;
import sessionBeans.ProfesorFacadeLocal;
import sessionBeans.ProfesoresPorDepartamentoFacadeLocal;

@Named("cursoController")
@RequestScoped
public class CursoController implements Serializable {
    @EJB
    private ProfesorFacadeLocal profesorFacade;
    @EJB
    private ProfesoresPorDepartamentoFacadeLocal profesoresPorDepartamentoFacade;
    @EJB
    
    private AlumnoFacadeLocal alumnoFacade;
    private Curso current;
    private DataModel items = null;
    @EJB
    private CursoFacadeLocal ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @Inject
    LoginSessionMB session;
    private List<Alumno> listAlumno = new ArrayList<>();
    private List<String> listAlumnoSelecionados = new ArrayList<>();
    private List<Profesor> listProfesor = new ArrayList<>();
    private List<String> listProfesorSelecionados = new ArrayList<>();
    @Inject
    AlumnosDelCursoController alumnosdelcurso;
    @Inject
    ProfesoresPorCursoController profesoresporcurso;
    public CursoController() {
    }
    @PostConstruct
    public void init(){
        listAlumno = alumnoFacade.BuscarPorIdUniversidad(session.getIdUniversidad());
        List<ProfesoresPorDepartamento> profePorDepa = profesoresPorDepartamentoFacade.BuscarPorIdUniversidad(session.getIdUniversidad());
            List lista = new ArrayList();
            for (ProfesoresPorDepartamento object : profePorDepa) {
                if(object.getProfesor()!=null && !lista.contains(object.getProfesor())){
                    lista.add(object.getProfesor()); 
                }
            }
            listProfesor = lista;
    }
    public Curso getSelected() {
        if (current == null) {
            current = new Curso();
            current.setCursoPK(new entity.CursoPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private CursoFacadeLocal getFacade() {
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

    public String prepareView(Curso vari) {
        current = vari;
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Curso();
        current.setCursoPK(new entity.CursoPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getCursoPK().setTipIdTipoAsignatura(current.getTipoAsignatura().getIdTipoAsignatura());
            current.getCursoPK().setAsiIdAsignatura(current.getAsignatura().getIdAsignatura());
            current.getCursoPK().setSemIdFecha(current.getSemestre().getIdFecha());
            ejbFacade.create(current);
            AlumnosDelCurso alumnosCurso = new AlumnosDelCurso();
            alumnosCurso.setAlumnosDelCursoPK(new entity.AlumnosDelCursoPK());
            alumnosCurso.setCurso(current);
            ProfesoresPorCurso profesorCurso = new ProfesoresPorCurso();
            profesorCurso.setProfesoresPorCursoPK(new entity.ProfesoresPorCursoPK());
            profesorCurso.setCurso(current);
            for (String object : listAlumnoSelecionados) {
                alumnosCurso.setAlumno(alumnoFacade.find(Integer.parseInt(object)));
                alumnosdelcurso.setCurrent(alumnosCurso);
                alumnosdelcurso.create();
            }
            for (String object : listProfesorSelecionados) {
                profesorCurso.setProfesor(profesorFacade.find(Integer.parseInt(object)));
                profesoresporcurso.setCurrent(profesorCurso);
                profesoresporcurso.create();
            }
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Curso creado", "Se ha creado una Curso correctamente"));
            return prepareList();
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Curso no creado", "Lo sentimos, los datos que ingresó ya existen"));
            return null;
        }
    }

    public String prepareEdit(Curso var) {
        current = var;
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getCursoPK().setTipIdTipoAsignatura(current.getTipoAsignatura().getIdTipoAsignatura());
            current.getCursoPK().setAsiIdAsignatura(current.getAsignatura().getIdAsignatura());
            current.getCursoPK().setSemIdFecha(current.getSemestre().getIdFecha());
            getFacade().edit(current);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Curso actualizado", "Se ha actualizado correctamente"));
            return "View";
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Curso no actualizado", "Lo sentimos, los datos que ingresó ya existen"));
            return null;
        }
    }

    public String destroy(Curso valor) {
        current = valor;
        //selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
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
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Curso eliminado", "Se ha eliminado una Curso"));
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Curso no eliminado", "Lo sentimos, intentelo mas tarde"));
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
            items = new ListDataModel(ejbFacade.BuscarPorIdUniversidad(session.getIdUniversidad()));
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
        return JsfUtil.getSelectItems(ejbFacade.BuscarPorIdUniversidad(session.getIdUniversidad()), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.BuscarPorIdUniversidad(session.getIdUniversidad()), true);
    }

    public Curso getCurso(entity.CursoPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Curso.class)
    public static class CursoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CursoController controller = (CursoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cursoController");
            return controller.getCurso(getKey(value));
        }

        entity.CursoPK getKey(String value) {
            entity.CursoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entity.CursoPK();
            key.setAsiIdAsignatura(Integer.parseInt(values[0]));
            key.setTipIdTipoAsignatura(Integer.parseInt(values[1]));
            key.setSemIdFecha(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(entity.CursoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getAsiIdAsignatura());
            sb.append(SEPARATOR);
            sb.append(value.getTipIdTipoAsignatura());
            sb.append(SEPARATOR);
            sb.append(value.getSemIdFecha());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Curso) {
                Curso o = (Curso) object;
                return getStringKey(o.getCursoPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Curso.class.getName());
            }
        }
    }

    public List<Alumno> getListAlumno() {
        return listAlumno;
    }

    public void setListAlumno(List<Alumno> listAlumno) {
        this.listAlumno = listAlumno;
    }

    public List<String> getListAlumnoSelecionados() {
        return listAlumnoSelecionados;
    }

    public void setListAlumnoSelecionados(List<String> listAlumnoSelecionados) {
        this.listAlumnoSelecionados = listAlumnoSelecionados;
    }

    public List<Profesor> getListProfesor() {
        return listProfesor;
    }

    public void setListProfesor(List<Profesor> listProfesor) {
        this.listProfesor = listProfesor;
    }

    public List<String> getListProfesorSelecionados() {
        return listProfesorSelecionados;
    }

    public void setListProfesorSelecionados(List<String> listProfesorSelecionados) {
        this.listProfesorSelecionados = listProfesorSelecionados;
    }
    
    
}
