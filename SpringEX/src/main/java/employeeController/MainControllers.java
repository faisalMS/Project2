package nameController;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

public class MainControllers {
    private ArrayList<String> date= new ArrayList<>();
    @GetMapping("firstName")
    public ArrayList<String> getCar(){
        return car;
    }

    @PostMapping("car")
    public String postCar(@RequestBody String carName){
        car.add(carName);
        return "User " + carName + "added to the arraylist";
    }

    @PutMapping("car")
    public String putCar(@PathVariable int index, @RequestBody String carName){
        car.set(index, carName);
        return "Index " + index + "Update to " + carName;
    }

    @DeleteMapping("car")
    public ArrayList<String> deleteCar(@PathVariable int index){
        car.remove(index);
        return car;
    }

}
