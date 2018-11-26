package sprites;

public class Point2D{
	private double x, y;

	public Point2D(double x, double y) {
		setX(x);
		setY(y);
	}

	public String toString(){
		return "Point : (" + x + ", " + y + ")\n";
	}
	
	public void print() {
		System.out.println("Point : (" + x + ", " + y + ")");
	}

	public void move(double dx, double dy) {
		setX(x+dx);
		setY(y+dy);
	}

	public double distance(Point2D p) {
		double d1 = p.x - x;
		double d2 = p.y - y;
		return Math.sqrt(d1*d1 + d2*d2);
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

	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o instanceof Point2D){
			Point2D p = (Point2D)o;
			if(x == p.x && y == p.y) return true;
		}
		return false;
	}
	
	
}
