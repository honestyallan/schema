package cn.slimsmart.kafka.demo.region;

/**
 * Created by admin on 2017/4/12.
 */
public interface Accounts {

}

class WaterAccounts implements Accounts {

}

class ElectricityAccounts implements Accounts {

}
//客户端
 class test {

    public static void main(String[] args) {

        WaterAccounts accsWater = new WaterAccounts();
        ElectricityAccounts accsElectricity = new ElectricityAccounts();
        acceptAcounts(accsWater);
        acceptAcounts(accsWater);
        acceptAcounts(accsElectricity);


        Class<?> parent = java.io.InputStream.class;
        Class<?> child = java.io.FileInputStream.class;
        System.out.println(parent.isAssignableFrom(child) );

    }

    // 第一种使用instanceof
    public static void acceptAcounts(Accounts accs) {

        if (accs instanceof WaterAccounts) {

            System.out.println("收水费");

        } else if (accs instanceof ElectricityAccounts) {

            System.out.println("收电费");
        }

    }

    // 第二种使用多态
    public static void acceptAcounts(WaterAccounts accs) {

        System.out.println("收水费");

    }

    public static void acceptAcounts(ElectricityAccounts accs) {

        System.out.println("收电费");

    }

}
