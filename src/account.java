import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
public class account implements Serializable {
    private static int objectCount = 0; // will use to create the account number
    private String name;
    private String job;
    private Date dateofc;
    private Date dateofb;
    private int accnum;
    private String pnum;
    private String pass;
    double balance = 0;
    public account(){}; // for serialization
    public account(String name, String job, Date dateofc, Date dateofb,String pass,String pnum)
    {
        this.pnum = pnum;
        this.name = name;
        this.job = job;
        this.dateofc = dateofc;
        this.dateofb = dateofb;
        this.pass = pass;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.dateofc);
        objectCount++;
        accnum = (calendar.get(Calendar.YEAR)*1000) + (calendar.get(Calendar.MONTH)* 10) + calendar.get(Calendar.DAY_OF_MONTH)+ objectCount;
    }
    void setpnum(String pnum)
    {
        this.pnum = pnum;
    }
    String getPnum()
    {
        return this.pnum;
    }
    void setName(String name)
    {
        this.name = name;
    }
    void setJob(String job)
    {
        this.job = job;
    }
    String getName()
    {
        return name;
    }
    String getJob()
    {
        return job;
    }
    Date getDateofb()
    {
        return dateofb;
    }
    Date getDateofc()
    {
        return dateofc;
    }
    int getAccnum()
    {
        return accnum;
    }
    double getBalance()
    {
        return balance;
    }
    void changeinBalance(double number)
    {
        balance = balance + number;
    }
    void SetBalance(double num)
    {
        balance = num;
    }
    // checks if the user matches the account number and password.
    boolean matches(int accn, String pa)
    {
        if(pass.equals(pa) && accnum == accn)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    // we need these methods since static attributes are not serializable.
    static int getObjectCount()
    {
        return objectCount;
    }
    static void setObjectCount(int num)
    {
        objectCount = num;
    }
}
