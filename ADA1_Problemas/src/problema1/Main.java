import java.util.Stack;

public class Main{
    public Main(){
    }

    public static void evaluarPostfija(String cadena){
        Stack<Integer> evaluando = new Stack<Integer>();
        //evaluando.push(0);      //Ingresamos 0 por si -2
        String[] cadenaRaw = cadena.split(" ");
        System.out.println("hola mundo");
        
        
        for (int i=0; i<cadenaRaw.length; i++){
            //char k = cadena.charAt(i);
            System.out.println("k: "+ cadenaRaw[i]);
            //if( k=='+'|| k=='-'|| k=='*'|| k=='/'|| k=='^'){
            /*if(cadenaRaw[i].equals("^")||
            cadenaRaw[i].equals("+")||cadenaRaw[i].equals("-")||
            cadenaRaw[i].equals("*")||cadenaRaw[i].equals("/")){
                
                System.out.println("Operador aritmetico");
                int tempActual;
                int tempAnterior;
                tempActual = evaluando.pop();
                tempAnterior = evaluando.pop();
                int resultado=0;
                System.out.println(tempActual);
                System.out.println(tempAnterior);
                System.out.println(resultado);
                
                char k = cadenaRaw[i].charAt(0);
                switch (k) {
                    case '+':
                        resultado = tempAnterior + tempActual;
                            System.out.println(tempActual);
                            System.out.println(tempAnterior);
                            System.out.println(resultado);
                        break;
                    case '-':
                        resultado = tempAnterior - tempActual;
                            System.out.println(tempActual);
                            System.out.println(tempAnterior);
                            System.out.println(resultado);
                        break;
                    case '*':
                        resultado = tempAnterior * tempActual;
                            System.out.println(tempActual);
                            System.out.println(tempAnterior);
                            System.out.println(resultado);
                        break;
                    case '/':
                        resultado = tempAnterior / tempActual;
                            System.out.println(tempActual);
                            System.out.println(tempAnterior);
                            System.out.println(resultado);
                        break;
                    case '^':
                        resultado = tempAnterior;  //ej. 2^3 res = 2 =>2*2 -> 4*2
                        for(int jTemp=1; jTemp<tempActual; jTemp++){
                            resultado *= resultado;
                        }
                        break;
                    default:
                    System.out.println("Kejesto");
                        break;
                }
                System.out.println("===================");
                evaluando.push(resultado);
                System.out.println("Metiendo a la pila: "+resultado);
            } else if(cadenaRaw[i].equals(" ")){
                System.out.println("Cadena vacia");
            }*/
            /*evaluando.push( Integer.parseInt(cadenaRaw[i]) );
            evaluando.peek();*/
        }
    }

    public static void main(String[] args) {
        String cadena = "2 4 + 7 * 3 9 5 - * +;";
        evaluarPostfija(cadena);
    }
}

/*
if(cadenaRaw[i].equals("^")||
           cadenaRaw[i].equals("+")||cadenaRaw[i].equals("-")||
           cadenaRaw[i].equals("*")||cadenaRaw[i].equals("/")){*/