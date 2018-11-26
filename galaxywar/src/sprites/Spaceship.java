package sprites;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Spaceship extends Sprite {

	private int type;
	private int attackPower;
	private int productionTime;
	private Point2D source, destination;
	
	public Spaceship(double width, double height, double maxX, double maxY, Color color, Owner owner) {
		super(width, height, maxX, maxY, color, owner);
	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(x,y,width,height);
		
		/*
		Polygon polygon = new Polygon();
		polygon.getPoints().addAll(new Double[]{
		    0.0, 0.0,
		    20.0, 10.0,
		    10.0, 20.0 });
		
		gc.fillPolygon(polygon.getPoints().get(0), polygon.getPoints().get(1), polygon.getPoints().get(2));
		*/
		
	}

}
