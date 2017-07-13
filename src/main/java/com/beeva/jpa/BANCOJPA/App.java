package com.beeva.jpa.BANCOJPA;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beeva.jpa.DAO.BancoDAO;
import com.beeva.jpa.DAO.BancoclienteDAO;
import com.beeva.jpa.DAO.ClienteDAO;
import com.beeva.jpa.DAO.CuentaDAO;
import com.beeva.jpa.DAO.TipoCuentaDAO;
import com.beeva.jpa.implementacion.BancoDAOImpl;
import com.beeva.jpa.implementacion.BancoclienteDAOImpl;
import com.beeva.jpa.implementacion.ClienteDAOImpl;
import com.beeva.jpa.implementacion.CuentaDAOImpl;
import com.beeva.jpa.implementacion.TipoCuentaDAOImpl;
import com.beeva.jpa.models.Banco;
import com.beeva.jpa.models.Bancocliente;
import com.beeva.jpa.models.Cliente;
import com.beeva.jpa.models.Cuenta;
import com.beeva.jpa.models.Tipocuenta;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Calendar fecha = Calendar.getInstance();
    	
    	// ================ LLAMADA A CONTEXT Y CLASE ====
    	ApplicationContext context = new ClassPathXmlApplicationContext("core-context.xml");
    	BancoDAO banco_dao = (BancoDAO)context.getBean(BancoDAOImpl.class);
    	ClienteDAO cliente_dao = (ClienteDAO)context.getBean(ClienteDAOImpl.class);
    	BancoclienteDAO banco_cliente = (BancoclienteDAO)context.getBean(BancoclienteDAOImpl.class);
    	CuentaDAO cuenta_dao = (CuentaDAO)context.getBean(CuentaDAOImpl.class);
    	TipoCuentaDAO tipo_cuenta_dao = context.getBean(TipoCuentaDAOImpl.class);
    	
        System.out.println( "Ejecutando Proyecto Banco JPA......!" );
        
        // ================= BANCO =======================
        Banco banco1 = new Banco();
        banco1.setNombre("BBBVA Bancomer");
        Banco banco_recuperado = banco_dao.saveBanco(banco1);
        
        Banco banco2 = new Banco();
        banco2.setNombre("Banamex");
        Banco banco_recuperado2 = banco_dao.saveBanco(banco2);
        
        // ================= CLIENTES ====================
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Oscar");
        cliente1.setApellido("Almazan");
        Cliente cliente_recuperado = cliente_dao.saveCliente(cliente1);
        
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Sandra");
        cliente2.setApellido("Argueta");
        Cliente cliente_recuperado2 = cliente_dao.saveCliente(cliente2);
        
        Cliente cliente3 = new Cliente();
        cliente3.setNombre("Arturo");
        cliente3.setApellido("Hernandez");
        Cliente cliente_recuperado3 = cliente_dao.saveCliente(cliente3);
        
        // ================= BANCO CLIENTES ==============
        Bancocliente ban_cli = new Bancocliente();
        ban_cli.setCliente(cliente_recuperado);
        ban_cli.setBanco(banco_recuperado);
        banco_cliente.saveBancocliente(ban_cli);
        
        Bancocliente ban_cli2 = new Bancocliente();
        ban_cli2.setCliente(cliente_recuperado2);
        ban_cli2.setBanco(banco_recuperado2);
        banco_cliente.saveBancocliente(ban_cli2);
        
        Bancocliente ban_cli3 = new Bancocliente();
        ban_cli3.setCliente(cliente_recuperado3);
        ban_cli3.setBanco(banco_recuperado2);
        banco_cliente.saveBancocliente(ban_cli3);
        
        // ================ TIPO CUENTA ==================
        Tipocuenta tipo_cuenta_ahorro = new Tipocuenta();
        tipo_cuenta_ahorro.setNombre("Ahorro");
        tipo_cuenta_dao.saveTipoCuenta(tipo_cuenta_ahorro);
        
        Tipocuenta tipo_cuenta_cheques = new Tipocuenta();
        tipo_cuenta_cheques.setNombre("Cheques");
        tipo_cuenta_dao.saveTipoCuenta(tipo_cuenta_cheques);
              
        // ================ CUENTAS ======================
        Cuenta cuenta1 = new Cuenta();
        cuenta1.setBalance(0.0);
        cuenta1.setCliente(cliente_recuperado);
        cuenta1.setTipocuenta(tipo_cuenta_ahorro);
        
        Cuenta cuenta2 = new Cuenta();
        cuenta2.setBalance(0.0);
        cuenta2.setCliente(cliente_recuperado2);
        cuenta2.setTipocuenta(tipo_cuenta_cheques);
        
        cuenta_dao.saveCuenta(cuenta1);
        cuenta_dao.saveCuenta(cuenta2);
              
        // =============== OPERACIONES CLIENTE 1 ===================
       
        List<Cuenta> lista_cuentas_cliente1 = cuenta_dao.getCuenta(cliente_recuperado); //cliente recuperado = cliente 1
        for(Cuenta c : lista_cuentas_cliente1)
        	System.out.println(c.getCliente().getNombre() + " tiene la cuenta " + c.getTipocuenta().getNombre()
        					 + " con un balance de: " + c.getBalance() + "\n");
        
        cliente_recuperado.setCuentas(lista_cuentas_cliente1); //le asigno su lista de cuentas
        List<Cuenta> list_c = cliente_recuperado.getCuentas(); //recupero la lista de cuentas
        
        cuenta_dao.deposito(list_c.get(0), 500); //DEPOSITAR 500 AL CLIENTE 1
        cuenta_dao.retiro(fecha, list_c.get(0),100);    //RETIRO 100 AL CLIENTE 1
        cuenta_dao.deposito(list_c.get(0), 5000); 
        cuenta_dao.retiro(fecha, list_c.get(0),100);    //RETIRO 100 AL CLIENTE 1
        
        // ============== OPERACIONES CLIENTE 2 ====================
        
        List<Cuenta> lista_cuentas_cliente2 = cuenta_dao.getCuenta(cliente_recuperado2);
        for(Cuenta c : lista_cuentas_cliente2)
        	System.out.println(c.getCliente().getNombre() + " tiene la cuenta " + c.getTipocuenta().getNombre()
        					 + " con un balance de: " + c.getBalance() + "\n");
        
        cliente_recuperado2.setCuentas(lista_cuentas_cliente2); //le asigno su lista de cuentas
        List<Cuenta> list_c2 = cliente_recuperado2.getCuentas(); //recupero la lista de cuentas
        
        // Creando fecha entre semana
        fecha.set(Calendar.YEAR,2017);
        fecha.set(Calendar.MONTH, 10);
        fecha.set(Calendar.DAY_OF_WEEK, 4);
        System.out.println("Fecha: " + fecha.getTime());
        cuenta_dao.retiro(fecha, list_c2.get(0), 100);
        
        // Cambio a fecha de domingo
        fecha.set(Calendar.YEAR,2017);
        fecha.set(Calendar.MONTH, 10);
        fecha.set(Calendar.DAY_OF_WEEK, 7);
        System.out.println("Fecha: " + fecha.getTime());
        cuenta_dao.retiro(fecha, list_c2.get(0), 100); 
          
        
        // ========================================================
    }
}
