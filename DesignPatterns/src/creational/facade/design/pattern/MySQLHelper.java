package creational.facade.design.pattern;

public class MySQLHelper {

	public static String getDBConnection() {
		System.out.println("MySQL DB connection");
		return "MySQL DB connection";
	}
	
	public String generateMysqlPdfReport(String tableName, String connection) {
		System.out.println("MySQL Pdf report");
		return "MySQL Pdf report";
	}
	
	public String generateMysqlHtmlReport(String tableName, String connection) {
		System.out.println("MySQL HTML Report");
		return "MySQL HTML Report";
	}
}
