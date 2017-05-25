/**
 * @file SpriteSheet.pde
 *
 * @CLASE SpriteSheet
 * permite generar un spriteSheet mediante una imagen
 * con el método getFrame(int) se devuelve uno de los frames del spriteSheet
 *
 * @PROPIEDADES
 * $spriteSheet {String}  ruta donde esta la imagen que forma el spriteSheet
 * $posFrame {ArrayList}  de vectores con la posición de cada frame
 * $frameW {entero}       Ancho del frame
 * $frameH {entero}       Alto del frame
 *
 * @METODOS (públicos)
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
          // añadimos nuevo vector a posFrames
          // con el método .add de los obejtos ArrayList
          // que añade un nuevo valor
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
    // con el método .get(numeroItemArray) del objeto ArrayList
    // este permite extraer un elemento segun su índice número
    // lo associamos al vector pos
    pos = posFrames.get(frameNumber);

    // devolvemos una imagen extraida de spriteSheet
    // usamos el método get de los objetos PImage
    // este permite leer el color de un pixel o tomar una seccion de una imagen
    // .get(posicionX, posicionY, anchoImagen, altoImagen)
    return spriteSheet.get(int(pos.x), int(pos.y), frameW, frameH);
  }
}
