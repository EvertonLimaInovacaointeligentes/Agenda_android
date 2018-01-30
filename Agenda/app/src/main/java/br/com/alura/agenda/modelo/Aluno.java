package br.com.alura.agenda.modelo;

import java.io.Serializable;

/**
 * Created by edwin on 24/07/2017.
 */

public class Aluno implements Serializable
{
    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String serie;
    private Double notas;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Double getNotas() {
        return notas;
    }

    public void setNotas(Double notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return getId().toString()+" - "+getNome();
    }
}
