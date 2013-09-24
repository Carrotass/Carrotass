package com.carrotass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainMenu {
	
	public static int EXIT = 0;
	
	private String header;
	
	private ArrayList<String> actions;
	
	public MainMenu(String header, ArrayList<String> variants) {
		this.header = header;
		this.actions = variants;
	}
	
	public void ShowMenu() {
		writeLine(header);
		for (String variant : actions) {
			writeLine(variant);
		}
	}
	
	public int GetAnswer() {
		int result = EXIT;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			writeLine(String.format("[%d-%d]: ", 0, actions.size()-1));
			try {
				result = Integer.parseInt(br.readLine());
			} catch (NumberFormatException nfe) {
				writeLine("Invalid Format!");
			}
        } catch (IOException e) {
        	writeLine("Read error!");
        }
		return result;
	}
	
	private void writeLine(String s) {
		System.out.println(s);
	}
}
