package com.example.content.Beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyResponse<T> {

	public T data;

	public String message;

	public boolean status;

}
