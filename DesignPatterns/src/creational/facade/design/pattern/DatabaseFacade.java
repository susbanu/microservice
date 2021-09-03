package creational.facade.design.pattern;

public class DatabaseFacade {

	public enum DBTypes {
		MYSQL, ORACLE;
	}

	public enum ReportTypes {
		HTML, PDF;
	}

	public static void generateReport(DBTypes dbType, ReportTypes reportType, String tableName) {
		String connection = null;
		switch (dbType) {
		case MYSQL:
			connection = MySQLHelper.getDBConnection();
			MySQLHelper mySqlHelper = new MySQLHelper();
			switch (reportType) {
			case HTML:
				mySqlHelper.generateMysqlHtmlReport(tableName, connection);
				break;
			case PDF:
				mySqlHelper.generateMysqlPdfReport(tableName, connection);
				break;
			}
			break;
		case ORACLE:
			connection = OracleHelper.getDBConnection();
			OracleHelper oracleHelper = new OracleHelper();
			switch (reportType) {
			case HTML:
				oracleHelper.generateOracleHtmlReport(tableName, connection);
				break;
			case PDF:
				oracleHelper.generateOraclePdfReport(tableName, connection);
				break;
			}
			break;
		}
	}

}
