/**
 * 
 */
package com.talbots.customer.feed.batch.service;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

/**
 * @author sr4mxl
 *
 */
public class ItemCountListener implements ChunkListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerFeedProcessor.class);

	@Override
	public void beforeChunk(ChunkContext context) {

	}

	@Override
	public void afterChunk(ChunkContext context) {
		int count = context.getStepContext().getStepExecution().getReadCount();

		if (LOGGER.isInfoEnabled()) {

			String message = MessageFormat.format("Processed customer record count: {0}", count);

			LOGGER.info(message);

		}

	}

	@Override
	public void afterChunkError(ChunkContext context) {

	}

}
