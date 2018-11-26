package sprites;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Sprite {
	private Image image;
	protected double x;
	protected double y;
	protected double xSpeed;
	protected double ySpeed;
	protected double width;
	protected double height;
	protected double maxX;
	protected double maxY;
	protected Color color;
	protected Owner owner;
	private final static double MARGIN = 25;

	public Sprite(String path, double width, double height, double maxX, double maxY) {
		image = new Image(path, width, height, false, false);
		this.width = width;
		this.height = height;
		this.maxX = maxX;
		this.maxY = maxY;
		setOwner(owner.NEUTRAL);
	}
	
	public Sprite(Sprite s) {
		image = s.image;
		width = s.width;
		height = s.height;
		maxX = s.maxX;
		maxY = s.maxY;
		color = s.color;
		setOwner(owner);
	}
	
	public Sprite( double width, double height, double maxX, double maxY, Color color, Owner owner){
		this.width = width;
		this.height = height;
		this.maxX = maxX;
		this.maxY = maxY;
		setColor(color);
		setOwner(owner);
	}
	
	public Sprite( double width, double maxX, double maxY, Color color, Owner owner){
		this.width = width;
		this.height = width;
		this.maxX = maxX;
		this.maxY = maxY;
		setColor(color);
		setOwner(owner);
	}

	public double width() {
		return width;
	}

	public double height() {
		return height;
	}

	public void validatePosition() {
		if (x + width >= maxX) {
			x = maxX - width;
			xSpeed *= -1;
		} else if (x < 0) {
			x = 0;
			xSpeed *= -1;
		}

		if (y + height >= maxY) {
			y = maxY - height;
			ySpeed *= -1;
		} else if (y < 0) {
			y = 0;
			ySpeed *= -1;
		}
	}

	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
		validatePosition();
	}

	public void setSpeed(double xSpeed, double ySpeed) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	public void changeSpeed(KeyCode code) {
		switch (code) {
		case LEFT:
			xSpeed--;
			break;
		case RIGHT:
			xSpeed++;
			break;
		case UP:
			ySpeed--;
			break;
		case DOWN:
			ySpeed++;
			break;
		case SPACE:
			ySpeed = xSpeed = 0;
			break;
		default:
		}
	}

	public void updatePosition() {
		x += xSpeed;
		y += ySpeed;
		validatePosition();
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(image, x, y);
	}

	public boolean intersects(Sprite s) {
		return ((x >= s.x && x <= s.x + s.width) || (s.x >= x && s.x <= x + width))
				&& ((y >= s.y && y <= s.y + s.height) || (s.y >= y && s.y <= y + height));
	}
	
	public boolean intersectsWithMargin(Sprite s) {
		return ((x  +MARGIN >= s.x && x -MARGIN <= s.x + s.width) || (s.x >= x -MARGIN && s.x <= x + width +MARGIN))
				&& ((y +MARGIN >= s.y && y -MARGIN <= s.y + s.height) || (s.y >= y -MARGIN && s.y <= y + height +MARGIN));
	}
	
	public boolean intersects(Collection<Sprite> sprites) {
		for(Sprite s: sprites){
			if(intersectsWithMargin(s)) return true;
		}
		return false;
	}

	public String toString() {
		return "Sprite<" + x + ", " + y + ">";
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public double getySpeed() {
		return ySpeed;
	}

	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getMaxX() {
		return maxX;
	}

	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}

	public double getMaxY() {
		return maxY;
	}

	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	
	
	

}
