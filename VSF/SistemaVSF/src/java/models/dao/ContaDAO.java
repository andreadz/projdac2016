package models.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.*;

/**
 *
 * @author André
 */
public class ContaDAO {

    private final String stmInserirConta = "INSERT INTO contas values (?,?,?,?,?,?,?)";

    private final String stmEncerrarConta = "UPDATE contas SET statusConta = ? where agencia= ? AND conta = ?";
    private final String stmDepositar = "UPDATE contas SET saldo = ? WHERE agencia = ? AND conta=?";
    private final String stmTransferir = "UPDATE contas SET saldo = ? WHERE agencia = ? AND conta=?";
    private final String stmClienteCPF = "SELECT id, nome FROM clientes where cpf=?";
    private final String stmTransferirTerceiros = "UPDATE contas SET saldo = ? WHERE agencia = ? AND conta=? AND idCliente = (SELECT id FROM clientes where cpf=?)";

    private final String stmVerificaContaExistente = "SELECT MAX(conta) FROM where agencia = ?";
    private final String stmSaldoAtual = "SELECT saldo, limite FROM contas where agencia=? AND conta=? ";
    private final String stmExtratoCompleto = "SELECT * FROM transacoes WHERE idConta=? AND idClienteConta=? ";
    private final String stmExtratoIntervalo = "SELECT * FROM transacoes WHERE idConta=? AND idClienteConta=? AND (dataTransacao BETWEEN '?' AND Date(now()))";

    public void inserir(Cliente cliente) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmInserirConta, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setString(3, cliente.getRg());
            pstmt.setString(4, cliente.getEndereco());
            pstmt.setString(5, cliente.getCep());
            pstmt.setString(6, cliente.getTelefone());
            pstmt.setString(7, cliente.getEmail());
            pstmt.setDouble(8, cliente.getRenda());
            pstmt.setString(9, cliente.getSenha());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            int i = rs.getInt(1);
            cliente.setId(i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            try {
                conexao.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        }
    }

    public void saldoAtual(Conta conta) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmSaldoAtual, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, conta.getNumAgencia());
            pstmt.setString(2, conta.getNumConta());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                conta.setSaldo(rs.getDouble("saldo"));
                conta.setLimite(rs.getDouble("limite"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            try {
                conexao.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        }
    }

    public Conta criarConta(String agencia, Cliente cliente, double limite) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        int valorconta = 0;
        Conta conta;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmVerificaContaExistente, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, agencia);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                valorconta = Integer.parseInt(rs.getString("conta"));
            }
            else{
                return null;
            }
            pstmt.clearParameters();
            valorconta++;
