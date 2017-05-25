import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class PRAC2 extends PApplet {

/**
*   PRACTICA 2
*   Integraci\u00f3 digital de continguts
*
*   @file PRAC2.pde
*   @author David Vinagre Cerezo
*   @version 1
*/

// declaraci\u00f3 de objectes i variables globals
Bird bird;            // objeto pajaro
Saltarina saltarina;  // objeto mujer saltando
PImage bg;            // imagen de fondo
int frameState;       // variable {entero} que guarda estado animacion
                      // 1 == en reproduccion
                      // 2 == parado

public void setup(){
   // medidas del documento
  frameRate(24);  // frames por segundo
  frameState = 1; // estado aplicacion

  // iniciamos variables y objetos
  bg = loadImage("images/background.jpg"); // cargamos la imagen de fondo
  bg.resize(640, 360);  // redimensionamos la imagen al mismo tama\u00f1o que el sketch

  // incializamos objeto bird
  // pasamos parametros de ruta a imagen i tama\u00f1o de frame
  bird = new Bird("images/ocell_SpriteSheet.png", 68, 94);
  bird.position(680,100); // posicion inicial del pajaro

  // incializamos objeto Saltarina
  // pasamos parametros de ruta a imagen i tama\u00f1o de frame
  saltarina = new Saltarina("images/saltando_sprite.png", 94, 214);
  saltarina.stopFrame(28);      // definimos fotograma de parada
  saltarina.position(400,200);  // definimos posicion x, y
}

public void draw(){
  background(bg); // muestra imagen de fondo
  bird.updatePosition(); // metodo que actualiza posicion del pajaro
  bird.display(); // muestra el pajaro
  // definimos guion formado por 2 frames
  switch(frameState) {
    case 1: // frame 1
      saltarina.play(); // animacion saltarina en reproduccion
      break;
    case 2: // frame 2
      saltarina.stop(); // animacion saltarina parada
      break;
  }
  saltarina.display();  // muestra saltarina
  showText(frameState); // funcion que muestra el texto
}

/**
*   Interacci\u00f3n de raton, la acci\u00f3n passa del frame 1 al frame 2
*/
public void mouseClicked() {
  frameState = (frameState == 1) ? 2 : 1;
}

/**
*   Funci\u00f3n que muestra un texto segun el estado de la aplicacion
*   @param fState {entero}
*/
public void showText(int fState){
  String textToShow;

  if(fState == 1){
    textToShow = "FRAME 1: SALTANT";
  } else {
    textToShow = "FRAME 2: PARADA";
  }
  text(textToShow, 20, 20); // mostramos el texto
}
/**
 * @CLASE Animation
 * clase que permite crear una animacion a partir de la clase SpriteSheet
 * extiende la clase SpriteSheet
 *
 * @PROPIEDADES
 * $frameSpeed {entero} velocidad de frames
 * $playStop {boleano} reproduce o para la animacion segun valores
 * $currentFrame {entero} fotograma en el que se encuentra la animacion
 * $totalFrames {entero} numero total de fotogramas que tiene la animacion
 * $posX {entero} posicion X de la animacion
 * $posY {entero} posicion Y
 * $scaleX {float} escala X de la animacion1.0 es 100%, 0.5 es 50%
 * $scaleY {float} escala Y
 *
 * @METODOS (p\u00fablicos)
 * getFrame(int numeroFrame)  Devuelve un objeto PImage, una imagen,
 *                            un determinado frame segun un numero
 *                            0 es el primer frame
 *
 * frameSpeed(int velFrames)  modifica la velocidad de la animacion
 *                            1 -> mismo frameRate sketch
 *                            2 -> 1/2 frameRate
 *
 * playStop()                 detiene o reproduce animacion
 *
 * position(int posicionX, int posicionY)   define posicion de la animacion
 *
 * scale(float escalaX, float escalaY)      permite ampliar / disminuir animacion
 *
 * display()    Muestra la animacion
 *
 * @METODOS (privados s\u00f3lo son accesibles desde dentro de la propia clase)
 * scaleImage(PImage immagenAEscalar)   escala una imagen segun $scaleX y $scaleY
 *
 * @see class SpriteSheet
 */

class Animation extends SpriteSheet{

  // PROPIEDADES
  int frameSpeed = 1;       // la animacion va al mismo ritmo que el frameRate
  boolean playStop = true;  // la animacion por defecto se esta reproduciendo
  int currentFrame = 0;     // la animacion inicia en el frame 0

  // numero total de frames que contiene la animacion
  // extraemos el numero tota de frames
  // con el m\u00e9todo .size() del objeto ArrayList
  int totalFrames = posFrames.size();

  // posicion de la animacion, por defecto en 0,0
  int posX = 0;
  int posY = 0;

  // spriteScale, por defecto a la misma escala
  float scaleX = 1.0f;
  float scaleY = 1.0f;

  // Realizar un seguimiento de la velocidad de frames
  int counter;

  /**
  * CONSTRUCTOR
  * mismos parametres clase SpriteSheet
  * @see constructor class SpriteSheet
  */
  Animation(String p, int w, int h){
  	// (super) palabra clave utilizada para referenciar la superclase de una subclase.
  	// pasa los parametros al constructor de la superclase
    super(p, w, h);
  }

  /**
  * SETTER que define la velocidad de frames de la animacion
  * 1 misma velocidad framerate sketch
  * 2 velocidad de un frame cada 2 frames del sketch...
  *
  * @param $s (entero)
  */
  public void frameSpeed(int s){
  	frameSpeed = s;
  }

  /* SETTER que detiene o reproduce la animacion */
  public void playStop(){
    playStop = !playStop;
  }

  /* SETTER que reproduce la animacion */
  public void play(){
    playStop = true;
  }

  /* SETTER que detiene la animacion */
  public void stop(){
    playStop = false;
  }

  /* SETTER que permite definir la posicion de la animacion */
  public void position(int x, int y){
    posX = x;
    posY = y;
  }

  /* SETTER que permite definir la escala la animacio */
  public void scale(float sX, float sY){
    scaleX = sX;
    scaleY = sY;
  }

  /**
  * SETTER que permite ampliar disminuir la imagen que pasamos como parametro
  *
  * @param $img (PImage) -> imagen a la que aplicamos el escalado
  */
  private void scaleImage(PImage img){
    // redimensiona la imagen mediante el m\u00e9todo
    // .resize del objeto PImage
     img.resize(PApplet.parseInt(frameW*scaleX), PApplet.parseInt(frameH*scaleY));
  }

  /**
  * SETTER que reproduce la animacion dependiendo del valor de $playStop
  * actualiza el valor de $counter i $currentFrame
  */
  public void updateCurrentFrame(){

    // si playstop==true reproduce animacion
    if(playStop){
      // controla el frameRate para el spriteSheet
      if(counter == (frameSpeed -1)){
        currentFrame = (currentFrame +1) % totalFrames;
      }
      // actualizamos $counter
      counter = (counter + 1) % frameSpeed;
    }
  }

  /**
  * GETTER que muestra la animacion
  */
  public void display(){
    // Objeto donde guardaremos la imagen a mostrar
    // esto nos permite aplicarle efectos como escalados
    PImage imageToDisplay;

    // actualizamos el valor $counter i $currentFrame
    updateCurrentFrame();

    // associamos el frame a la imagen
    imageToDisplay = this.getFrame(currentFrame);

    // aplicamos transformaciones a la imagen
    this.scaleImage(imageToDisplay);

    // modo de mostrar la imagen
    imageMode(CENTER);

    // mostramos la imagen
    image(imageToDisplay, posX, posY);
  }
}
/**
 * @file Bird.pde
 *
 * @CLASE Bird
 * clase que extiende la class Animation
 *
 *   @PROPIEDADES
 *   $posXInicial {entero} posicion X de inicio del pajaro
 *   $posYInicial {entero} posicion Y de inicio;
 *
 *   @METODOS
 *   position(int posicionX, int posicionY) define posicion de inicio de la animacion
 *   updatePosition()      define trajectoria del pajaro
 *
 *   @see class Animation
 */
class Bird extends Animation{
  // PROPIEDADES
  int posXInicial;
  int posYInicial;

  /**
  * CONSTRUCTOR
  * @see constructor class SpriteSheet
  */
  Bird (String p, int w, int h){
    super(p, w, h); // pasa los parametros al constructor de la superclase
  }

  /*
  * SETTER que permite define la posicion incial de la animacion
  * sobreescribe el m\u00e9todo position() de la class Animation
  *
  * @see class Animation.position()
  */
  public void position(int x, int y){
    posX = x;
    posY = y;
    posXInicial = x;
    posYInicial = y;
  }


  /*
  * SETTER que actualiza la posicion de la animacion
  * Crea la animacion lateral del pajaro
  */
  public void updatePosition(){
    // cada frame que pasa el pajaro se mueve 2 pixeles a la izquierda
    posX = posX-3;

    // cada frame que pasa el pajaro se mueve arriba o abajo de forma aleatoria
    posY = posY + PApplet.parseInt(random(-3.0f,3.0f));

    // cuando el pajaro sale de la pantalla 40px vuelve a la posicion inicial
    if( posX < -40){
      posX = posXInicial;
    }
  }

}
/**
 * @file Saltarina.pde
 *
 * @CLASE Saltarina
 * clase que extiende la class Animation
 *
 *   @PROPIEDADES
 *   $stopFrame {entero} fotograma en el que se detiene la animaci\u00f3n
 *
 *   @METODOS
 *   stopFrame(int ultimoFrame)  Define
 *
 *   @see class Animation
 */
class Saltarina extends Animation{

  //  Atributo que define en que frame se detiene la animaci\u00f3n
  //  por defecto corresponde al \u00faltimo frame
  //  esto se hace para que la saltarina detenga la animaci\u00f3n siempre en el suelo
  int stopFrame = totalFrames-1;

  /**
  * CONSTRUCTOR
  * @see constructor class SpriteSheet
  */
  Saltarina (String p, int w, int h){
    super(p, w, h); // pasa los parametros al constructor de la superclase
  }

  /**
   * SETTER que define el \u00faltimo frame de la animaci\u00f3n
   * donde se detiene
   * @param $sF ->nuevo valor para stopFrame
   */
  public void stopFrame(int sF){
    stopFrame = sF;
  }

  /**
  * SETTER que reproduce la animacion dependiendo del valor de $playStop
  * sobreescribe el m\u00e9todo play() de la class Animation
  * hace que la animaci\u00f3n se pare en $stopFrame
  *
  * @see class Animation.updateCurrentFrame()
  */
  public void updateCurrentFrame(){

    // no detiene la animacion hasta que currentFrame
    if(playStop || (playStop != true) && (currentFrame != stopFrame)){

      // controla el frameRate para el sprite Sheet
      if(counter == (frameSpeed -1)){
        currentFrame = (currentFrame +1) % totalFrames;
      }
      counter = (counter + 1) % frameSpeed;
    }
  }

}
/**
 * @file SpriteSheet.pde
 *
 * @CLASE SpriteSheet
 * permite generar un spriteSheet mediante una imagen
 * con el m\u00e9todo getFrame(int) se devuelve uno de los frames del spriteSheet
 *
 * @PROPIEDADES
 * $spriteSheet {String}  ruta donde esta la imagen que forma el spriteSheet
 * $posFrame {ArrayList}  de vectores con la posici\u00f3n de cada frame
 * $frameW {entero}       Ancho del frame
 * $frameH {entero}       Alto del frame
 *
 * @METODOS (p\u00fablicos)
 * getFrame(int)  Devuelve un objeto PImage
 *                un determinado frame segun un numero
 *                0 es el primer frame
 */
class SpriteSheet{

  // PROPIEDADES o CAMPOS
  PImage spriteSheet; // donde guardamos la imagen spriteSheet
  int frameW;         // ancho del frame
  int frameH;         // alto del frame
  ArrayList<PVector> posFrames; // Array donde guardamos posicion de cada frame

  /**
   * CONSTRUCTOR
   * permite pasar ruta al archivo sprite sheet y ancho / alto del frame
   *
   * @param imageP ruta a la imagen que forma el sprite Sheet
   * @param w ancho del frame
   * @param h alto del frame
   */
  SpriteSheet(String imageP, int w, int h){

    // inicializamos las propiedades de clase
    spriteSheet = loadImage(imageP); // carga imagen en spriteSheet
    frameW = w;
  	frameH = h;
    // iniciamos el arrayList posFrames
    posFrames = new ArrayList<PVector>();

    // calcula num filas sprite sheet
    int rows = spriteSheet.height / frameH;

    // calcula num de columnas del sprite sheet
    int cols = spriteSheet.width / frameW;

    // llamamos al metodo privado que genera todos las posiciones de los frames
    // i los guarda en arrayList SpriteSheet
    this.setFramesPosition(rows, cols);
  }

  /**
  * SETTER que genera todas las posiciones
  * de los diferentes frames llena posFrames de valores
  *
  * @param $r numero de filas
  * @param $c numero de columnas
  */
  private void setFramesPosition(int r, int c){
    // recorremos todas las filas (r)
    for (int i = 0; i < r; i++){
        // recorremos todas las columas (c)
        for (int e = 0; e < c; e++){
          // a\u00f1adimos nuevo vector a posFrames
          // con el m\u00e9todo .add de los obejtos ArrayList
          // que a\u00f1ade un nuevo valor
          posFrames.add( new PVector(e*frameW, i*frameH));
        }
    }
  }

  /**
  * GETTER que devuelve a una imagen segun el numero de frame
  *
  * @param frameNumber numero de frame inicia en 0
  * @return PImage  devuelve una imagen, un frame
  *
  */
  public PImage getFrame(int frameNumber){

    // variable donde guardamos un vector
    PVector pos = new PVector();

    // extraemos un valor del array posFrames
    // con el m\u00e9todo .get(numeroItemArray) del objeto ArrayList
    // este permite extraer un elemento segun su \u00edndice n\u00famero
    // lo associamos al vector pos
    pos = posFrames.get(frameNumber);

    // devolvemos una imagen extraida de spriteSheet
    // usamos el m\u00e9todo get de los objetos PImage
    // este permite leer el color de un pixel o tomar una seccion de una imagen
    // .get(posicionX, posicionY, anchoImagen, altoImagen)
    return spriteSheet.get(PApplet.parseInt(pos.x), PApplet.parseInt(pos.y), frameW, frameH);
  }
}
  public void settings() {  size(640, 360); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "PRAC2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
