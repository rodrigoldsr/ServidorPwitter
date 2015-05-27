package br.pwitter.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Rodrigo
 */
@Entity
@Table(name = "pesquisa")
@XmlRootElement
@XmlType(propOrder = {"usuario", "dataHoraPost", "localizacao", "msgPost", "data_atual"})
public class Pesquisa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private String dataHoraPost;
    private String localizacao;
    private String msgPost;
    private String dataAtual;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDataHoraPost() {
        return dataHoraPost;
    }

    public void setDataHoraPost(String dataHoraPost) {
        this.dataHoraPost = dataHoraPost;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getMsgPost() {
        return msgPost;
    }

    public void setMsgPost(String msgPost) {
        this.msgPost = msgPost;
    }

    public String getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(String dataAtual) {
        this.dataAtual = dataAtual;
    }
    
    /**
     * Construtor do Model
     */
    public Pesquisa() {
        
    }

}
