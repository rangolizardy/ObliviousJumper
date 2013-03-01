package src.util;

//Refactor some of this? >,>
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//Done for now.
public class Log {
	private int logSize;
	private int logRotation;
	private Handler handler;
	private SimpleFormatter logFormatter;
	private Logger logInternal;

	private boolean isLogging; // E.g. Dont create the file unless we need to.
	public Log() {
		isLogging = false;
		logSize = 5000;
		logRotation = 1;
		logFormatter = new SimpleFormatter();
		logInternal = Logger.getLogger("");
	}

	public void setLevel(String severity) {
		logInternal.setLevel(Level.parse(severity));
		return;
	}

	// Return the current logger, It's a singleton i belive.
	public Logger getLogger() {
		if (isLogging == false) {

			// Timestamp on files.
			String date = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss").format(new Date());
			try {
				handler = new FileHandler("./logs/" + date + ".log", logSize, logRotation);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// Where is your god now??!?
				e.printStackTrace();
			}
			
			Logger.getLogger("").addHandler(handler);
			handler.setFormatter(logFormatter);
			
			//We are logging dont open another file!
			isLogging = true;
		}
		return logInternal;
	}

}
