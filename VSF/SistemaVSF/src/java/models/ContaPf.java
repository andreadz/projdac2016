package models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author André
 */
public class ContaPf extends Conta implements Serializable{
    
    private String cpf;

    public ContaPf(String cpf, int id, Cliente cliente, String numConta, String numAgencia, double saldo, 
            double limite, String tipoConta, Boolean statusConta) {
        super(id, cliente, numConta, numAgencia, saldo, limite, tipoConta, statusConta);
        this.cpf = cpf;
    }

    public ContaPf() {
    }
    
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
    
}
