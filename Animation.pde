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
 * @METODOS (públicos)
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
 * @METODOS (privados sólo son accesibles desde dentro de la propia clase)
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
  // con el método .size() del objeto ArrayList
  int totalFrames = posFrames.size();

  // posicion de la animacion, por defecto en 0,0
  int posX = 0;
  int posY = 0;

  // spriteScale, por defecto a la misma escala
  float scaleX = 1.0;
  float scaleY = 1.0;

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
  void frameSpeed(int s){
  	frameSpeed = s;
  }

  /* SETTER que detiene o reproduce la animacion */
  void playStop(){
    playStop = !playStop;
  }

  /* SETTER que reproduce la animacion */
  void play(){
    playStop = true;
  }

  /* SETTER que detiene la animacion */
  void stop(){
    playStop = false;
  }

  /* SETTER que permite definir la posicion de la animacion */
  void position(int x, int y){
    posX = x;
    posY = y;
  }

  /* SETTER que permite definir la escala la animacio */
  void scale(float sX, float sY){
    scaleX = sX;
    scaleY = sY;
  }

  /**
  * SETTER que permite ampliar disminuir la imagen que pasamos como parametro
  *
  * @param $img (PImage) -> imagen a la que aplicamos el escalado
  */
  private void scaleImage(PImage img){
    // redimensiona la imagen mediante el método
    // .resize del objeto PImage
     img.resize(int(frameW*scaleX), int(frameH*scaleY));
  }

  /**
  * SETTER que reproduce la animacion dependiendo del valor de $playStop
  * actualiza el valor de $counter i $currentFrame
  */
  void updateCurrentFrame(){

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
  void display(){
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
