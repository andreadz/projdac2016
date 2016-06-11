
package models;

import java.io.Serializable;
import java.util.Date;


public class ContaPj extends Conta implements Serializable {
    
    private String cnpj;

    public ContaPj() {
    }

    public ContaPj(String cnpj, int id, Cliente cliente, String numConta, String numAgencia, double saldo, 
            double limite, String tipoConta,Boolean statusConta) {
        super(id, cliente, numConta, numAgencia, saldo, limite, tipoConta, statusConta);
        this.cnpj = cnpj;
    }
    

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    
}
