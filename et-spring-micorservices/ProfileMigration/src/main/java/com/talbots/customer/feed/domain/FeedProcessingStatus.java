package com.talbots.customer.feed.domain;

public enum FeedProcessingStatus {

	PROFILE_FEED_NOT_SENT(0),
	PROFILE_PASSWORD_CHANGES(1),
    PROFILE_FEED_CREATED(2),
    CREDIT_CARD_DECRYPTED(3),
    OMNI_CONVERTED(4),
    CDREDIT_CARD_PROCESSES(5);

	private final int processStatus;

	FeedProcessingStatus(int processStatus) {
		this.processStatus = processStatus;
	}

	public int getProcessStatus() {
		return this.processStatus;
	}

}
