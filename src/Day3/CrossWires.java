package Day3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CrossWires {
    final String regex = "\\D";
    List<String> wayA;
    List<String> wayB;
    List<Point> listA;
    List<Point> listB;

    public static void main(String[] args) throws FileNotFoundException {
        List<String> listA = fileReader("src/Day3/listA");
        List<String> listB = fileReader("src/Day3/listB");
        CrossWires crossWires = new CrossWires(listA, listB);
        System.out.println(crossWires.getResult());
    }

    static List<String> fileReader(String filePath) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String currentLine = "";
        try {
            currentLine = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList(currentLine.split(","));
    }

    public CrossWires(List<String> wayA, List<String> wayB) {
        this.wayA = wayA;
        this.wayB = wayB;
    }

    private String getResult() {
        List<Point> difference = this.findDifference();
        int little = 0;
        int shortest = 0;
        for (Point point : difference) {
            int manhattan = Math.abs(Math.abs(point.x) + Math.abs(point.y));
            if (manhattan != 0) {
                if (little == 0) {
                    little = manhattan;
                }
                if (manhattan < little) {
                    little = manhattan;
                }
            }
            int steps = this.listA.indexOf(point) + this.listB.indexOf(point);
            if (steps != 0) {
                if (shortest == 0) {
                    shortest = steps;
                }
                if (steps < shortest) {
                    shortest = steps;
                }
            }
        }
        return "Distanze: " + little + " Steps: " + shortest;
    }

    private List<Point> findDifference() {
        this.listA = calculateLine(this.wayA);
        this.listB = calculateLine(this.wayB);
        return listA.stream().filter(listB::contains).collect(Collectors.toList());
    }

    private List<Point> calculateLine(List<String> ways) {
        List<Point> newPointList = new ArrayList<>();
        newPointList.add(new Point(0,0));
        for (String way : ways) {
            int steps = 0;
            switch (way.substring(0, 1)) {
                case "R":
                    steps = Integer.parseInt(way.replaceAll(regex, ""));
                    for (int i = 0; i < steps; i++) {
                        newPointList.add(this.goRight(newPointList.get(newPointList.size() - 1)));
                    }
                    break;
                case "L":
                    steps = Integer.parseInt(way.replaceAll(regex, ""));
                    for (int i = 0; i < steps; i++) {
                        newPointList.add(this.goLeft(newPointList.get(newPointList.size() - 1)));
                    }
                    break;
                case "U":
                    steps = Integer.parseInt(way.replaceAll(regex, ""));
                    for (int i = 0; i < steps; i++) {
                        newPointList.add(this.goUp(newPointList.get(newPointList.size() - 1)));
                    }
                    break;
                case "D":
                    steps = Integer.parseInt(way.replaceAll(regex, ""));
                    for (int i = 0; i < steps; i++) {
                        newPointList.add(this.goDown(newPointList.get(newPointList.size() - 1)));
                    }
                    break;
            }
        }
        return newPointList;
    }

    private Point goUp(Point lastPoint) {
        return new Point(lastPoint.x, lastPoint.y + 1);
    }

    private Point goDown(Point lastPoint) {
        return new Point(lastPoint.x, lastPoint.y - 1);
    }

    private Point goLeft(Point lastPoint) {
        return new Point(lastPoint.x - 1, lastPoint.y);
    }

    private Point goRight(Point lastPoint) {
        return new Point(lastPoint.x + 1, lastPoint.y);
    }
}
