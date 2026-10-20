import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.iniciar();
    }
}

class Menu {

    private final Scanner scanner;
    private final int[] listaOriginal;
    private boolean ordenado;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.listaOriginal = new int[20];
        this.ordenado = false;
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = scanner.nextInt();
            ejecutarOpcion(opcion);
        } while (opcion != 4);

        scanner.close();
    }

    private void mostrarOpciones() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Generar números aleatorios");
        System.out.println("2. Ordenar con Quicksort");
        System.out.println("3. Buscar un número (Búsqueda Binaria)");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                ArregloUtils.generarNumeros(listaOriginal);
                ordenado = false;
                break;
            case 2:
                System.out.println("\n--- Ordenando arreglo ---");
                ArregloUtils.ordenarQuicksort(listaOriginal, 0, listaOriginal.length - 1);
                System.out.println("\nLista ordenada:");
                ArregloUtils.imprimirArreglo(listaOriginal);
                ordenado = true;
                break;
            case 3:
                if (!ordenado) {
                    System.out.println("\n¡Atención! Debe ordenar la lista antes de realizar la búsqueda binaria.");
                    break;
                }
                System.out.print("\nIngrese el número a buscar: ");
                int valorBuscado = scanner.nextInt();

                int posicion = ArregloUtils.buscarBinario(listaOriginal, valorBuscado, 0, listaOriginal.length - 1);

                if (posicion != -1) {
                    int puntoMedio = (listaOriginal.length - 1) / 2;
                    System.out.println("El número " + valorBuscado + " se encuentra en el índice: " + posicion);

                    if (posicion <= puntoMedio) {
                        System.out.println("Ubicación: Lado izquierdo del arreglo.");
                    } else {
                        System.out.println("Ubicación: Lado derecho del arreglo.");
                    }
                } else {
                    System.out.println("El número " + valorBuscado + " no fue encontrado en el arreglo.");
                }
                break;
            case 4:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
}

class ArregloUtils {

    public static void generarNumeros(int[] arreglo) {
        System.out.println("\nLista original:");
        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = (int) (Math.random() * 50 + 1);
            System.out.print(arreglo[i] + " ");
        }
        System.out.println();
    }

    public static void ordenarQuicksort(int[] arreglo, int inferior, int superior) {
        int i = inferior;
        int j = superior;
        int central = (inferior + superior) / 2;
        int pivote = arreglo[central];

        System.out.println("Límites que se analizan. Inferior: " + i + " Superior: " + j + " Pivote: " + pivote);

        do {
            while (arreglo[i] < pivote) i++;
            while (arreglo[j] > pivote) j--;

            if (i <= j) {
                intercambiar(arreglo, i, j);
                i++;
                j--;
            }
        } while (i <= j);

        if (inferior < j) {
            ordenarQuicksort(arreglo, inferior, j);
        }
        if (i < superior) {
            ordenarQuicksort(arreglo, i, superior);
        }
    }

    private static void intercambiar(int[] arreglo, int i, int j) {
        int aux = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = aux;
    }

    public static int buscarBinario(int[] arreglo, int valorBuscado, int inferior, int superior) {
        if (inferior > superior) {
            return -1;
        } else {
            int central = (inferior + superior) / 2;
            if (arreglo[central] == valorBuscado) {
                return central;
            } else if (arreglo[central] < valorBuscado) {
                return buscarBinario(arreglo, valorBuscado, central + 1, superior);
            } else {
                return buscarBinario(arreglo, valorBuscado, inferior, central - 1);
            }
        }
    }

    public static void imprimirArreglo(int[] arreglo) {
        for (int num : arreglo) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}