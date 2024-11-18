package mountain;

import java.util.HashMap;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class MountainFractal extends Fractal{
    //Corners of first triangle
    Point a, b, c;
    //First deveation
    int deveation;
    //HashMap to store sides
    HashMap<MountainSide, Point> sideMap;

    public MountainFractal(int[] points, int deveation){
        super();
        if(points.length != 6){
            throw new IllegalArgumentException("Vector of points does not meet the requirements. Please specify 3 points");
        }
        else{
            this.a = new Point(points[0], points[1]);
            this.b = new Point(points[2], points[3]);
            this.c = new Point(points[4], points[5]);
            this.deveation = deveation;
            sideMap = new HashMap<MountainSide, Point>();
        }
    }

    /**
     * Returns the title
     * @return the title 
     */
    @Override
    public String getTitle() {
        return "Mountain fractal";
    }

    /**
     * Public draw method
     */
    @Override
    public void draw(TurtleGraphics turtle) {
        turtle.moveTo(a.getX(), a.getY());
        fractalTriangle(turtle, a, b, c, order, deveation);
    }

    /**
     * Recursive draw method to draw a given fractal triangle based on order.
     * @param turtle
     * @param a
     * @param b
     * @param c
     * @param order
     * @param deveation
     */
    private void fractalTriangle(TurtleGraphics turtle, Point a, Point b, Point c, int order, int deveation){
        if(order == 0){
            turtle.moveTo(a.getX(), a.getY());
			turtle.penDown();
			turtle.forwardTo(b.getX(), b.getY());
            turtle.forwardTo(c.getX(), c.getY());
            turtle.forwardTo(a.getX(), a.getY());
			turtle.penUp();
        }
        else{
            //Split sides into midpoints and new smaller sides
            Point ab, bc, ca;
            MountainSide sideAB = new MountainSide(a, b);
            MountainSide sideBC = new MountainSide(b, c);
            MountainSide sideCA = new MountainSide(c, a);

            //Check if midpoint exists. If it exists in HashMap, fetch it and remove. Otherwise create new and add into HashMap.
            ab = sideMap.containsKey(sideAB) ? fetchPointAndRemove(sideAB) : createPointAndAdd(sideAB);
            bc = sideMap.containsKey(sideBC) ? fetchPointAndRemove(sideBC) : createPointAndAdd(sideBC);
            ca = sideMap.containsKey(sideCA) ? fetchPointAndRemove(sideCA) : createPointAndAdd(sideCA);
            
            //Recursive step
            fractalTriangle(turtle, a, ab, ca, order-1, deveation/2);
            fractalTriangle(turtle, ab, b, bc, order-1, deveation/2);
            fractalTriangle(turtle, ca, bc, c, order-1, deveation/2);
            fractalTriangle(turtle, ab, bc, ca, order-1, deveation/2);
        }
    }

    /**
     * Creates new Point from MountainSide and adds to HashMap
     * @param side
     * @return Point midPoint
     */
    private Point createPointAndAdd(MountainSide side){
        //Creates the new Point from MountainSide
        Point midPoint = new Point((side.getP1().getX()+side.getP2().getX())/2, (int) RandomUtilities.randFunc(deveation) + (side.getP1().getY()+side.getP2().getY())/2);
        //Create MountainSide and add midpoint to map
        sideMap.put(side, midPoint);
        return midPoint;
    }

    /**
     * Fetches Point from MountainSide side and removes it from HashMap
     * @param side
     * @return Point tmp
     */
    private Point fetchPointAndRemove(MountainSide side){
        //Remove midpoint from HashMap and return
        Point tmp = sideMap.get(side);
        sideMap.remove(side);
        return tmp;
    }
}
