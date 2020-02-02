package Conn;

import java.sql.*;

public class Conn{
	public Connection getconnecttion() {
		Connection temp = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("加载驱动完成!");
			temp=DriverManager.getConnection("jdbc:mysql://localhost:3306/student",
					"root","wayhdzzzqhk");
			System.out.println("数据库连接成功!");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	public static void main(String arg0[]) {
		Conn Con=new Conn();
		Connection con;
		con=Con.getconnecttion();
		ResultSet res;
		try {
			Statement sql;
			PreparedStatement pre_sql;
			sql=con.createStatement();
			res=sql.executeQuery("select * from student_info");
			System.out.println("原数据库数据:");
			while(res.next()) {
				String id,name;
				id=res.getString("id");
				name=res.getString("stuname");
				System.out.println("学号:"+id+"\t姓名:"+name);
			}
			

			//以下为插入操作：
			System.out.println("\n插入后数据:");
			pre_sql=con.prepareStatement("insert into student_info"+
										" values(?,?)");
			pre_sql.setInt(1,5);
			pre_sql.setString(2, "王五");
			pre_sql.executeUpdate();	//executeUpdate用于除了查询以外，如：插入、删除、更新操作
			//而executeQuery用于查询
			pre_sql=con.prepareStatement("select * from student_info");
			res=pre_sql.executeQuery();
			while(res.next()) {
				String id,name;
				id=res.getString("id");
				name=res.getString("stuname");
				System.out.println("学号:"+id+"\t姓名:"+name);
			}

			
			//以下为修改操作
			System.out.println("\n更新后数据:");
			pre_sql=con.prepareStatement("update student_info set stuname=? where id=?");
			pre_sql.setString(1,"老五");
			pre_sql.setInt(2,5);
			pre_sql.executeUpdate();	//executeUpdate用于除了查询以外，如：插入、删除、更新操作
			//而executeQuery用于查询
			pre_sql=con.prepareStatement("select * from student_info");
			res=pre_sql.executeQuery();
			while(res.next()) {
				String id,name;
				id=res.getString("id");
				name=res.getString("stuname");
				System.out.println("学号:"+id+"\t姓名:"+name);
			}
			
			//以下为删除操作
			System.out.println("\n删除后数据:");
			pre_sql=con.prepareStatement("delete from student_info where id=?");
			pre_sql.setInt(1,5);
			pre_sql.executeUpdate();	//executeUpdate用于除了查询以外，如：插入、删除、更新操作
			//而executeQuery用于查询
			pre_sql=con.prepareStatement("select * from student_info");
			res=pre_sql.executeQuery();
			while(res.next()) {
				String id,name;
				id=res.getString("id");
				name=res.getString("stuname");
				System.out.println("学号:"+id+"\t姓名:"+name);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

/**
public class Conn {

	static Connection con;
	static PreparedStatement sql;
	static ResultSet res;
	public Connection getConnetion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");	//加载驱动类
			System.out.println("数据库驱动加载成功！");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		try {	//访问数据库url，获取数据库连接对象
			con=DriverManager.getConnection("jdbc:mysql:"+"//localhost:3306/student","root","wayhdzzzqhk");
			System.out.println("数据库连接成功！");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String[] args) {
		Conn c=new Conn();	//创建本类对象
		c.getConnetion();	//获取与数据库的连接
		try {
			//sql=con.createStatement();	//实例化statement对象
			//执行sql语句
			//res=sql.executeQuery("select * from student_info");
			
			//实例化预处理对象
			sql=con.prepareStatement("select * from student_info where id=?");
			//设置参数
			sql.setInt(1,1);
			res=sql.executeQuery();	//执行预处理语句
			while(res.next()) {
				String name=res.getString("stuname");
				String id=res.getString("id");
				System.out.print("学号:"+id);
				System.out.println("\t名字:"+name);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
**/