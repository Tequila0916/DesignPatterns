package whisky.class03;

import org.junit.Test;
import org.whisky.bridge_pattern.*;
import org.whisky.factory_pattern.scheme02.entity.AwardInfo;
import org.whisky.factory_pattern.scheme02.entity.ResponseResult;
import org.whisky.factory_pattern.scheme03.controller.DeliverController;
import org.whisky.prototype_pattern.shallow_clone.ConcretePrototype;
import org.whisky.prototype_pattern.shallow_clone.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

/**
 * @ClassName TestApi03
 * @Description TODO
 * @Author GT-R
 * @Date 2024/6/26上午11:00
 * @Version 1.0
 */
public class TestApi03 {
    DeliverController deliverController = new DeliverController();

    @Test
    public void test() {
        AwardInfo info = new AwardInfo();
        info.setUid("1001");
        info.setAwardType(1);
        info.setAwardNumber("DEL12345");
        ResponseResult responseResult = deliverController.awardToUser(info);
        System.out.println(responseResult);
    }

    @Test
    public void test01() throws CloneNotSupportedException {
        ConcretePrototype c1 = new ConcretePrototype();
        ConcretePrototype c2 = c1.clone();

        System.out.println("对象c1和c2是同一个对象？" + (c1 == c2));
    }


    @Test
    public void test02() throws CloneNotSupportedException {
        ConcretePrototype c1 = new ConcretePrototype();
        Person p1 = new Person("凡哥");
        c1.setPerson(p1);

        //复制c1
        ConcretePrototype c2 = c1.clone();
        //获取复制对象c2中的Person对象
        Person p2 = c2.getPerson();
        p2.setName("峰哥");

        //判断p1与p2是否是同一对象
        System.out.println("p1和p2是同一个对象？" + (p1 == p2));

        c1.show();
        c2.show();
    }


    @Test
    public void test03() throws Exception {

        ConcretePrototype c1 = new ConcretePrototype();
        Person p1 = new Person("峰哥");
        c1.setPerson(p1);

        //创建对象序列化输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("c.txt"));

        //将c1对象写到文件中
        oos.writeObject(c1);
        oos.close();

        //创建对象序列化输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("c.txt"));

        //读取对象
        ConcretePrototype c2 = (ConcretePrototype) ois.readObject();
        Person p2 = c2.getPerson();
        p2.setName("凡哥");

        //判断p1与p2是否是同一个对象
        System.out.println("p1和p2是同一个对象?" + (p1 == p2));

        c1.show();
        c2.show();
    }
    @Test
    public void test04() {
        System.out.println("测试场景1: 微信支付、人脸方式.");
        Pay wxpay = new WxPay(new PayFaceMode());
        wxpay.transfer("wx_00100100","10001900",new BigDecimal(100));

        System.out.println();

        System.out.println("测试场景2: 支付宝支付、指纹方式");
        Pay zfbPay = new ZfbPay(new PayFingerPrintMode());
        zfbPay.transfer("jlu1234567","567689999999",new BigDecimal(200));
    }
}
