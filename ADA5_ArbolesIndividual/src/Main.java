
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
   
    static ArrayList<Prioridad> Lista = new ArrayList<Prioridad>(); //LISTA PARA PRIORIDADES DE OPERADORES
    static ArrayList<String> Lista2 = new ArrayList<String>();      //LISTA CON CADENAS DE LAS EXPRESIONES, LINEAS CORRECTAS

    public static void main(String[] args) throws Exception {
        TreeGenerico<Integer> tree = new TreeGenerico<>();

        String infijo="";
        String postfijo="";
        String salida ="";
        inicializarPrioridad(Lista);        //Llenamos "Lista" con las prioridades aritmeticas
        generarArchivo();                   //Generamos archivo "exp_postfijas.txt" si no existe, y ya :V
        String archivo= "exp_infijas.txt";  //Nombre del archivo en root del proyecto
        leerContenido(archivo);             //Para todas las líneas: quitamos espacios, solo añadimos las líneas que tengan parentesis balanceados
        //Despues de esta función solo habrán líneas correctas en "Lista2"

        for(int i=0;i<Lista2.size();i++){   //Iterar entre todas las líneas de expresiones aritmeticas
            try {
                infijo = Lista2.get(i);     //Linea i
                postfijo=aPostfijo(Lista,infijo);
                String postfijo2=postfijo.replace("^", "**");
                salida = "Exp["+(i+1)+"]: " + postfijo2 +"; Eval:"+ resultado(postfijo)+"\n";
                
                tree.expresionesAritmeticas(infijo);  //Creamos arbol binario de expresiones aritmeticas simples, desde una expresión infija
                String recorridos = tree.tresRecorridos();
                salida += recorridos +"\n\n";
            } catch (Exception e) {
                salida="ERROR\n";
            }
            anadirArchivo(salida);
            
            BTreePrinter.printNode(tree.getRoot()); //Imprimir arbol Binario
            
        }
        
        
    }

    private static int regresarValor(ArrayList<Prioridad> Lista, char signo2) {
        int x=0;
        for(int i=0; i<Lista.size();i++){
            if((Lista.get(i).signo)==signo2){
                x=Lista.get(i).valor;
            }
        }
    return x;
    }
    
    private static String aPostfijo(ArrayList <Prioridad> Lista, String infijo) throws StackException{
        String postfijo="";
        Stack<Character> pila = new  Stack<Character>(50);

        int i=0;
        int num=0;
        int ope=0;
        
        int longitud =infijo.length()-1;
        // System.out.println("La longitud de la cadena es: "+longitud);
        while (i < infijo.length()-1){
            char x= infijo.charAt(i);
            // System.out.println("Posicion:"+(i)+" Caracter: "+x);
            
            int y=0;
            if(Character.isDigit(x)){
                y=1;
            }
            if(infijo.charAt(i)=='(')
                y=2;
            if(infijo.charAt(i)==')')
                y=3;
            if(infijo.charAt(i)==('*') || infijo.charAt(i)==('/') || infijo.charAt(i)=='+' || infijo.charAt(i)=='-' || infijo.charAt(i)=='^'){
                y=4;
                ope++;
            }
        
            switch(y){
                //Caso de que es un número
                case 1:
                postfijo = postfijo + infijo.charAt(i);
                    if(i<longitud-1){
                        char p = infijo.charAt(i+1);
                        if(!Character.isDigit(p)){
                            postfijo = postfijo + " ";
                            num++;
                        }
                        
                    }else{
                        postfijo = postfijo + " ";
                        num++;
                    }
                break; 

                //Caso de que es un paréntesis a la izquierda
                case 2:
                pila.push(infijo.charAt(i));
                break;    

                //Caso de que es un paréntesis derecho
                case 3:                    
                while(pila.isEmpty()!=true && pila.peek()!='('){
                    postfijo=postfijo + pila.peek()+" ";
                    pila.pop();
                }
                pila.pop();
                break;
                
                //Caso de que es un operador
                case 4:
                    int a = regresarValor(Lista,infijo.charAt(i));
                    int b=0;
                    if(pila.isEmpty()!=true){
                        b = regresarValor(Lista,pila.peek());
                    }
                    
                    while(pila.isEmpty()!=true && b>=a){
                        postfijo=postfijo + pila.peek()+" ";
                        pila.pop(); 
                        if(pila.isEmpty()!=true)
                            b=regresarValor(Lista,pila.peek());
                    }
                    pila.push(infijo.charAt(i));   
                    break;   
            }     
            i++;
        }
        while(pila.isEmpty()!=true){
                postfijo=postfijo + pila.peek()+" ";
                pila.pop(); 
        }
        
        if(ope==(num-1)){
            return postfijo;
        }else{
            return null;
        }


        }
        
    private static void inicializarPrioridad(ArrayList <Prioridad> Lista){
        Lista.add(new Prioridad('^',3));
        Lista.add(new Prioridad('*',2));
        Lista.add(new Prioridad('/',2));
        Lista.add(new Prioridad('+',1));
        Lista.add(new Prioridad('-',1));
        Lista.add(new Prioridad('(',0));    
    }

    private static float resultado(String postfijo) throws StackException{
        Stack<Float> pila = new  Stack<Float>(50);
        float a=0;
        float b=0;
        String c="";
        int longitud =postfijo.length();
        for(int i=0; i<postfijo.length();i++){
            if(Character.isDigit(postfijo.charAt(i))){
                c=c+postfijo.charAt(i);
                if(i<longitud-2){
                char p=postfijo.charAt(i+1);
                if(!Character.isDigit(p)){
                pila.push(Float.parseFloat(c));
                c="";
                }
                }
            }else{
                if(postfijo.charAt(i)!=' '){
                    b=pila.peek();
                    pila.pop();
                    a=pila.peek();
                    pila.pop();
                    float res= operacion(a,b,postfijo.charAt(i));
                    pila.push(res);
                }
            }
        }
        return pila.peek();
    }

    private static float operacion(float a, float b, char op){
        float x=0;
        
        switch(op){
            case '+':
                x=a+b;   
            break;
            
            case '-':
                x=a-b;   
            break;
            
            case '*':
                x=a*b;       
            break;
            
            case '/':
                x=a/b;       
            break;
            
            case '^':
                x=(float) Math.pow(a, b);
            break;
            
                
                
        }
        
        return x;
    }
 
    private static void generarArchivo() throws IOException{
        File archivo = null;
        
        archivo = new File ("exp_postfijas.txt");
        if(!archivo.exists()){
            archivo.createNewFile();
        }
        
    }

