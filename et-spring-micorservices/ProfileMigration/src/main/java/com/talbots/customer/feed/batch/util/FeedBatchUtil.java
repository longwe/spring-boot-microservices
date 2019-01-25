package com.talbots.customer.feed.batch.util;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedBatchUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeedBatchUtil.class);

	private FeedBatchUtil() {
	}

	// Create a new file
	public static File createFileWithTimeStamp(String filename) {
		// get current project path
		// create a new file with Time Stamp
		File file = new File( filename + getCurrentTimeStamp().replace(":", "_").replace(".", "_") + ".txt");

		try {
			if (!file.exists()) {
				if (!file.createNewFile()) {
					LOGGER.error("Could not create file");
				}

				if (LOGGER.isInfoEnabled()) {

					String message = MessageFormat.format("File is created; file name is {0}", file.getName());

					LOGGER.info(message);

				}

			} else {

				if (LOGGER.isInfoEnabled()) {
					LOGGER.info("File already exist");
				}
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return file;
	}

	// Get current system time
	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHH:mm:ss.SSS");
		Date now = new Date();

		return sdfDate.format(now);
	}
	
	

}
