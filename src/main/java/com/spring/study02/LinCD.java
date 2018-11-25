package com.spring.study02;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("lincd")
//@SongNamer
//@MusicNamer
public class LinCD implements CD{

	public void sing() {
		System.out.println("青花瓷");
	}

}
