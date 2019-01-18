package com.shieldingshell.nile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.shieldingshell.nile.functions.Functions;
import com.shieldingshell.nile.pojos.Camion;
import com.shieldingshell.nile.pojos.CamionID;
import com.shieldingshell.nile.pojos.Commande;
import com.shieldingshell.nile.utils.FinalsUtils;

public class App {

	public static void main(String[] args) throws InterruptedException {
		Functions fct = new Functions();
		List<CamionID> camionsDisp = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		while (!exit) {
			System.out.println(
					"What do you want to do (commands : \"help \", \"createorder\", \"truckadd\", \"lstruck\", \"mkdir\", \"touch\", \"rm\", \"rd\", \"exit\") ?");
			String input = sc.nextLine();
			String commande = "";
			String param = "";
			if (input.equals("exit")) {
				commande = "exit";
				param = "";
			} else if (input.equals("help")) {
				commande = "help";
				param = "";
			} else if (input.equals("lstruck")){
				commande = "lstruck";
				param = "";
			} else {
				String[] inputSplitted = input.split(" ");
				commande = inputSplitted[0];
				param = input.substring(commande.length() + 1, input.length());
				System.out.println(param);
			}
			switch (commande) {
			case "help":
				System.out.println("==============HELP=============");
				System.out.println("createorder X where X is the number of boxes you want to create");
				System.out.println(
						"truckadd X where X is the type of truck (XL, M, S) you want to add. If you want to add two XL truck and one M truck, type \"truckadd XL XL M\"");
				System.out.println("lstruck");
				System.out.println("cp file1 file2 to copy file1 to file2");
				System.out.println("mkdir directory to create a directory");
				System.out.println("touch file to create a file");
				System.out.println("rm file to remove a file");
				System.out.println("rd directory to remove a directory");
				System.out.println("exit to quit");
				System.out.println("==============HELP=============");
				break;
			case "createorder":
				Long commandeName = fct.createDate();
				File file = new File(FinalsUtils.TEST_FOLDER + commandeName);
				try {
					int nbrCarton = Integer.parseInt(param);
					Commande commandeTest = new Commande(
							fct.orderCommande(fct.applyIdToCartonInCommande(fct.createCommande(nbrCarton))));
					try {
						fct.writeCommande(file, commandeTest);
						System.out.println("Order created with the commande number :" + commandeName + " it contain "
								+ nbrCarton + " boxes");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (NumberFormatException e) {
					System.out.println("This parameter must be an integer");
				}
				break;
			case "truckadd":
				String[] paramSplitted = param.split(" ");
				for (String camionSize : paramSplitted) {
					switch (camionSize) {
					case "XL":
						camionsDisp.add(new CamionID(Camion.TYPE_XL));
						break;
					case "M":
						camionsDisp.add(new CamionID(Camion.TYPE_M));
						break;
					case "S":
						camionsDisp.add(new CamionID(Camion.TYPE_S));
						break;
					default:
						System.out.println("Unknow truck size \"" + camionSize + "\"");
						break;
					}
				}
				System.out.println("You have currently " + camionsDisp.size() + " trucks available");
				break;
			case "lstruck":
				fct.listTruck(camionsDisp);
				break;
			case "exit":
				exit = true;
				System.out.println("bye bye");
				break;
			default:
				System.out.println("unknow command");
				break;
			}

		}
		sc.close();

		File file = new File(FinalsUtils.TEST_FOLDER + fct.createDate());

		Commande commandeTest = new Commande(fct.orderCommande(fct.applyIdToCartonInCommande(fct.createCommande(50))));
		try {
			fct.writeCommande(file, commandeTest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		fct.loadTruck(fct.readCommande(file), 48, Camion.TYPE_XL);

//		System.out.println(16%12);
	}

}
