import java.util.*;
import java.io.*;

public class KNN {

    //reads all data from the file and creates an instance that stores its useful data (class, #features, data)
    public static ArrayList<Instance> readDataFile(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        ArrayList<Instance> data = new ArrayList<Instance>();
        while(sc.hasNextLine()){
            data.add(new Instance(10, sc.nextLine()));
        }
        return data;
    }

    //removes the instance we are going to test from the training data and returns it
    public static Instance removeTestInstance(int index, ArrayList<Instance> data){
        Instance test_instance = data.get(index);
        data.remove(index);
        return test_instance;
    }

    //returns the classification based on the nearest neighbor to the input instance
    public static double nearest_neighbor_classifier(Instance test_instance, ArrayList<Integer> features, ArrayList<Instance> data){
        int closestIndex;
        double minDist;
        //do first instance to initialize minDist
        minDist = data.get(0).getInstanceDistance(test_instance, features);
        closestIndex = 0;
        System.out.print("0: ");
        System.out.println(minDist);
        for (int i = 1; i < data.size(); i++){
            double nextDist = data.get(i).getInstanceDistance(test_instance, features);
            if (minDist > nextDist){
                minDist = nextDist;
                closestIndex = i;
            }
//            System.out.print(i);
//            System.out.print(": ");
//            System.out.println(nextDist);
        }
//        System.out.print("Nearest neighbor is index: ");
//        System.out.println(closestIndex);

        //returns class of closest Instance
        return data.get(closestIndex).getInstanceClass();
    }

    public static double percentCorrect(ArrayList<Integer> features, ArrayList<Instance> data){
        double correct = 0;
        for (int i = 0; i < data.size(); i++){
            Instance test = removeTestInstance(i, data);
            if (test.getInstanceClass() == nearest_neighbor_classifier(test, features, data)){
                correct++;
            }
            data.add(i, test); //adds the test instance back into its original position
        }
        return (correct / data.size())*100;
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Instance> data = readDataFile("C:\\Users\\Ryan\\Desktop\\cs_170_small80.txt");

//        for (int i = 0; i < data.size(); i++){
//            data.get(i).printInstance();
//        }



        //all features
        ArrayList<Integer> features = new ArrayList<>();
//        for (int i = 0; i < 10; i++){
//            features.add(i);
//        }

        features.add(3 - 1);
        features.add(5 - 1);
        features.add(7 - 1);

        double percent = percentCorrect(features, data);
        System.out.print("Percent Correct with all features: ");
        System.out.println(percent);


    }




}
