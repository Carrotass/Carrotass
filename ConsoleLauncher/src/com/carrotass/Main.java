package com.carrotass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.carrotass.jira.Jira.JiraType;
import com.carrotass.jira.JiraPreferences;
import com.carrotass.jira.JiraWorker;

public class Main {
	
	private static JiraPreferences jiraPreferences = new JiraPreferences();
	
	private static JiraWorker jiraWorker = new JiraWorker();

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
					case 4:
						EneterJiraPaths();
						break;
					case 5:
						ShowJiraPaths();
						break;
					case 6:
						LoginToJira();
						break;
					case 7:
						GetIssuesList();
						break;
					default:
						System.out.println("Unrecognized option, please try again");
						break;
					}
				}
				else {
					needExit = true;
					System.out.println("Exiting");
				}
			}
		} catch (Exception e) {
			System.out.println(String.format("Error occured: %s. Now exiting.", e.getLocalizedMessage()));
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> GetMenuActions() {
		ArrayList<String> menuActions = new ArrayList<>();
		menuActions.add("1. Say hello");
		menuActions.add("2. Enter Jira's credentials");
		menuActions.add("3. Show Jira's credentials");
		menuActions.add("4. Enter Jira's paths");
		menuActions.add("5. Show Jira's paths");
		menuActions.add("6. Login to Jira");
		menuActions.add("7. Get issues list");
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
		jiraPreferences.setCredentials(login, password);
	}

	private static void ShowJiraCredentials() {
		System.out.println(String.format("Login: %s, password: %s", jiraPreferences.getUsername(), jiraPreferences.getPassword()));
	}
	
	private static void ShowJiraPaths() {
		System.out.println(String.format("Inernal Jira:\n%s\nExternal Jira:\n%s\nRigentJira:\n%s", 
				jiraPreferences.getJiraPath(JiraType.InternalJira),
				jiraPreferences.getJiraPath(JiraType.ExternalJira),
				jiraPreferences.getJiraPath(JiraType.RigentJira)));
	}

	private static void EneterJiraPaths() throws IOException {
		System.out.println("Enter internal jira path");
		String internalJiraPath = new BufferedReader(new InputStreamReader(System.in)).readLine();
		System.out.println("Enter external jira path");
		String externalJiraPath = new BufferedReader(new InputStreamReader(System.in)).readLine();
		System.out.println("Enter rigent jira path");
		String rigentJiraPath = new BufferedReader(new InputStreamReader(System.in)).readLine();
		
		jiraPreferences.setJiraPath(JiraType.InternalJira, internalJiraPath);
		jiraPreferences.setJiraPath(JiraType.ExternalJira, externalJiraPath);
		jiraPreferences.setJiraPath(JiraType.RigentJira, rigentJiraPath);
		
	}
	
	private static void LoginToJira() throws Exception {
		System.out.println("Enter jira name [InternalJira, ExternalJira, RigentJira]");
		String jiraName = new BufferedReader(new InputStreamReader(System.in)).readLine();
		jiraWorker.Login(JiraType.valueOf(jiraName));
	}
	
	private static void GetIssuesList() throws Exception {
		System.out.println("Enter jira name [InternalJira, ExternalJira, RigentJira]");
		String jiraName = new BufferedReader(new InputStreamReader(System.in)).readLine();
		InputStream stream = jiraWorker.LoadIssuesListThisMonth(JiraType.valueOf(jiraName));
		
		ArrayList<String> issues = jiraWorker.ConvertIssuesListFromXMLToArrayOfNames(stream);
		
		for (String issue : issues) {
			System.out.println(issue);
		}
	}
}
