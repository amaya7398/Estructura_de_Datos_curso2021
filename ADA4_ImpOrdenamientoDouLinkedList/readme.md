# ADA4 SELECTION SORT, BINARY SEARCH & DOUBLY LINKED LIST
Ejecutamos el programa desde cmd.
Teniendo la posibilidad de pasar por parametro los archivos de ENTRADA y/o SALIDA.
    Pudiendo pasar desde 0 hasta 2 parametros
    SI fuese uno, equivaldría a pasar el archivo IN y por defecto usaría "moviesOrdenado.csv"
    SI fuesen dos, equivaldría a pasar el archivo IN y OUT
    SI no ingresase algún archivo, este tomaría archivos por defecto para IN: "Movies.csv" y para OUT: "moviesOrdenado.csv"

El programa carga las movies y las guarda de manera dinamica en "list" de tipo MovieDoublyLinkedList
Una vez cargado el maximo numero de Movies, que por defecto son 50 y este se puede cambiar en la clase ListaMovie.java, en su atributo privado "maxMovies",
Nos desplegará un texto anunciando todas estas operaciones

```
Loading...
50 movies loaded
LOAD DONE
```

Seguido de esto nos desplegará un menu con 4 opciones:
    0: Para finalizar el programa
    1: Para realizar busqueda binaria, que por defecto esta ordena la lista de menor a mayor dado el ID;
    2: Para ordenar la lista dado el ID de cada pelicula   ;
    3: Para ordenar la lista dado el titulo de la pelicula ;

* Si escogemos la opción [0] el programa terminará

* Si escogemos la opción [1] nos pedira ingresar un entero, el cual será el ID a buscar dentro de la lista y seguido nos desplegará las metricas
    sobre la ejecución de esta misma
    SI el ID no se encontrase nos desplegaría una pelicula vacía
    SI el ID se encontrase, nos desplegará la información detallada de esta misma

* Si escogemos la opción [2] o [3] se desplegarán dos opciones que nos indicarán el orden, ya sea ascendente o descendente:
    Opción [1]: ascendente;  De menor a mayor, ya sea por ID o título
    Opción [0]: descendente; De mayor a menor, ya sea por ID o título
Después de escoger el orden, nos desplegará las metricas sobre la ejecución de este mismo
Y se persistirá este orden en el documento OUT que ingresamos desde CMD, por defecto será "movieOrdenado.csv"

* Si se usará alguna otra opción nos desplegaría un "ERROR"
