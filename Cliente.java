
package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable{
    
    private int id;
    private ArrayList<Conta> contas;
    private String nome;
    private String cpf;
    private String cnpj;
    private String rg;
    private String endereco;
    private String cep;
    private String telefone;
    private String email;
    private String senha;
    private double renda;

    public Cliente(int id, String cpf, String cnpj, String nome, String rg, String endereco, String cep, 
            String telefone, String email, String senha, double renda) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.rg = rg;
        this.endereco = endereco;
        this.cep = cep;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.renda = renda;
    }
    public Cliente(){}
    

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }    

    public void setContas(ArrayList<Conta> contas) {
        this.contas = contas;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }  
    
    
}
