/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author André
 */

public class Transacoes {
    private int id;
    private int tipoTransacao;
    private double valor;
    private Date dataTransacao;       
    private int idCliente1;
    private int idCliente2;
    private int idConta1;
    private int idConta2;

    public int getIdCliente1() {
        return idCliente1;
    }

    public void setIdCliente1(int idCliente1) {
        this.idCliente1 = idCliente1;
    }

    public int getIdCliente2() {
        return idCliente2;
    }

    public void setIdCliente2(int idCliente2) {
        this.idCliente2 = idCliente2;
    }

    public int getIdConta1() {
        return idConta1;
    }

    public void setIdConta1(int idConta1) {
        this.idConta1 = idConta1;
    }

    public int getIdConta2() {
        return idConta2;
    }

    public void setIdConta2(int idConta2) {
        this.idConta2 = idConta2;
    }
    
    public Transacoes() {
    }   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(int tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    
    
}
