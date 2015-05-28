package br.pwitter.dao;

import br.pwitter.hibernate.HibernateUtil;
import org.hibernate.Session;
import br.pwitter.model.Pesquisa;
import java.util.List;
import org.hibernate.Criteria;
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
        LOG.info("[PesquisaDao/openSession] Sessão aberta");
    }
    
    private void closeSession() {
        getSession().close();
        LOG.info("[PesquisaDao/closeSession] Sessão fechada");
    }
    
    public boolean inserir(Pesquisa pesquisa) {
        boolean retorno = false;
        try {
            openSession();
            getSession().beginTransaction();
            getSession().save(pesquisa);
            getSession().getTransaction().commit();
            retorno = true;
            LOG.info("[PesquisaDao/inserir] Objeto pesquisa inserido com sucesso!");
        } catch(Exception ex) {
            LOG.error("[PesquisaDao/inserir] Erro: "+ex.getMessage());
        } finally {
            closeSession();
        }
        return retorno;
    }
    
    public List listar() {
        List retorno = null;
        try {
            openSession();
            Criteria cri = getSession().createCriteria(Pesquisa.class);
            retorno = cri.list();
            LOG.info("[PesquisaDao/listar] Listagem do BD feita com sucesso!");
        } catch(Exception ex) {
            LOG.error("[PesquisaDao/listar] Erro: "+ex.getMessage());
        } finally {
            closeSession();
        }
        return retorno;
    }
    
}
