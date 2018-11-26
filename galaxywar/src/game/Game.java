package game;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sprites.*;

public class Game extends Application {
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int score;
	private final static double WIDTH = 1200;
	//= screenSize.getWidth();
	private final static double HEIGHT = 700;
	//= screenSize.getHeight()-64;
	private final static int NBPINAPPLES = 400;
	private final static Color COLORPLAYER1 = Color.ORANGERED;
	private final static Color COLORPLAYER2 = Color.YELLOWGREEN;
	private final static Color COLORNEUTRAL = Color.WHITE;
	
	private Collection<Sprite> planets = new ArrayList<Sprite>();

	public static String getRessourcePathByName(String name) {
		return Game.class.getResource('/' + name).toString();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void changeSpeed(Sprite pinapple) {
		int max = 5;
		pinapple.setSpeed(max * Math.random() - max / 2, max * Math.random() - max / 2);
	}
	
	private void createPlanets(){
		Random random = new Random();
		int initialShips = (int)(Math.random()*200 + 100);
		
		Sprite planetP1 = new Planet(Math.random() * 100 + 40, WIDTH, HEIGHT, COLORPLAYER1, Owner.PLAYER1, initialShips);
		planetP1.setPosition(random.nextInt((int) (WIDTH-planetP1.getWidth()-120)) + 60, random.nextInt((int) (HEIGHT-planetP1.getHeight()-120)) +60);
		planets.add(planetP1);
		
		Sprite planetP2 = new Planet(Math.random() * 100 + 40,WIDTH, HEIGHT, COLORPLAYER2, Owner.PLAYER2, initialShips);
		planetP2.setPosition(random.nextInt((int) (WIDTH-planetP2.getWidth()-120)) + 60, random.nextInt((int) (HEIGHT-planetP2.getHeight()-120)) +60);
		while(planetP2.intersectsWithMargin(planetP1)){
			planetP2.setPosition(random.nextInt((int) (WIDTH-planetP2.getWidth()-120)) + 60, random.nextInt((int) (HEIGHT-planetP2.getHeight()-120)) +60);
		}
		planets.add(planetP2);
		
		for(int i = 0; i < 10; i++){
			initialShips = (int)(Math.random()*200 + 50);
			Sprite planetN = new Planet(Math.random() * 100 + 40,WIDTH, HEIGHT, COLORNEUTRAL, Owner.NEUTRAL, initialShips);
			planetN.setPosition(random.nextInt((int) (WIDTH-planetN.getWidth()-120)) + 60, random.nextInt((int) (HEIGHT-planetN.getHeight()-120)) +60);

			while(planetN.intersects(planets)){
					planetN.setPosition(random.nextInt((int) (WIDTH-planetN.getWidth()-120)) + 60, random.nextInt((int) (HEIGHT-planetN.getHeight()-120)) +60);
			}
			planets.add(planetN);
		}
	}

	public void start(Stage stage) {

		stage.setTitle("Galaxy War");
		stage.setResizable(false);

		Group root = new Group();
		Scene scene = new Scene(root);
		
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		Canvas canvas2 = new Canvas(WIDTH, HEIGHT);
		Canvas canvasPlanets = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);
		root.getChildren().add(canvas2);
		root.getChildren().add(canvasPlanets);
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		final GraphicsContext gc2 = canvas2.getGraphicsContext2D();
		final GraphicsContext gcPlanets = canvasPlanets.getGraphicsContext2D();
		
		gc.setFill(COLORPLAYER1);
		gc.setStroke(COLORPLAYER1);
		String txt = "Score Player 1: " + score;
		gc.setTextAlign(TextAlignment.LEFT);
		gc.fillText(txt,  500, 36);
		gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		gc.setLineWidth(1);
		//gc.strokeText(txt,  170, 36);
		
		gc2.setFill(COLORPLAYER2);
		gc2.setStroke(COLORPLAYER2);
		txt = "Score Player 2: " + score;
		gc2.setTextAlign(TextAlignment.LEFT);
		gc2.fillText(txt, WIDTH - 120, 36);
		//gc2.strokeText(txt, WIDTH - 120, 36);
		gc2.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		gc2.setLineWidth(1);

		final Image space = new Image(getRessourcePathByName("images/space.jpg"), WIDTH, HEIGHT, false, false);

		final Sprite spaceship = new Sprite(getRessourcePathByName("images/alien.png"), 62, 36, WIDTH, HEIGHT);
		spaceship.setPosition(WIDTH / 2 - spaceship.width() / 2, HEIGHT / 2 - spaceship.height() / 2);
		
		createPlanets();
		
		stage.setScene(scene);
		stage.show();
		//stage.setFullScreen(true);

		EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				//spaceship.setSpeed(0, 0);
				//spaceship.setPosition(e.getX() - spaceship.width() / 2, e.getY() - spaceship.height() / 2);
				Point2D clickedPosition = new Point2D(e.getX(), e.getY());
				//System.out.println(clickedPosition);
				for(Sprite planet: planets){
					if(planet.isInside(clickedPosition)){
						Planet pl = (Planet) planet;
						Spaceship ship = pl.getSpaceships().get(0);
						ship.setPosition(planet.getX()+planet.getWidth()/2 - 6, planet.getY()-14);
					}
				}
			}
		};
		
		EventHandler<MouseEvent> mouseHandlerReleased = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				Point2D destinationPosition = new Point2D(e.getX(), e.getY());
				//System.out.println(destinationPosition);
				for(Sprite planet: planets){
						Planet pl = (Planet) planet;
						Spaceship ship = pl.getSpaceships().get(0);
						ship.setPosition(ship.getX() +10, ship.getY()+10);
				}
			}
		};

		//scene.setOnMouseDragged(mouseHandler);
		scene.setOnMousePressed(mouseHandler);
		scene.setOnMouseReleased(mouseHandlerReleased);

		MediaPlayer mediaPlayer = null;
		try {
			mediaPlayer = new MediaPlayer(new Media(getRessourcePathByName("sounds/Engine.mp4")));// Only format allowed
																									// in the context of
																									// the project (mp4)
		} catch (MediaException e) {
			// in case of a platform without sound capabilities
		}
		MediaPlayer mediaPlayerPffft = null;
		try {
			mediaPlayerPffft = new MediaPlayer(new Media(getRessourcePathByName("sounds/Explosion.mp4")));

		} catch (MediaException e) {
			// in case of a platform without sound capabilities
		}

		final MediaPlayer mediaPlayerFinalCopy = mediaPlayer;// final copies are needed to transmit to inner classes
		final MediaPlayer mediaPlayerBoomFinalCopy = mediaPlayerPffft;

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				spaceship.changeSpeed(e.getCode());
				if (mediaPlayerFinalCopy != null) {
					mediaPlayerFinalCopy.stop();
					mediaPlayerFinalCopy.play();
				}
			}
		});
		
		//add a spaceship to every non-neutral planet every 3 seconds
		Timer timer = new Timer();
		TimerTask task = new TimerTask()
		{
		        public void run()
		        {
		            for(Sprite p: planets){
		            	
		            	Planet pl = (Planet) p;
		            	if(pl.getOwner() != Owner.NEUTRAL){
		            		if(pl.countSpaceships() < pl.getMaxShips()){
		            			pl.addSpaceship();
		            		}
		            	}
		            }
		        }
		};
		timer.schedule(task,3000,3000);

		new AnimationTimer() {
			public void handle(long arg0) {
				gc.drawImage(space, 0, 0);

				spaceship.updatePosition();

				/*
				Iterator<Sprite> it = pinapples.iterator();
				while (it.hasNext()) {
					Sprite pinapple = it.next();
					pinapple.updatePosition();
					if (pinapple.intersects(spaceship)) {
						it.remove();
						if (mediaPlayerBoomFinalCopy != null) {
							mediaPlayerBoomFinalCopy.stop();
							mediaPlayerBoomFinalCopy.play();
						}
						score += 100;
					} else {
						pinapple.render(gc);
						if (Math.random() > 0.995) {
							changeSpeed(pinapple);
						}
					}
				}
				 */
				
				//render planets
				for(Sprite planet: planets){
					planet.render(gcPlanets);
				}
				
				//render spaceships
				for(Sprite planet: planets){
					if(planet.getOwner() != Owner.NEUTRAL){
						Planet pl = (Planet) planet;
						for(Sprite ship: pl.getSpaceships()){
							ship.render(gcPlanets);
						}						
					}
				}			
				
			}
				
		}.start();
	}
}
