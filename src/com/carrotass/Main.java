package com.carrotass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.carrotass.preferences.JiraLoginPreferences;

public class Main {

	public static void main(String[] args) {
		try {
			ArrayList<String> menuActions = GetMenuActions();
			MainMenu menu = new MainMenu("Enter action number:", menuActions);
			
			boolean needExit = false;
			while (!needExit) {
				menu.ShowMenu();
				int answer = menu.GetAnswer();
				if (answer != MainMenu.EXIT) {
					switch (answer) {
					case 1:
						SayHello();
						break;
					case 2:
						EnterJiraCredentials();
						break;
					case 3:
						ShowJiraCredentials();
						break;
					default:
						System.out.println("Unrecognized option, please try again");
						break;
					}
				}
				else
				{
					needExit = true;
					System.out.println("Exiting");
				}
			}
		} catch (Exception e) {
			System.out.println(String.format("Error occured: %s. Now exiting.", e.getLocalizedMessage()));
		}
	}
	
	public static ArrayList<String> GetMenuActions() {
		ArrayList<String> menuActions = new ArrayList<>();
		menuActions.add("1. Say hello");
		menuActions.add("2. Enter Jira credentials");
		menuActions.add("3. Show Jira credentials");
		menuActions.add("0. Exit");
		return menuActions;
	}
	
	public static void SayHello() {
		System.out.println("Hello world!");
	}
	
	public static void EnterJiraCredentials() throws IOException {
		System.out.println("Enter login");
		String login = new BufferedReader(new InputStreamReader(System.in)).readLine();
		System.out.println("Enter password");
		String password = new BufferedReader(new InputStreamReader(System.in)).readLine();
		JiraLoginPreferences preferences = new JiraLoginPreferences();
		preferences.setCredentials(login, password);
	}

	private static void ShowJiraCredentials() {
		JiraLoginPreferences preferences = new JiraLoginPreferences();
		System.out.println(String.format("Login: %s, password: %s", preferences.getUsername(), preferences.getPassword()));
	}
}
