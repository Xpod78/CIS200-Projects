package me.xpod78.proj5;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Project5 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Big 12 Bank Morgtage Calculation Software");
		System.out.println("Developed by Eric Isom");
		System.out.println("");
		System.out.print("Please enter the file name to be used for output (without .txt): ");
		String filename = scanner.nextLine();
		System.out.println("Please choose from one of the following choice below: ");
		System.out.println("    1) Promotional loan ($150,000.00 @ 3.95% for 25 years)");
		System.out.println("    2) Unique Loan (enter in loan values)");
		System.out.println("    3) Quit (Exit Program)");
		System.out.println("");
		System.out.println("    Please enter your selection (1-3): ");
		int loop = 1;
		do{
			String choice = scanner.nextLine();
			int count = Integer.parseInt(choice);
			//Promotional loan - Preset values
			if(count == 1) {
				System.out.println("PROMOTIONAL LOAN...: ");
				String customerId = getCustomerNumber();
				System.out.print("Customer Number: " + customerId);
				double monthlyPayment = calcMonthlyPayment(150000, 3.95, 25);
				double totalPayment = calcTotalPayment(monthlyPayment, 25);
				displayPayments(customerId, 150000, 3.95, 25, monthlyPayment, totalPayment);
			//Custom loan - enter custom values
			}else if(count == 2) {
				System.out.println("UNIQUE LOAN...: ");
				String customerId = getCustomerNumber();
				int loanAmt = getLoanAmount();
				double interest = getInterestRate();
				int term = getLoanTerm();
				double monthlyPayment = calcMonthlyPayment(loanAmt, interest, term);
				double totalPayment = calcTotalPayment(monthlyPayment, term);
				displayPayments(customerId, loanAmt, interest, term, monthlyPayment, totalPayment);
			}else if(count == 3) {
				loop = 0;
				System.out.println("PROGRAM COMPLETE...");
			}else {
				System.out.print("        Invalid choice. Please select 1, 2, or 3: ");
			}
		}while(loop > 0);

		
		
	}
private static void displayPayments(String customerId, int loanAmt, double interest, int term,
			double monthlyPayment, double totalPayment) {
		System.out.print("Customer Number: " + customerId);
		String amount = Integer.toString(loanAmt);
		double amountFormat = Double.parseDouble(amount);
		DecimalFormat formatter = new DecimalFormat("#,###.00");
		System.out.println("Loan Amount: $" + formatter.format(amountFormat));
		System.out.println("Interest rate: " + formatter.format(interest) + "%");
		System.out.println("Monthly Payment: $" + formatter.format(monthlyPayment));
		System.out.println("Total Payment: $" + formatter.format(totalPayment));
	}
private static double calcMonthlyPayment(int amt, double rate, int term) {
		double adjustedRate = rate*0.01;
		int months = term*12;
		double monthlyRate = adjustedRate/12;
		double monthly = (amt*monthlyRate) / 
	            (1-Math.pow(1+monthlyRate, -months));
	       
		return monthly;
	}
private static double calcTotalPayment(double monthly, int years) {
	int months = years*12;
	
	return monthly*months;
}
//Takes the term (amount of time) of the loan
	private static int getLoanTerm() {
		Scanner sc = new Scanner(System.in);
		System.out.print("    Enter the length of the loan(15,20,25,30): ");
		int loop = 1;
		do {
			String term = sc.nextLine();
			if(term.equals("15") || term.equals("20") || term.equals("25") || term.equals("30")) {
				loop = 0;
				return Integer.parseInt(term);
				
			}else {
				System.out.print("        Invalid choice. Please select 15, 20, 25, or 30: ");
			}
		}while(loop > 0);
		return 0;
	}

	private static double getInterestRate() {
		Scanner sc = new Scanner(System.in);
		System.out.print("    Enter the yearly interest rate (Ex: 5.25): ");
		int loop = 1;
		do {
			String interest = sc.nextLine();
			double rate = Double.parseDouble(interest);
			if(rate >= 2 && rate <= 9) {
				
				return rate;
			}else {
				System.out.println("        Valid Interest Rates are 2% - 9%");
				System.out.print("        Please re-enter valid yearly interest rate (Ex: 5.25): ");
			}
			
		}while(loop > 0);
		return 0;
	}

	private static int getLoanAmount() {
		Scanner sc = new Scanner(System.in);
		System.out.print("    Enter the loan amount without $ or commas (Ex: 75500): ");
		int loop = 1;
		do {
			String val = sc.nextLine();
			int convert = Integer.parseInt(val);
			if(convert >= 50000 && convert <= 1000000) {
				loop = 0;
				return convert;
			}else {
				System.out.println("        Valid loan amounts are $50,000 - $1,000,000");
				System.out.print("        Please re-enter the loan amount without $ or commas: ");
				
			}
		}while(loop > 0);
		
		return 0;
	}

	private static String getCustomerNumber() {
		
		Scanner sc = new Scanner(System.in);
		boolean check1 = false;
		boolean check2 = true;
		int loop = 1;
		System.out.print("    Enter the customer number (6-characters and/or digits): ");
		do {
			String id = sc.nextLine();
			char[] id_char = id.toCharArray();
			String comparable = "!@#$%^&*(){}:><?|[]";
			char[] comparable_char = comparable.toCharArray();
			//checking the amount of characters
			if( id.length() == 6) {
				check1 = true;
			}else {
				System.out.println("        Valid customer numbers are 6-characters and/or digits");
			}
			//Comparing characters
			
			for(int x = 0; x<id.length(); x++) {
				char a = id_char[x];
				for(int y = 0; y<comparable.length(); y++) {
					char b = comparable_char[y];
					if( a == b) {
						check2 = false;
						System.out.println("        Valid customer numbers are 6-characters and/or digits");
					}
					
				}
			}
			if(check1 == false && check2 == false) {
				System.out.print("        Please re-enter valid customer number: ");
				check2 = true;
			}else if(check1 == true && check2 == true) {
				loop = 0;
				return id;
			}else {
				System.out.print("        Please re-enter valid customer number: ");
				check2 = true;
			}
		}while(loop > 0);
		
		return null;
	}

}
