package com.chatbot.domain.exception;

import java.util.UUID;

public class ConversationClosedException extends RuntimeException {

	public ConversationClosedException(UUID conversationId) {
		super("Conversation " + conversationId + " is closed");
	}
}
