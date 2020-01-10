//When I wrote this, only God and I understood what I was doing
//Now, God only knows...

/* World of Tanks v2.0 Beta
 * 
 * Author: GaborLevai
 * NeptunCode: AKTA7P
 * Total_hours_wasted: 20hrs
 * 
 */

package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;

public class Main extends Application {	
	@Override
	public void start(Stage primaryStage) {
		try {
			ViewManager manager = new ViewManager(); 
			primaryStage = manager.getMainStage();
			primaryStage.setTitle("World of Tanks v2.0 Beta");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}

/*
 * 
       .            .                     .
              _        .                          .            (
             (_)        .       .                                     .
.        ____.--^.
.      /:  /    |                               +           .         .
     /:  `--=--'   .                                                .
LS    /: __[\==`-.___          *           .
   /__|\ _~~~~~~   ~~--..__            .             .
   \   \|::::|-----.....___|~--.                                 .
    \ _\_~~~~~-----:|:::______//---...___
.   [\  \  __  --     \       ~  \_      ~~~===------==-...____
    [============================================================-
    /         __/__   --  /__    --       /____....----''''~~~~      .
*    /  /   ==           ____....=---='''~~~~ .
  /____....--=-''':~~~~                      .                .
  .       ~--~         KuatDrive Yard's Imperial-class Star Destroyer
                 .                                   .           .
                      .                      .             +
    .     +              .                                       <=>
                                           .                .      .
.                 *                 .                *                ` -
*/
