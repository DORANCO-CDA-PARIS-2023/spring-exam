package com.doranco.examspring.payload;

public class Payload {

    private Object content;

    public Payload() {
    	
    }

	public Payload(Object content) {
		super();
		this.content = content;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
    
    
}
