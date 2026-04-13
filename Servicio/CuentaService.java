package servicio;

import model.CuentaCorriente;
import java.util.List;

public interface CuentaService {

    void agregarCuenta(CuentaCorriente cuenta);

    CuentaCorriente buscarCuenta(String numero);

    boolean eliminarCuenta(String numero);

    List<CuentaCorriente> listarCuentas();

    void depositar(String numero, double monto);
}
