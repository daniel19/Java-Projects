public class CarBuilder{
    private CarItem item;

    public CarBuilder()
    {
        item = null;
    }
    public boolean buildModel(char modelChar) { //creates the CarModel if the input is okay, else does nothing and returns false
        //check modelChar if it is c then set item to Civic class
        //System.out.println("Building Model: " + modelChar + " / " + hasModel());
        switch (modelChar){
            case 'F':
                if(!hasModel()) {
                    item = new Fit();
                    return true;
                }
                return false;
            case 'A':
                if(!hasModel()) {
                    item = new Accord();
                    return true;
                }
                return false;
            case 'C':
                if(!hasModel()) {
                    item = new Civic();
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
    public boolean buildColor(char colorChar) { //creates a CarColor and adds it to the order if the input is okay
        //System.out.println("Building Color: " + colorChar + " / " + hasModel());
        if(!hasModel()){
            return false;
        }else {
            switch (colorChar) {
                case 'B':
                    if(hasColor())
                        item = new Black((CarColor) item);
                    else
                        item = new Black((CarModel) item);
                    return true;
                case 'S':
                    if(hasColor())
                        item = new Silver((CarColor) item);
                    else
                        item = new Silver((CarModel) item);
                    return true;
                case 'W':
                    if(hasColor() && item instanceof CarColor)
                        item = new White((CarColor) item);
                    else
                        item = new White((CarModel) item);
                    return true;
                default:
                    return false;
            }
        }
    }

    //TODO: Check instance of item to see if we should construct color or item.
    public boolean buildOption(char optionChar) { //creates a CarOption and adds it to the order if the input is okay
        if(!hasModel()){
            return false;
        }else {
            CarItem prevItem = item;
            switch (optionChar){
                case 'S':
                    if(item instanceof CarColor){
                        item = new Spoiler((CarColor) item);
                    }else if(item instanceof CarOption){
                        item = new Spoiler((CarOption) item);
                    }
                    if(isDuplicate((CarOption)item)){
                        System.out.println("Duplicate found");
                        item = prevItem;
                        //return false;
                    }

                    return true;
                case 'G':
                    if(item instanceof CarColor){
                        item = new GPS((CarColor) item);
                    }else if(item instanceof CarOption){
                        item = new GPS((CarOption) item);
                    }
                    if(isDuplicate((CarOption)item)){
                        item = prevItem;
                    }
                    return true;
                case 'A':
                    if(item instanceof CarColor){
                        item = new AlloyWheels((CarColor) item);
                    }else if(item instanceof CarOption){
                        item = new AlloyWheels((CarOption) item);
                    }
                    if(isDuplicate((CarOption)item)){
                        item = prevItem;
                    }
                    return true;
                case 'F':
                    if(item instanceof CarColor){
                        item = new FloorMats((CarColor) item);
                    }else if(item instanceof CarOption){
                        item = new FloorMats((CarOption) item);
                    }
                    if(isDuplicate((CarOption)item)){
                        item = prevItem;
                    }
                    return true;
                default:
                    return false;
            }
        }

    }


    public CarItem orderCar() { //returns the finished car or null if the order is incomplete
        return item;
    }

    private boolean hasModel(){
        return item != null;
    }

    private boolean hasColor(){
        if (item instanceof CarColor){
            return true;
        }else{
            return false;
        }
    }

    private boolean hasOption(){
        if (item instanceof CarOption)
            return true;
        else
            return false;
    }

    private boolean isDuplicate(CarOption option){
        return item.isDuplicate(option);
    }
}
