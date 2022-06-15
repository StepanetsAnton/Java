package Lesson6;

public class PlanetMassCalculator {
    public Double calc(PlanetData planet){

        double volume = 4 * 3.1415926535 * planet.radius * planet.radius * planet.radius / 3;
        Class<?> c = planet.getClass();
        Planet pl = c.getAnnotation(Planet.class);

        return volume * pl.density();
    }

    public static void main(String[] args){
        PlanetData earth = new PlanetData();
        earth.radius = (double)6371;
        PlanetMassCalculator calculator = new PlanetMassCalculator();
        Integer mass = calculator.calc(earth).intValue();
        System.out.println("Mass of the provided planet is "+mass+" kg.");
    }
}