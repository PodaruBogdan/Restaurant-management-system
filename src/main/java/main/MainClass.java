package main;

import businessLayer.Restaurant;
import presentationLayer.MainFrame;


public class MainClass {
    public static void main(String[] args){
        Restaurant restaurant=new Restaurant();
        MainFrame frame=new MainFrame(restaurant);
        restaurant.addObserver(frame.getGui());
    }
}
