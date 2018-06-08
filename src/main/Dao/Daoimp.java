import java.io.StringReader;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Daoimp implements IDao {
    public void intoshujuk(Connection connection, List list) {
        String sql =CONFIG.bossjianliSql;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0;i<list.size();i++){

                ps.setString(1,((PeopleBean)(list.get(i))).getName());
                ps.setInt(2,((PeopleBean)(list.get(i))).getAge());
                ps.setString(3,((PeopleBean)(list.get(i))).getSex());
                ps.setString(4,((PeopleBean)(list.get(i))).getEmail());
                ps.setString(5,((PeopleBean)(list.get(i))).getPhone());
                ps.setString(6,((PeopleBean)(list.get(i))).getHangye());
                ps.setString(7,((PeopleBean)(list.get(i))).getHopemoney());
                ps.setInt(8,((PeopleBean)(list.get(i))).getGongzuoage());
                ps.setString(9,((PeopleBean)(list.get(i))).getYouxiangjieshoushijian());
                ps.setString(10,null);
                ps.setString(11,((PeopleBean)(list.get(i))).getCunfangweizhi());
                ps.setString(12,((PeopleBean)(list.get(i))).getJianlidaxiao());
                ps.setString(13,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                ps.addBatch();
            }
            ps.executeBatch();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
