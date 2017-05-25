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
  * sobreescribe el método position() de la class Animation
  *
  * @see class Animation.position()
  */
  void position(int x, int y){
    posX = x;
    posY = y;
    posXInicial = x;
    posYInicial = y;
  }


  /*
  * SETTER que actualiza la posicion de la animacion
  * Crea la animacion lateral del pajaro
  */
  void updatePosition(){
    // cada frame que pasa el pajaro se mueve 2 pixeles a la izquierda
    posX = posX-3;

    // cada frame que pasa el pajaro se mueve arriba o abajo de forma aleatoria
    posY = posY + int(random(-3.0,3.0));

    // cuando el pajaro sale de la pantalla 40px vuelve a la posicion inicial
    if( posX < -40){
      posX = posXInicial;
    }
  }

}
