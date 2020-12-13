Juego de Memoria con Leap Motion

Después de haber recibido el dispositivo de LEAP, estoy más que motivado para desarrollar aplicaciones con este aparato mágico que te permite controlar tus aplicaciones con los movimientos de tus dedos, manos, herramientas, gestos, etc. Por otro lado, desde hace mucho he querido hacer juegos y éste es un buen momento para empezar.

En la experiencia tuve en cuenta los siguientes aspectos clave:

1. Definir el tipo de aplicación a desarrollar: En este caso un juego de memoria, el cual es sencillo y fácil de implementar. Para empezar esta bien.

2. Escoger las herramientas de programación: Teniendo en cuenta que el objetivo principal es usar LEAP para controlar el juego hay que tener en cuenta las opciones que proporciona el API de LEAP. Entre los lenguajes soportados están Java, C#, C, Javascript, etc. En mi caso escogí Java ya que tengo cierta experiencia con este lenguaje. Por otra parte se debe escoger una librería de desarrollo de juegos. Escogí Slick2D por su facilidad de uso y por experiencias de otros desarrolladores que comentaban que era ideal para iniciarse en el mundo del desarrollo de videojuegos con Java.

3. Definir la lógica del juego y cómo interactuará el LEAP con la aplicación: La idea es establecer la estructura del juego, las vistas, los menús y otras cosas relacionadas con la interfaz junto con las opciones de interacción que ofrece LEAP. Para mi ejemplo, me tome las cosas con calma: necesitaría una vista con 18 cartas, las cuales en un principio están boca abajo para esconder la imagen que contienen y el juego permitiría seleccionar una carta y voltearla. Para seleccionar una carta usaría el gesto swipe, parecido al que se hace en un celular táctil cuando se pasan las fotos, y para voltearla usaría el gesto círculo, es decir se traza un círculo en el aire para voltear la carta.

Luego de estos 3 puntos solo queda implementar la aplicación y jugar. Para la implementación escribí las siguientes clases:

    Card.java -> define propiedades y acciones de una carta

    CardBox.java -> define las propiedades de la carta con respecto a su ubicación en la pantalla

    CardsLogic.java -> contiene todas las cartas y operaciones globales sobre las mismas

    Game.java -> contiene la vista de la aplicación

    LeapListener.java -> contiene el adaptador del LEAP controller

    MemoryGame.java -> instancia para jugar el juego con mouse

    MemorySlick.java -> instancia para jugar el juego con LEAP

Estas clases necesitan las siguientes dependencias:

    Lwjgl-2.9 (jinput.jar, lwjgl.jar, lwjgl_util.jar)

    Slick2D Library only (slick.jar)

    LeapJava (LeapJava.jar) viene con el SDK de Leap

También se necesita configurar las librerías nativas de Leap (LeapSDK/lib/x86/) y de Lwjgl (lwjgl-x.x/native/linux) en -Djava.path.library, para ello se abren las propiedades del proyecto > Run

image

En VM Options se escriben las URL’s de las librerías nativas mencionadas, en mi caso corresponden a las que estan entre parentesis.

Y no menos importante, se necesitan algunos recursos como imágenes y tipografías para usar en la aplicación. En este caso use imágenes de cartas de un naipe para armar las parejas.

También use una fuente especial para las letras del juego. Se encuentran en las carpetas /img y /fonts respectivamente.


Cuando la flechita del mouse se acerca a una carta, ésta se da cuenta de tal acción y reacciona al evento. Esto es posible gracias a la clase CardBox.

Esta clase define las propiedades de una caja imaginaria que contiene a la imagen de la carta y las coordenadas de origen de tal forma que se pueda calcular si el cursor colisiona con dicha carta.

Nota: Esta clase se usa solo en el juego con mouse.

Se establece una especie de grilla en la cual cada carta tiene una fila y una columna con la cual se calcula su posición en la ventana usando el margen acumulado más el ancho acumulado como coordenada x y el margen acumulado y el alto acumulado como coordenada y.

La grilla de cartas se crea ingresando los valores de las coordenadas x e y de cada carta, al momento de almacenar las cartas en un vector de cartas, sobre el cual se pueden realizar operaciones como abrir todas las cartas, cerrar todas las cartas, etc.

La clase Game contiene la vista principal del juego, extiende de la clase BasicGame proporcionada por Slick2D la cual facilita crear el juego y actualizar su estado. Tiene 3 metodos abstractos: init(), para cargar recursos como imágenes y fuentes, render() para mostrar las vistas del juego y update() para actualizar en tiempo real los componentes del juego de acuerdo a interacciones con dispositivos de entrada como el mouse o el LEAP.

La clase LeapListener extiende de la clase Listener del API de LEAP, la cual tiene las siguientes clases abstractas: onInit() para inicializar el dispositivo, onConnect() para ejecutar acciones cuando el dispositivo se conecte al puerto, onDisconnect() para ejecutar acciones al desconectar el dispositivo y onFrame() para procesar los frames que son unidades de información que proviene del dispositivo y que son mapeadas por las clases del API de LEAP.

En el método onFrame se activa la lectura de gestos y se hace un switch para realizar una acción de acuerdo al gesto detectado. En el caso que se haga un swipe, se evalúa la direccion del swipe la cual puede ser para arriba y abajo o para la izquierda y derecha. En el primer caso, el valor de las filas aumenta o disminuye respectivamente, en el segundo caso, el valor de las columnas aumenta o disminuye respectivamente, y cada vez que ocurra el gesto, se le envian los valores al juego para que mueva el selector de la carta hacia la izquierda, derecha, arriba o abajo. En el caso que se haga un círculo, se volteara la carta que tenga color rojo, es decir la seleccionada en el momento. Para esto se evalúan ciertas propiedades del circulo trazado como el radio y la magnitud del movimiento circular para poder identificar el deseo del usuario de voltear la carta. Cuando los valores mínimos se cumplan se le envia el dato al juego para que efectúe la acción de voltear la carta.

