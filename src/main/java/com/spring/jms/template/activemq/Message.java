package com.spring.jms.template.activemq;

import java.util.Date;

public class Message {
	private long id;
	private String content;
	private Date date;
	
	public Message() {
		super();
	    }
	
	public Message(long id, String content, Date date) {
		super();
		this.id = id;
		this.content = content;
		this.date = date;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + ", date=" + date + "]";
	}
	
	

}
