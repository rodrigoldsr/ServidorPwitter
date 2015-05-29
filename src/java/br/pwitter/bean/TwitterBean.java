package br.pwitter.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;
import twitter4j.Logger;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@ManagedBean(name = "pwitter")
@ViewScoped
public class TwitterBean implements Serializable {
    
    @Size(min=2,max=150)
    private String mensagem;
    private static final Logger LOG = Logger.getLogger(TwitterBean.class);
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public TwitterBean() {
    }

    public void postMsg() {
        try {
            TwitterFactory TwitterFactory = new TwitterFactory();
            Twitter twitter = TwitterFactory.getSingleton();
            String message = getMensagem();
            twitter.updateStatus(message);
            LOG.info("[TwitterBean/postMsg] Mensagem enviada com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mensagem postada com sucesso!"));
        } catch (TwitterException ex) {
            LOG.error("[TwitterBean/postMsg] Erro: " + ex.getMessage());
        }
    }

    public List showTimeline() {
        List retorno = null;
        try {
            Twitter twitter = TwitterFactory.getSingleton();
            List<Status> statuses = twitter.getHomeTimeline();
            retorno = statuses;
        } catch (TwitterException ex) {
            LOG.error("[TwitterBean/showTimeline] Erro: " + ex.getMessage());
        }
        return retorno;
    }
}
