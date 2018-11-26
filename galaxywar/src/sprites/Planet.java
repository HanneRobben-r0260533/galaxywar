package sprites;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class Planet extends Sprite {
	
	private ArrayList<Spaceship> spaceships = new ArrayList<Spaceship>();
	private Point2D center;
	private double radius;

	public Planet(double width, double maxX, double maxY, Color color, Owner owner, int initialShips) {
		super(width, maxX, maxY, color, owner);
		setCenter(new Point2D(x+(width/2), (y+height/2)));
		setRadius(width/2);
		populatePlanet(initialShips);
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(x,y,width,height);
		
		gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.BLACK);
		String txt =  ""+ countSpaceships();
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText(txt, x + width/2, y+height/2+6);
		gc.strokeText(txt, x + width/2, y+height/2+6);	
	}
	
	
	
	void addSpaceship(Spaceship spaceship){
		spaceships.add(spaceship);
	}
	
	void sendSpaceship(Spaceship spaceship){
		spaceships.remove(spaceship);
	}
	
	void populatePlanet(int initialShips){
		for(int i = 0; i< initialShips; i++){
			//temporary fix
			spaceships.add(new Spaceship(0, 0, 0, 0, color, owner));
		}
	}
	
	/*
	//@Override
	public boolean isInterior(Point2D p) {
		double distance = p.distance(center);
		return distance <= radius;
	}
	*/
	
	int countSpaceships(){
		return spaceships.size();
	}

	public Point2D getCenter() {
		return center;
	}

	public void setCenter(Point2D center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	
}
