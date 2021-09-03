package creational.facade.design.pattern;

public class Main {

	/*
	 * facade encapsulates a complex subsystem behind a simple interface. It hides
	 * much of the complexity and makes the subsystem easy to use.
	 * 
	 * Also, if we need to use the complex subsystem directly, we still can do that;
	 * we aren't forced to use the facade all the time.
	 * 
	 * Facade design pattern is more like a helper for client applications, it
	 * doesn’t hide subsystem interfaces from the client. Whether to use Facade or
	 * not is completely dependent on client code. 
	 * 
	 * Facade design pattern can be applied at any point of development, 
	 * usually when the number of interfaces
	 * grow and system gets complex. 
	 * 
	 * Subsystem interfaces are not aware of Facade
	 * and they shouldn’t have any reference of the Facade interface. 
	 * 
	 * Facade design
	 * pattern should be applied for similar kind of interfaces, its purpose is to
	 * provide a single interface rather than multiple interfaces that does the
	 * similar kind of jobs.
	 */
	public static void main(String[] args) {

		String tableName = "Employee";

		// generating MySql HTML report and Oracle PDF report without using Facade
		String con = MySQLHelper.getDBConnection();
		MySQLHelper mySqlHelper = new MySQLHelper();
		mySqlHelper.generateMysqlHtmlReport(tableName, con);

		String con1 = OracleHelper.getDBConnection();
		OracleHelper oracleHelper = new OracleHelper();
		oracleHelper.generateOraclePdfReport(tableName, con1);

		// generating MySql HTML report and Oracle PDF report using Facade
		DatabaseFacade.generateReport(DatabaseFacade.DBTypes.MYSQL, DatabaseFacade.ReportTypes.HTML, tableName);
		DatabaseFacade.generateReport(DatabaseFacade.DBTypes.ORACLE, DatabaseFacade.ReportTypes.PDF, tableName);
	}
}
