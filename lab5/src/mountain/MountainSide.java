package mountain;

public class MountainSide {

    private Point p1, p2;

    public MountainSide(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point[] getEndpoints(){
        return new Point[] {p1, p2};
    }

    public Point getP1(){
        return p1;
    }

    public Point getP2(){
        return p2;
    }

    @Override
    public int hashCode(){
        return p1.hashCode() + p2.hashCode();
    }


    @Override
    public boolean equals(Object object){
        if(object instanceof MountainSide){
            final MountainSide side = (MountainSide) object;
            return (this.p1.equals(side.getP1()) && this.p2.equals(side.getP2()) || 
            this.p1.equals(side.getP2()) && this.p2.equals(side.getP1()));
        }
        else
            return false;
    }
}
