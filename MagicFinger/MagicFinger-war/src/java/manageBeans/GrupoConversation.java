/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entity.Grupos;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author lol
 */
@Named(value = "grupoConversation")
@ConversationScoped
public class GrupoConversation extends AbstractConversation implements Serializable{
@Inject
GruposMB grupoConversation;
private Grupos gruposelecionado;
    public GrupoConversation() {
        
    }

    public Grupos getGruposelecionado() {
        return gruposelecionado;
    }

    public void setGruposelecionado(Grupos gruposelecionado) {
        this.gruposelecionado = gruposelecionado;
    }
    
}
