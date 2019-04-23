package com.spring.Spring_02;

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
