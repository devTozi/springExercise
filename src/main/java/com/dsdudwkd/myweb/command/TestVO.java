package com.dsdudwkd.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Setter
//@Getter
//@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 멤버변수 생성자
@Data //getter, setter, toString 만들어주는 어노테이션(롬복에서 만들어줌) 
public class TestVO {
	private String name;
	private int age;
	private String addr;
	private String xxx;
}
