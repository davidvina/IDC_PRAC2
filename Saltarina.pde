/**
 * @file Saltarina.pde
 *
 * @CLASE Saltarina
 * clase que extiende la class Animation
 *
 *   @PROPIEDADES
 *   $stopFrame {entero} fotograma en el que se detiene la animación
 *
 *   @METODOS
 *   stopFrame(int ultimoFrame)  Define
 *
 *   @see class Animation
 */
class Saltarina extends Animation{

  //  Atributo que define en que frame se detiene la animación
  //  por defecto corresponde al último frame
  //  esto se hace para que la saltarina detenga la animación siempre en el suelo
  int stopFrame = totalFrames-1;

  /**
  * CONSTRUCTOR
  * @see constructor class SpriteSheet
  */
  Saltarina (String p, int w, int h){
    super(p, w, h); // pasa los parametros al constructor de la superclase
  }

  /**
   * SETTER que define el último frame de la animación
   * donde se detiene
   * @param $sF ->nuevo valor para stopFrame
   */
  void stopFrame(int sF){
    stopFrame = sF;
  }

  /**
  * SETTER que reproduce la animacion dependiendo del valor de $playStop
  * sobreescribe el método play() de la class Animation
  * hace que la animación se pare en $stopFrame
  *
  * @see class Animation.updateCurrentFrame()
  */
  void updateCurrentFrame(){

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
