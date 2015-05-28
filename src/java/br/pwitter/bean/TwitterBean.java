package br.pwitter.bean;

import br.pwitter.dao.PesquisaDao;
import br.pwitter.dao.PessoaDao;
import br.pwitter.model.Pessoa;
import br.pwitter.model.Pesquisa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import twitter4j.Logger;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@ManagedBean(name = "pwitter")
public class TwitterBean implements Serializable {
    
    private String twitt;
    private String twittsResult;
    private UIInput uiMsg;
    private UIInput uiResultMsg;
    private static final Logger LOG = Logger.getLogger(TwitterBean.class);
    
    public String getTwitt() {
        return twitt;
    }

    public void setTwitt(String twitt) {
        this.twitt = twitt;
    }

    public UIInput getUiMsg() {
        return uiMsg;
    }

    public void setUiMsg(UIInput uiMsg) {
        this.uiMsg = uiMsg;
    }

    public UIInput getUiResultMsg() {
        return uiResultMsg;
    }

    public void setUiResultMsg(UIInput uiResultMsg) {
        this.uiResultMsg = uiResultMsg;
    }

    public String getTwittsResult() {
        return twittsResult;
    }

    public void setTwittsResult(String twittsResult) {
        this.twittsResult = twittsResult;
    }

    public TwitterBean() {
        
    }

    public void postingToTwitter() {
        try {
            TwitterFactory TwitterFactory = new TwitterFactory();
            Twitter twitter = TwitterFactory.getSingleton();

            String message = getTwitt();
            Status status = twitter.updateStatus(message);

            String s = "\nstatus.toString() = " + status.toString()
                    + "\nstatus.getInReplyToScreenName() = " + status.getInReplyToScreenName()
                    + "\nstatus.getSource() = " + status.getSource()
                    + "\nstatus.getText() = " + status.getText()
                    + "\nstatus.getContributors() = " + Arrays.toString(status.getContributors())
                    + "\nstatus.getCreatedAt() = " + status.getCreatedAt()
                    + "\nstatus.getCurrentUserRetweetId() = " + status.getCurrentUserRetweetId()
                    + "\nstatus.getGeoLocation() = " + status.getGeoLocation()
                    + "\nstatus.getId() = " + status.getId()
                    + "\nstatus.getInReplyToStatusId() = " + status.getInReplyToStatusId()
                    + "\nstatus.getInReplyToUserId() = " + status.getInReplyToUserId()
                    + "\nstatus.getPlace() = " + status.getPlace()
                    + "\nstatus.getRetweetCount() = " + status.getRetweetCount()
                    + "\nstatus.getRetweetedStatus() = " + status.getRetweetedStatus()
                    + "\nstatus.getUser() = " + status.getUser()
                    + "\nstatus.getAccessLevel() = " + status.getAccessLevel()
                    + "\nstatus.getHashtagEntities() = " + Arrays.toString(status.getHashtagEntities())
                    + "\nstatus.getMediaEntities() = " + Arrays.toString(status.getMediaEntities())
                    + "\nstatus.getURLEntities() = " + Arrays.toString(status.getURLEntities())
                    + "\nstatus.getUserMentionEntities() = " + Arrays.toString(status.getUserMentionEntities());
            /*String s = "status.getId() = " + status.getId()
             + "\nstatus.getUser() = " + status.getUser().getName() + " - " + status.getUser().getScreenName()
             + "\nstatus.getGeoLocation() = " + status.getGeoLocation()
             + "\nstatus.getText() = " + status.getText();*/
            setTwittsResult(s);
            this.getUiMsg().setSubmittedValue("");
            this.getUiResultMsg().setSubmittedValue(getTwittsResult());
        } catch (TwitterException ex) {
            LOG.error("Erro no postingToTwitter() - " + ex.toString());
        }
    }

    private void showTimeline() {
        try {

            String saida = "";
            Twitter twitter = TwitterFactory.getSingleton();
            List<Status> statuses = twitter.getHomeTimeline();

            for (Status status : statuses) {
                saida = saida + "==================================\n";
                saida = saida + "" + status.getUser().getName() + ":" + status.getText() + "\n";
                saida = saida + "==================================\n";
            }

            setTwittsResult(saida);

        } catch (TwitterException ex) {
            //java.util.logging.Logger.getLogger(TwitterBean.class.getName()).log(Level.SEVERE, null, ex);
            LOG.error("Erro metodo showTimeline: " + ex.getMessage());
        }
    }
}
