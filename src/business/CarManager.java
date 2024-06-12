package business;

import core.Helper;
import dao.CarDao;
import entity.Car;
import entity.Model;

import java.util.ArrayList;

public class CarManager {
    private final CarDao carDao;

    public CarManager(){
        this.carDao=new CarDao();
    }
    public Car getById(int id){
        return this.carDao.getById(id);
    }

    public ArrayList<Car> findAll(){
        return this.carDao.findAll();
    }
    public ArrayList<Object[]> getForTable(int size,ArrayList<Car>cars){
        ArrayList<Object[]> carList= new ArrayList<>();
        for (Car obj : cars){
            int i=0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getModel().getBrand().getName();
            rowObject[i++] = obj.getModel().getName();
            rowObject[i++] = obj.getPlate();
            rowObject[i++] = obj.getColor();
            rowObject[i++] = obj.getKm();
            rowObject[i++] = obj.getModel().getYear();
            rowObject[i++] = obj.getModel().getType();
            rowObject[i++] = obj.getModel().getFuel();
            rowObject[i++] = obj.getModel().getGear();
            carList.add(rowObject);
        }
        return carList;
    }
    public boolean save(Car car){
        if (this.getById(car.getId())!=null){
            Helper.showMsg(car.getId()+" error");
            return false;
        }
        return this.carDao.save(car);
    }
    public boolean update(Car car){
        if (this.getById(car.getId())==null){
            Helper.showMsg(car.getId()+" ID kayıtlı araç bulunamadı");
            return false;
        }
        return this.carDao.update(car);
    }
    public boolean delete(int id){
        if (this.getById(id)==null){
            Helper.showMsg(id +" ID kayıtlı araç bulunamadı");
            return false;
        }
        return this.carDao.delete(id);
    }

    public ArrayList<Car> searchForBooking(String strt_date, String fnsh_date, Model.Type type, Model.Fuel fuel, Model.Gear gear){
        String query = "SELECT * FROM public.car as c LEFT JOIN public.model as m";

        ArrayList<String> where = new ArrayList<>();
        ArrayList<String> joinWhere = new ArrayList<>();

        joinWhere.add("c.car_model_id = m.model_id");

        if (fuel != null){
            where.add("m.model_fuel = '" + fuel.toString() + "'");
        }
        if (gear != null){
            where.add("m.model_gear = '" + gear.toString() + "'");
        }
        if (type != null){
            where.add("m.model_type = '" + type.toString() + "'");
        }

        String whereStr = String.join(" AND ", where);
        String joinStr = String.join(" AND ", joinWhere);

        if (joinStr.length()>0){
            query += " ON " + joinStr;
        }
        if (whereStr.length()>0){
            query += " WHERE "+ whereStr;
        }
        System.out.println(query);

        return new ArrayList<>();
    }
}
