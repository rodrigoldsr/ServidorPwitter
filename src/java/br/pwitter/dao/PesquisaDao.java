package br.pwitter.dao;

import br.pwitter.hibernate.HibernateUtil;
import org.hibernate.Session;
import br.pwitter.model.Pesquisa;
import twitter4j.Logger;

/**
 *
 * @author Rodrigo
 */
public class PesquisaDao {
    
    private Session session;
    private static final Logger LOG = Logger.getLogger(PesquisaDao.class);

    private Session getSession() {
        return session;
    }

    private void setSession(Session session) {
        this.session = session;
    }
    
    public PesquisaDao() { }
    
    private void openSession() {
        setSession(HibernateUtil.getSessionFactory().openSession());
    }
    
    private void closeSession() {
        getSession().close();
    }
    
    public boolean inserir(Pesquisa pesquisa) {
        boolean retorno = false;
        try {
            openSession();
            getSession().beginTransaction();
            getSession().save(pesquisa);
            getSession().getTransaction().commit();
            LOG.info("[PesquisaDao/inserir] Objeto pesquisa inserido com sucesso!");
        } catch(Exception ex) {
            LOG.error("[PesquisaDao/inserir] Erro: "+ex.getMessage());
        } finally {
            closeSession();
            LOG.info("[PesquisaDao/inserir] Sessão fechada");
        }
        
        
        return retorno;
    }
    
}
