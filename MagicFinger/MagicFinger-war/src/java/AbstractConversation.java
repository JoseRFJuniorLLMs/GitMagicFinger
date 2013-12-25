package manageBeans.tomaAsistencia;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.enterprise.context.Conversation;
import javax.inject.Inject;

/**
 *
 * @author chevo
 */
class AbstractConversation {
    @Inject Conversation conversation;
    public void beginConversation() {
        if (conversation.isTransient()) {
            //conversation.setTimeout(5*60*60*1000);
            conversation.begin();
        }
    }
    
    public void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
    
    public Conversation getConversation() {
        return conversation;
    }
    
}
