package fahrenheitCelisius;

public class Temperatura {

	public static double aFahrenheit(double celsius) {
		return (celsius * 1.8)+ 32;
		
	}
	public static double aCelsius(double fahrenheit) {
		return (fahrenheit - 32)/1.8;
		
	}
}
