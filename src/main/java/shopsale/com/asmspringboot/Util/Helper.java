package shopsale.com.asmspringboot.Util;

import java.util.Date;

public class Helper {
	public static Date DateSQLToDateJava(java.sql.Date sqlDate) {
		return new Date(sqlDate.getTime());
	}
}