//            String padded = ;
            String numConta = String.format("%06d", valorconta);
            if (cliente.getCpf().isEmpty()) {
                conta = new ContaPj();
                conta.setTipoConta("J");
            } else {
                conta = new ContaPf();
                conta.setTipoConta("F");
            }
            conta.setNumConta(numConta);
            conta.setNumAgencia(agencia);
            conta.setSaldo(0.00);
            conta.setLimite(limite);
            conta.setStatusConta(true);
            conta.setCliente(cliente);

            pstmt = conexao.prepareStatement(stmInserirConta, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, conta.getNumAgencia());
            pstmt.setString(2, conta.getNumConta());
            pstmt.setDouble(3, conta.getSaldo());
            pstmt.setDouble(4, conta.getLimite());
            pstmt.setBoolean(5, conta.getStatusConta());
            pstmt.setString(6, conta.getTipoConta());
            pstmt.setInt(7, cliente.getId());
            pstmt.execute();

            rs = pstmt.getGeneratedKeys();
            rs.next();
            int i = rs.getInt(1);
            conta.setId(i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            try {
                conexao.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        }
        return conta;
    }

    public void encerrarConta(Conta conta) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmEncerrarConta, Statement.RETURN_GENERATED_KEYS);
            pstmt.setBoolean(1, conta.getStatusConta());
            pstmt.setString(2, conta.getNumAgencia());
            pstmt.setString(3, conta.getNumConta());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            try {
                conexao.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        }
    }

    public void depositar(Conta contaRetirada, Conta contaDeposito) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmDepositar, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, contaRetirada.getSaldo());
            pstmt.setString(2, contaRetirada.getNumAgencia());
            pstmt.setString(3, contaRetirada.getNumConta());
            pstmt.executeUpdate();
            pstmt.setDouble(1, contaDeposito.getSaldo());
            pstmt.setString(2, contaDeposito.getNumAgencia());
            pstmt.setString(3, contaDeposito.getNumConta());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            try {
                conexao.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        }
    }

    //do próprio cliente
    public void transferir(Conta contaRetirada, Conta contaDeposito) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmTransferir, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, contaRetirada.getSaldo());
            pstmt.setString(2, contaRetirada.getNumAgencia());
            pstmt.setString(3, contaRetirada.getNumConta());
            pstmt.executeUpdate();
            pstmt.setDouble(1, contaDeposito.getSaldo());
            pstmt.setString(2, contaDeposito.getNumAgencia());
            pstmt.setString(3, contaDeposito.getNumConta());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            try {
                conexao.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        }
    }

    public void selecionaClienteCPF(String cpf) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmClienteCPF, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();
            Cliente cliente = new Cliente();
            while (rs.next()) {
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            try {
                conexao.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        }
    }

    //para terceiros
    public void transferir(Cliente cliente, Conta contaRetirada, Conta contaDeposito) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmTransferirTerceiros, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, contaRetirada.getSaldo());
            pstmt.setString(2, contaRetirada.getNumAgencia());
            pstmt.setString(3, contaRetirada.getNumConta());
            //pstmt.setInt(4, contaRetirada.getCliente());           
            pstmt.executeUpdate();
            pstmt.setDouble(1, contaDeposito.getSaldo());
            pstmt.setString(2, contaDeposito.getNumAgencia());
            pstmt.setString(3, contaDeposito.getNumConta());
            // pstmt.setInt(4, contaDeposito.getCliente());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            try {
                conexao.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        }
    }

    public ArrayList<Transacoes> extratoCompleto(Conta conta) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ArrayList<Transacoes> trans = new ArrayList<Transacoes>();
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmExtratoCompleto, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, conta.getId());
            // pstmt.setInt(2, conta.getCliente());  
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Transacoes transacao = new Transacoes();
                transacao.setTipoTransacao(rs.getInt("tipoTransacao"));
                transacao.setValor(rs.getDouble("valor"));
                transacao.setDataTransacao(rs.getDate("dataTransacao"));
                transacao.setIdConta1(rs.getInt("idConta"));
                transacao.setIdCliente1(rs.getInt("idClienteConta"));
                transacao.setIdConta2(rs.getInt("idConta2"));
                transacao.setIdCliente2(rs.getInt("idClienteConta2"));

                trans.add(transacao);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            try {
                conexao.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        }
        return trans;
    }

    public ArrayList<Transacoes> extratoIntervalo(Conta conta, Date dataTrans) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ArrayList<Transacoes> trans = new ArrayList<Transacoes>();
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmExtratoCompleto, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, conta.getId());
            // pstmt.setInt(2, conta.getCliente());  
            pstmt.setDate(3, dataTrans);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Transacoes transacao = new Transacoes();
                transacao.setTipoTransacao(rs.getInt("tipoTransacao"));
                transacao.setValor(rs.getDouble("valor"));
                transacao.setDataTransacao(rs.getDate("dataTransacao"));
                transacao.setIdConta1(rs.getInt("idConta"));
                transacao.setIdCliente1(rs.getInt("idClienteConta"));
                transacao.setIdConta2(rs.getInt("idConta2"));
                transacao.setIdCliente2(rs.getInt("idClienteConta2"));

                trans.add(transacao);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            try {
                conexao.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        }
        return trans;
    }

}
