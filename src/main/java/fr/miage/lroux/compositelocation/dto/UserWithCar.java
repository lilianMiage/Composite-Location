package fr.miage.lroux.compositelocation.dto;

public class UserWithCar extends User{

    private Car car;
    public UserWithCar(User user, Car car) {
        super(user.getUserId(), user.getLastName(), user.getFirstName(), user.getAccessCardId());
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
