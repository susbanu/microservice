package creational.facade.design.pattern;

public class OracleHelper {

	public static String getDBConnection() {
		System.out.println("Oracle DB connection");
		return "Oracle DB connection";
	}
	
	public String generateOraclePdfReport(String tableName, String connection) {
		System.out.println("Oracle Pdf report");
		return "Oracle Pdf report";
	}
	
	public String generateOracleHtmlReport(String tableName, String connection) {
		System.out.println("Oracle HTML Report");
		return "Oracle HTML report";
	}
}
