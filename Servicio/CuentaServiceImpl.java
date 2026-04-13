package servicio;

import model.CuentaCorriente;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CuentaServiceImpl implements CuentaService {

    private List<CuentaCorriente> lista = new ArrayList<>();

    @Override
    public void agregarCuenta(CuentaCorriente cuenta) {
        lista.add(cuenta);
    }

    @Override
    public CuentaCorriente buscarCuenta(String numero) {
        for (CuentaCorriente c : lista) {
            if (c.getNumeroCuenta().equals(numero)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public boolean eliminarCuenta(String numero) {
        CuentaCorriente c = buscarCuenta(numero);
        if (c != null) {
            lista.remove(c);
            return true;
        }
        return false;
    }

    @Override
    public List<CuentaCorriente> listarCuentas() {
        return lista;
    }

    @Override
    public void depositar(String numero, double monto) {
        CuentaCorriente c = buscarCuenta(numero);
        if (c != null) {
            c.depositar(monto);
            System.out.println("Depósito realizado.");
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    //  LECTURA DE ARCHIVO
    public void cargarDesdeArchivo(String ruta) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                String titular = datos[0];
                String numero = datos[1];
                double saldo = Double.parseDouble(datos[2]);
                double descubierto = Double.parseDouble(datos[3]);

                CuentaCorriente cuenta = new CuentaCorriente(titular, numero, saldo, descubierto);
                lista.add(cuenta);
            }

            br.close();
            System.out.println("Cuentas cargadas desde archivo.");
        } catch (Exception e) {
            System.out.println("Error al leer archivo.");
        }
    }
}
