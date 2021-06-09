
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
   
    static ArrayList<Prioridad> Lista = new ArrayList<Prioridad>();
    static ArrayList<String> Lista2 = new ArrayList<String>();
    public static void main(String[] args) throws Exception {
        String infijo="";
        String postfijo="";
        String salida ="";
        inicializarPrioridad(Lista);
        generarArchivo();
        String archivo= "exp_infijas.txt";
        leerContenido(archivo);
        

        for(int i=0;i<Lista2.size();i++){          
            try {
                infijo = Lista2.get(i);
                postfijo=aPostfijo(Lista,infijo);
                String postfijo2=postfijo.replace("^", "**");
                salida = "Exp: " + postfijo2 +"; Eval:"+ resultado(postfijo)+"\n";
            } catch (Exception e) {
                salida="ERROR\n";
            }
            anadirArchivo(salida);
            
        }
        
        TreeGenerico<Integer> a1 = new TreeGenerico<>();
        a1.insert(10);
        a1.insert(15);
        a1.insert(12);
        a1.insert(5);
        a1.insert(7);
        a1.insert(6);
        a1.insert(8);
        a1.insert(3);
        a1.insert(1);
        a1.insert(2);
        System.out.println("Finding: "+a1.find(11)+"\t"+ a1.find(5)+"\t"+ a1.find(15));
        a1.displayTree();
        a1.traverse(1);
        a1.traverse(2);
        a1.traverse(3);

        System.out.println("============================================================");
        BTreePrinter.printNode(a1.getRoot());
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
                char p=infijo.charAt(i+1);
                if(!Character.isDigit(p)){
                    postfijo = postfijo + " ";
                    num++;
                }
                    
                }else{
                    postfijo=postfijo+" ";
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

        if(cadena.contains("++") || cadena.contains("--") || cadena.contains("//") || cadena.contains("***")){
         x=false;
        }else{
          cadena=cadena.replace("**","^");
        }

        if(validarParentesis(cadena) && cadena.charAt(cadena.length()-1)==';' && (Character.isDigit(cadena.charAt(0))|| cadena.charAt(0)=='(') && x==true){
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






