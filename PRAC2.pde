/**
*   PRACTICA 2
*   Integració digital de continguts
*
*   @file PRAC2.pde
*   @author David Vinagre Cerezo
*   @version 1
*/

// declaració de objectes i variables globals
Bird bird;            // objeto pajaro
Saltarina saltarina;  // objeto mujer saltando
PImage bg;            // imagen de fondo
int frameState;       // variable {entero} que guarda estado animacion
                      // 1 == en reproduccion
                      // 2 == parado

void setup(){
  size(640, 360); // medidas del documento
  frameRate(24);  // frames por segundo
  frameState = 1; // estado aplicacion

  // iniciamos variables y objetos
  bg = loadImage("images/background.jpg"); // cargamos la imagen de fondo
  bg.resize(640, 360);  // redimensionamos la imagen al mismo tamaño que el sketch

  // incializamos objeto bird
  // pasamos parametros de ruta a imagen i tamaño de frame
  bird = new Bird("images/ocell_SpriteSheet.png", 68, 94);
  bird.position(680,100); // posicion inicial del pajaro

  // incializamos objeto Saltarina
  // pasamos parametros de ruta a imagen i tamaño de frame
  saltarina = new Saltarina("images/saltando_sprite.png", 94, 214);
  saltarina.stopFrame(28);      // definimos fotograma de parada
  saltarina.position(400,200);  // definimos posicion x, y
}

void draw(){
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
*   Interacción de raton, la acción passa del frame 1 al frame 2
*/
void mouseClicked() {
  frameState = (frameState == 1) ? 2 : 1;
}

/**
*   Función que muestra un texto segun el estado de la aplicacion
*   @param fState {entero}
*/
void showText(int fState){
  String textToShow;

  if(fState == 1){
    textToShow = "FRAME 1: SALTANT";
  } else {
    textToShow = "FRAME 2: PARADA";
  }
  text(textToShow, 20, 20); // mostramos el texto
}
