package br.pwitter.dao;

import br.pwitter.hibernate.HibernateUtil;
import br.pwitter.model.Pessoa;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import twitter4j.Logger;

public class PessoaDao {

    private Session session;
    private static final Logger LOG = Logger.getLogger(PessoaDao.class);

    public void inserir(Pessoa pessoa) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(pessoa);
            session.getTransaction().commit();
        } catch(Exception ex) {
            LOG.error("Erro PessoaDao metodo inserir: "+ex.getMessage());
        } finally {
            session.close();
        }
    }

    public void alterar(Pessoa pessoa) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(pessoa);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public void excluir(Pessoa pessoa) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(pessoa);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List listar() {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria cri = session.createCriteria(Pessoa.class);
            return cri.list();
        } finally {
            session.close();
        }
    }
}
