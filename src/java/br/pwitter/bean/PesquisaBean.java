package br.pwitter.bean;

import br.pwitter.dao.PesquisaDao;
import br.pwitter.model.Pesquisa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;
import twitter4j.Logger;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 *
 * @author Rodrigo
 */
@ManagedBean(name = "pesquisa")
public class PesquisaBean implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(PesquisaBean.class);
    private List pesquisaRelizada = new ArrayList();
    @Size(min=2,max=30)
    private String termoPesquisa;

    public List getPesquisaRelizada() {
        return pesquisaRelizada;
    }

    public void setPesquisaRelizada(List pesquisaRelizada) {
        this.pesquisaRelizada = pesquisaRelizada;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public PesquisaBean() {
    }
    
    public void pesquisarTwitt() {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query(getTermoPesquisa());
        try {
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                salvarPesquisa(status.getUser().getScreenName(), status.getUser().getCreatedAt().toString(), status.getUser().getLocation(), status.getText(), new Date().toString());
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pesquisa realizada com sucesso. Confira resultados agora."));
            setPesquisaRelizada(new PesquisaDao().listar());
        } catch (TwitterException ex) {
            LOG.error("[TwitterBean/pesquisaTwitt] Erro: "+ex.getMessage());
        }
    }

    public void salvarPesquisa(String usuario, String dataHoraPost, String localizacao, String msgPost, String dataAtual) {
        Pesquisa pesquisa = new Pesquisa();
        /*
        pesquisa.setUsuario("@Rodrigo");
        pesquisa.setDataHoraPost("27/05/2015 07:53");
        pesquisa.setLocalizacao("Cruzeiro");
        pesquisa.setMsgPost("Texto do post");
        pesquisa.setDataAtual("27/05/2015 09:00");
        */
        pesquisa.setUsuario(usuario);
        pesquisa.setDataHoraPost(dataHoraPost);
        pesquisa.setLocalizacao(localizacao);
        pesquisa.setMsgPost(msgPost);
        pesquisa.setDataAtual(dataAtual);
        pesquisa.setTermoPesquisado(getTermoPesquisa());
        new PesquisaDao().inserir(pesquisa);
    }
    
}
