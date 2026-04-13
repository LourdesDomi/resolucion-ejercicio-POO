package inicio;

import model.CuentaCorriente;
import servicio.CuentaServiceImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CuentaServiceImpl service = new CuentaServiceImpl();

        //  CARGA DESDE ARCHIVO
        service.cargarDesdeArchivo("cuentas.txt");

        int opcion;

        do {
            System.out.println("\n===== SISTEMA BANCARIO =====");
            System.out.println("1. Agregar cuenta");
            System.out.println("2. Buscar cuenta");
            System.out.println("3. Eliminar cuenta");
            System.out.println("4. Listar cuentas");
            System.out.println("5. Depositar");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Titular: ");
                    String titular = scanner.nextLine();

                    System.out.print("Número de cuenta: ");
                    String numero = scanner.nextLine();

                    System.out.print("Saldo inicial: ");
                    double saldo = scanner.nextDouble();

                    System.out.print("Descubierto: ");
                    double descubierto = scanner.nextDouble();

                    CuentaCorriente nueva = new CuentaCorriente(titular, numero, saldo, descubierto);
                    service.agregarCuenta(nueva);
                    break;

                case 2:
                    System.out.print("Número: ");
                    numero = scanner.nextLine();

                    CuentaCorriente c = service.buscarCuenta(numero);
                    if (c != null) {
                        System.out.println("Titular: " + c.getTitular());
                        System.out.println("Saldo: " + c.getSaldo());
                        System.out.println("Descubierto: " + c.getDescubierto());
                    } else {
                        System.out.println("Cuenta no encontrada.");
                    }
                    break;

                case 3:
                    System.out.print("Número: ");
                    numero = scanner.nextLine();

                    if (service.eliminarCuenta(numero)) {
                        System.out.println("Cuenta eliminada.");
                    } else {
                        System.out.println("Cuenta no encontrada.");
                    }
                    break;

                case 4:
                    for (CuentaCorriente cuenta : service.listarCuentas()) {
                        System.out.println("Titular: " + cuenta.getTitular());
                        System.out.println("Número: " + cuenta.getNumeroCuenta());
                        System.out.println("Saldo: " + cuenta.getSaldo());
                        System.out.println("Descubierto: " + cuenta.getDescubierto());
                        System.out.println("---------------------");
                    }
                    break;

                case 5:
                    System.out.print("Número: ");
                    numero = scanner.nextLine();

                    System.out.print("Monto: ");
                    double monto = scanner.nextDouble();

                    service.depositar(numero, monto);
                    break;

                case 6:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 6);

        scanner.close();
    }
}
