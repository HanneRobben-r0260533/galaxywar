package sprites;

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
	private int maxShips;
	

	public Planet(double width, double maxX, double maxY, Color color, Owner owner, int initialShips) {
		super(width, maxX, maxY, color, owner);
		setCenter(new Point2D(x+(width/2), (y+height/2)));
		setRadius(width/2);
		populatePlanet(initialShips);
		setMaxShips(500);
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
	
	
	
	public void addSpaceship(){
		Spaceship ship = new Spaceship(12, 12, maxX, maxY, color, owner);
		ship.setPosition(center.getX(), center.getY());
		spaceships.add(ship);
	}
	
	public void sendSpaceship(Spaceship spaceship){
		spaceships.remove(spaceship);
	}
	
	public void populatePlanet(int initialShips){
		for(int i = 0; i< initialShips; i++){
			addSpaceship();
		}
	}
	
	/*
	//@Override
	public boolean isInterior(Point2D p) {
		double distance = p.distance(center);
		return distance <= radius;
	}
	*/
	
	public int countSpaceships(){
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

	public ArrayList<Spaceship> getSpaceships() {
		return spaceships;
	}

	public void setSpaceships(ArrayList<Spaceship> spaceships) {
		this.spaceships = spaceships;
	}

	public int getMaxShips() {
		return maxShips;
	}

	public void setMaxShips(int maxShips) {
		this.maxShips = maxShips;
	}
	
	
	
	
}