private static void anadirArchivo(String cadena) throws IOException{
    BufferedWriter bw = null;
    FileWriter fw = null;
    File file = new File("exp_postfijas.txt");
    fw = new FileWriter(file.getAbsoluteFile(), true);
    bw = new BufferedWriter(fw);
    bw.write(cadena);
    bw.close();
    fw.close();

}

private static void leerContenido(String archivo) throws FileNotFoundException, IOException, StackException {
    String cadena;

    FileReader f = new FileReader(archivo);
    BufferedReader b = new BufferedReader(f);
    while((cadena = b.readLine())!=null) {
        cadena =cadena.replace(" ", "");
        boolean x =true;

        if(cadena.contains("++") || cadena.contains("--") || cadena.contains("//") || cadena.contains("***")){ //Hay algo mal en la sintaxis
            x=false;
        }else{
            cadena=cadena.replace("**","^"); //Todo está bien, remplazamos ** por ^
        }

        if(validarParentesis(cadena) && cadena.charAt(cadena.length()-1)==';' && x==true && (Character.isDigit(cadena.charAt(0))|| cadena.charAt(0)=='(')){
            Lista2.add(cadena);
        }
       
    }
    b.close();
}

    private static boolean validarParentesis(String infijo) throws StackException{
        Stack<Character> val = new Stack<Character>(20);
        int i=0;
        boolean x;
        int tam=infijo.length()-1;
        while(i<tam){
            if(infijo.charAt(i)=='('){
                val.push('(');
            }else{
                if(infijo.isEmpty()){
                    break;
                }else{
                    if(infijo.charAt(i)==')')
                        val.pop();
                }
            }
            i++;
        }
        if(val.isEmpty() & i==tam){
            x=true;
        }else{
            x=false;
        }
        
        return x;
    }
}






