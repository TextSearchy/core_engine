package com.mmcoe.core;

import com.mmcoe.core.utils.StringFinder;

public class Engine {

	public static void main(String[] args) {
		
		StringFinder SF = new StringFinder();
		for(int i=1;i<args.length;i++) {
		System.out.println("\nFile: "+args[i]);
		SF.findString(args[0],args[i]);
		}
			
	}

}
