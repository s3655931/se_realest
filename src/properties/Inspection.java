package properties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Inspection {

	//id = propertyId + employeeId (eg. P100E100)
	private String id;
	private String employeeId;
	private Date dateTime;
	
	public Inspection(String id, String employeeId, String inputTime) {
		this.id = id;
		this.employeeId = employeeId;
		try {
			dateTime = new SimpleDateFormat("EEE, MMM d, yyyy").parse(inputTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String getTime() {
		return new SimpleDateFormat("EEE, MMM d, yyyy").format(dateTime);
	}
		
	
}
