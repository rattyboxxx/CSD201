// =========== DO NOT EDIT THE GIVEN CONTENT OF THIS FILE ============

class Bike {

    String brand;
    int color, weight;

    Bike() {
    }

    Bike(String xPlace, int xDepth, int xType) {
        brand = xPlace;
        color = xDepth;
        weight = xType;
    }

    @Override
    public String toString() {
        return ("(" + brand + "," + color + "," + weight + ")");
    }
}
