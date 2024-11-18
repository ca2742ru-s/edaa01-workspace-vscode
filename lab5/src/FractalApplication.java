import fractal.Fractal;
import fractal.FractalView;
import koch.Koch;
import mountain.MountainFractal;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[0] = new MountainFractal(new int[] {50, 350, 350, 100, 450, 500}, 5);
		fractals[1] = new Koch(300);
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}