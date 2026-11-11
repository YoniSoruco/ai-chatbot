package com.chatbot.domain.model;

import com.chatbot.domain.exception.ConversationClosedException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Conversation {

	private final UUID id;
	private final Instant createdAt;
	private final List<Message> messages = new ArrayList<>();
	private ConversationStatus status;

	public Conversation() {
		this.id = UUID.randomUUID();
		this.createdAt = Instant.now();
		this.status = ConversationStatus.ACTIVE;
	}

	public Message addMessage(MessageRole role, String content) {
		if (status == ConversationStatus.CLOSED) {
			throw new ConversationClosedException(id);
		}
		Message message = Message.of(role, content);
		messages.add(message);
		return message;
	}

	public void close() {
		this.status = ConversationStatus.CLOSED;
	}

	public UUID getId() {
		return id;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public ConversationStatus getStatus() {
		return status;
	}

	public List<Message> getMessages() {
		return Collections.unmodifiableList(messages);
	}
}
