import java.util.*;
import java.io.*;

public class Instance {

    private final double instance_class;
    private final int num_features;
    private final double[] data;

    public Instance(int features, String line) {
        Scanner sc = new Scanner(line);
        this.instance_class = sc.nextDouble();
        this.num_features = features;
        this.data = new double[features];

        for (int i = 0; i < features; i++){
            this.data[i] = sc.nextDouble();
        }
    }

    //Getters
    public double getInstanceClass() {
        return this.instance_class;
    }

    public int getNumFeatures() {
        return this.num_features;
    }

    public double[] getData() {
        return this.data;
    }
    //prints the class and then all the features
    public void printInstance() {
        System.out.print(this.instance_class);
        System.out.print(" ");
        for (int i = 0; i < this.num_features; i++){
            System.out.print(this.data[i]);
            System.out.print(" ");
        }
        System.out.println();
    }


    //gets distance based on array of features indexes given
    public double getInstanceDistance(Instance test, ArrayList<Integer> features){
        double distance = 0;
        for(int i = 0; i < features.size(); i++){
            //                     feature value from Training          feature value from Test
            double difference = (this.data[features.get(i)] - test.getData()[features.get(i)]);
            difference *= difference;   //square the distance for the feature
            distance = distance + difference;   //add to the total distance
        }

        //take squared sum of all distances based on selected features and square root them
        return Math.sqrt(distance);
    }

}
