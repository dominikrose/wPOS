//
// https://sourceforge.net/p/chromispos/discussion/uscripts/thread/549dc91d/
//
// Enables two happy hour times each day.   
// Also enables an all day happy hour based on a key in the properties file.
// Also enables Senior Citzen Tuesday on 1st Tuesday of each month
// Finally enables a specific day of the week for discount - Thirsty Thursday

import uk.chromis.pos.forms.AppConfig;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

Calendar cal = Calendar.getInstance();
int hour = cal.get(Calendar.HOUR_OF_DAY);
int day = cal.get(Calendar.DAY_OF_WEEK);
int dow = cal.get(Calendar.DAY_OF_MONTH);

DateFormat dateFormat = new SimpleDateFormat("HHmm");
int hhnow = Integer.parseInt(dateFormat.format(cal.getTime()));

discountapplied = 0;
discprice = 0;
ln = line.getProperty("product.name");

//Happy hour times
start1 = 1558;
end1 = 2202; 
//start1 = 2206;
//end1 = 2302; 


if (hhnow >= start1 && hhnow < end1) {
    hhour = 1;
} else {
    hhour = 0;
}

// Regular Happy Hour
if (hhour == 1 && discountapplied == 0)
{
    discountapplied = 1;
    discountperiod = "happy-hour";
    discprice = line.getProperty("happy-hour-price");
}

// Apply appropriate discount if any of the discount periods have been triggered
if (discountapplied > 0) {
    switch(discountperiod)
    {
        case "happy-hour" :
            np = line.getProperty("happy-hour-price");
            if (np != null) {
              Double newPrice = new Double(np);
              line.setPrice(newPrice);
              line.setProperty("product.name", ln  + " (HH)");
            }
            break;
        default :
            break;
    }
}