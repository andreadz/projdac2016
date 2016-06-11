package models;

import java.io.Serializable;
import java.util.Date;

public class Conta implements Serializable {

    private int id;
    private Cliente cliente;
    private String numConta;
    private String numAgencia;
    private double saldo;
    private double limite;
    private String tipoConta;
    public Boolean statusConta;

    public Conta(int id, Cliente cliente, String numConta, String numAgencia, double saldo, double limite, String tipoConta, Boolean statusConta) {
        this.id = id;
        this.cliente = cliente;
        this.numConta = numConta;
        this.numAgencia = numAgencia;
        this.saldo = saldo;
        this.limite = limite;
        this.tipoConta = tipoConta;
        this.statusConta = statusConta;
    }

    public Conta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumConta() {
        return numConta;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    public String getNumAgencia() {
        return numAgencia;
    }

    public void setNumAgencia(String numAgencia) {
        this.numAgencia = numAgencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Boolean getStatusConta() {
        return statusConta;
    }

    public void setStatusConta(Boolean statusConta) {
        this.statusConta = statusConta;
    }
}
