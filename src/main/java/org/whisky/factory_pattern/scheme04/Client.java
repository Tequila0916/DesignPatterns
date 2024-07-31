package org.whisky.factory_pattern.scheme04;

import org.whisky.factory_pattern.scheme04.factory.AppliancesFactory;
import org.whisky.factory_pattern.scheme04.factory.HairFactory;
import org.whisky.factory_pattern.scheme04.product.AbstractFreezer;
import org.whisky.factory_pattern.scheme04.product.AbstractTV;

/**
 * @ClassName Client
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/26下午6:45
 * @Version 1.0
 */
public class Client {
    private AbstractTV tv;
    private AbstractFreezer freezer;

    public Client(AppliancesFactory factory) {
        this.tv = factory.createTV();
        this.freezer = factory.createFreezer();
    }

    public AbstractTV getTv() {
        return tv;
    }

    public void setTv(AbstractTV tv) {
        this.tv = tv;
    }

    public AbstractFreezer getFreezer() {
        return freezer;
    }

    public void setFreezer(AbstractFreezer freezer) {
        this.freezer = freezer;
    }

    public static void main(String[] args) {
        Client client = new Client(new HairFactory());
        AbstractTV tv = client.getTv();
        System.out.println(tv);
        AbstractFreezer freezer = client.getFreezer();
        System.out.println(freezer);
    }
}
