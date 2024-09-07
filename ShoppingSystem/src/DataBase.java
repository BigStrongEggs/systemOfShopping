import java.util.*;

public class DataBase {
    public static Map<Integer,String> managerInfo = new HashMap<>();
    public static Map<Integer,String> customerPassWordInfo= new HashMap<>();
    public static List<Customer> customerInfo=new LinkedList<>();
    public static List<Goods> goodsInfo=new LinkedList<>();
    public static void addManagerInfo(int account,String passWord){
        managerInfo.put(account,passWord);
    }
    public static void addCustomerPassWordInfo(@org.jetbrains.annotations.NotNull Customer customer){
        customerPassWordInfo.put(customer.getAccount(),customer.getMyPassWord());
    }
    public static void modifyCustomerPassWord(Customer customer,String passWord){
        for (Customer temp : customerInfo) {
            if (temp.getCustomerId() == customer.getCustomerId()) {
                int count=customerInfo.indexOf(customer);
                customer.setMyPassWord(passWord);
                customerInfo.set(count,customer);
            }
        }
    }
    public static void updateCustomersCart(Customer customer){
        int count=0;
        for(Customer temp: DataBase.customerInfo){
            if(temp.getCustomerId()==customer.getCustomerId()){
                break;
            }
            count++;
        }
        DataBase.customerInfo.set(count,customer);
    }
    public static void addCustomerInfo(Customer customer){
        customerInfo.add(customer);
    }
    public static void addGoodsInfo(Goods goods){
        goodsInfo.add(goods);
    }
    public static void modifyGoodsNums(int goodsId,int nums){
        for(Goods temp:goodsInfo){
            if(temp.getGoodsID()==goodsId){
                if(temp.getQuantity()-nums<=0){
                    goodsInfo.remove(temp);
                    return;
                }
                int count=goodsInfo.indexOf(temp);
                temp.setQuantity(temp.getQuantity()-nums);
                goodsInfo.set(count,temp);
            }
        }
    }
}
