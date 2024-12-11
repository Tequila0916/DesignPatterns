package org.whisky.mediator_pattern;

/**
 * @ClassName MediatorStructure
 * @Description TODO
 * @Author GT-R
 * @Date 2024/9/2517:34
 * @Version 1.0
 */
public class MediatorStructure extends Mediator{
    private  HouseOwner houseOwner;
    private  Tenant tenant;

    public HouseOwner getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(HouseOwner houseOwner) {
        this.houseOwner = houseOwner;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    @Override
    public void contact(String message, Person person) {
        if(person == houseOwner){  //如果是房主,则租房者获得信息
            tenant.getMessage(message);
        }else { //如果是租房者则获取房主信息
            houseOwner.getMessage(message);
        }
    }
}
