package simpletron;

import java.util.Scanner;

public class Simpletron {
	
	private static final Scanner entrada = new Scanner(System.in); // Permite leer los datos
				// del usuario
	
	// OPERACIONES
	private final int LEER = 10;
	private final int ESCRIBIR = 11;
	
	private final int CARGAR = 20;
	private final int ALMACENAR = 21;
	
	private final int SUMAR = 30;
	private final int RESTAR = 31;
	private final int DIVIDIR = 32;
	private final int MULTIPLICAR = 33;
	
	private final int BIFURCAR = 40;
	private final int BIFURCARNEG = 41;
	private final int BIFURCARCERO = 42;
	private final int TERMINAR = 43;
	
	// HARDWARE
	private int[] memoria = new int[100];
	
	//registro
	private int acumulador;				//guarda el resultado
	private int contadorInstrucciones;	//determina la ubicacion en la memoria de la instruccion a ejecutar (PC)
	private int codigoOperacion;		//DETERMINA LA OPERACION A REALIZAR
	private int operando;				//ubicacion de la memoria donde se encuentran los datos
	private int registroInstruccion;
	
	private int[][] programas = {{1028, 1027, 2027, 4101, 2027, 4201, 1025, 2026, 3127, 4222, 1024, 2025, 3124, 4016, 2024, 2125, 2026, 3028, 2126, 2026, 3127, 4110, 1125, 4300, 0000, 0000, 0000, 0000, 0000 },
			
	};
	
	public Simpletron(int programa) {
		for (int i = 0; i < programas[programa].length; i++) {
			memoria[i] = programas[programa][i];
		}
	}
	
	public void cargar() {
		
		int ubicaci�n = 0; // La ubicaci�n de memoria
		int palabra;
		
		System.out.printf("*** %s ***%n", "�Bienvenido simpletron!");
		System.out.printf("*** %s ***%n", "Por favor, introduzca en su programa una instrucci�n");
		System.out.printf("*** %s ***%n", "(o palabra de datos) a ala vez. Yo le mostrar�");
		System.out.printf("*** %s ***%n", "el n�mero de hubicaci�n y un signo de interrogaci�n (?)");
		System.out.printf("*** %s ***%n", "Entonces usted escribir� la palabra para esa ubicaci�n.");
		System.out.printf("*** %s ***%n", "Teclee -99999 para dejar de introducir su programa.");
		
		do {
			
			System.out.printf("%02d ? ", ubicaci�n);
			palabra = entrada.nextInt(); // Lee el sgte entero escrito por el usuario
			
			if(palabra != -99999) {
				
				memoria[ubicaci�n] = palabra;
				ubicaci�n++;
			}

			
		} while (palabra != -99999);
		
		System.out.printf("*** %s ***%n", "Se complet� la carga del programa");
		System.out.printf("*** %s ***%n", "Empieza la ejecuci�n del programa");

	} // cargar()
	
	void cpu() {
		do {
			obtener();	//obtener una instruccion de la memoria
			decodificar();//decodificar la isntruccion obtenida
			ejecutar();  //ejecutar las instruccion
		} while (codigoOperacion != 43);
	} //cpu
	
	void obtener() {
		registroInstruccion = memoria[contadorInstrucciones];
	}//obtener()
	
	void decodificar() {
		codigoOperacion = registroInstruccion/100;	//obtiene los pri,eros dos digitos de la instruccion
		operando = registroInstruccion%100;			//obtiene los ultimos dos digitos de la instruccion
	} //decodificar()
	
	void ejecutar() {
		switch (codigoOperacion) {
			case LEER:
				System.out.printf("*** %s%n", "ingrese el valor: ");
				memoria[operando]= entrada.nextInt();
			
				break;
			case ESCRIBIR:
				System.out.printf("%n%d", memoria[operando]);
				break;
			case CARGAR:
				acumulador = memoria[operando];
				break;
			case ALMACENAR:
				memoria[operando]= acumulador;
				break;
			case SUMAR:
				acumulador += memoria[operando];
				break;
			case RESTAR:
				acumulador -= memoria[operando];
				break;
			case DIVIDIR:
				acumulador /= memoria[operando];
				break;
			case MULTIPLICAR:
				acumulador *= memoria[operando];
				break;
			case BIFURCAR:
				contadorInstrucciones = operando;
				return;
			case BIFURCARNEG:
				if (acumulador < 0) {
					contadorInstrucciones = operando;
					return;
				}
				break;
			case BIFURCARCERO:
				if (acumulador == 0) {
				contadorInstrucciones = operando;
					return;
				}
				break;
			default:
				codigoOperacion = TERMINAR;
				return;
		}
		++contadorInstrucciones;
	}//ejecutar
	
	public void vaciar() {
		
		System.out.println("\nREGISTROS:");
		System.out.printf("%-26s %+05d%n", "Acumulador:", acumulador);
		System.out.printf("%-26s	%02d%n", "Contador de instrucciones:", contadorInstrucciones);
		System.out.printf("%-26s %+05d%n", "Registro de Instruccion", registroInstruccion);
		System.out.printf("%-26s	%02d%n", "Codigo de operacion::", codigoOperacion);
		System.out.printf("%-26s	%02d%n", "Operando:", operando);
		
		
		System.out.printf("%s%n  ","MEMORIA:");
		for(int c =0; c < 10; c++) {
			
			System.out.printf("%6d", c);
		}
		
		System.out.println();
		
		for(int f = 0; f < 100; f += 10) {
			
			System.out.printf("%2d ", f);
			
			for(int c =0; c<10; c++) {
				
				System.out.printf("%+05d ", memoria[ f + c ]);
				
			}
			System.out.println();
		}
		
	} // Vaciar()
	
	
} // Simpletron
