package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Product> list2 = new ArrayList<Product>();
		
		System.out.println("Enter the file path: ");
		
		String strPath = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(strPath))) {
			
			String line = br.readLine();
			
			while(line != null) {
				
				String[] infos = line.split(",");
				
				String name = infos[0];
				Double price = Double.parseDouble(infos[1].trim());
				Integer quantity = Integer.parseInt(infos[2].trim());
				
				//System.out.println(product);
				list2.add(new Product(name, price, quantity));
				line= br.readLine();
			}
						
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		File path = new File(strPath);
		
		File folderPath = new File(path.getParent()+"\\out");
		
		System.out.println("The new folder was created: "+ folderPath.mkdir());
		
		String lastPath = folderPath.getPath() +"\\summary.csv";
		
		//System.out.println(lastPath);
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(lastPath))){
			
			for(Product product : list2) {
				bw.write(String.format("%s, %.2f", product.getName(), product.total()));
				bw.newLine();
			}
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();
	}

}
