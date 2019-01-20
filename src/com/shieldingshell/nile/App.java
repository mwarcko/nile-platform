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
		File fileAvailable = new File(FinalsUtils.CAMION_REP + "available");
		LinkedList<CamionID> camionsDisp = new LinkedList<>();
		if(!fileAvailable.exists()) {
			try {
				fileAvailable.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		camionsDisp = fct.readCamion(fileAvailable);
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		while (!exit) {
			System.out.println(
					"What do you want to do (commands : \"help \", \"createorder\", \"truckadd\", \"lstruck\", \"loadorder\", \"exit\") ?");
			String input = sc.nextLine();
			String commande = "";
			String param = "";
			if (!input.contains(" ")) {
				param = "";
				commande = input;
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
				System.out.println("lstruck list the truck you have");
				System.out.println("loadorder to load your orders");
				System.out.println("exit to quit");
				System.out.println("==============HELP=============");
				break;
			case "createorder":
				if (param.equals("")) {
					System.out.println(
							"You need to specify a number of order, type \"help\" if you want to know further");
				} else {
					Long commandeName = fct.createDate();
					File file = new File(FinalsUtils.COMMANDE_REP + commandeName);
					try {
						int nbrCarton = Integer.parseInt(param);
						Commande commandeTest = new Commande(
								fct.orderCommande(fct.applyIdToCartonInCommande(fct.createCommande(nbrCarton))),
								commandeName.toString());
						try {
							fct.writeCommande(file, commandeTest);
							System.out.println("Order created with the commande number :" + commandeName
									+ " it contain " + nbrCarton + " boxes");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} catch (NumberFormatException e) {
						System.out.println("This parameter must be an integer, type \"help\" if you want to know further");
					}
				}
				break;
			case "truckadd":
				if (param.equals("")) {
					System.out.println(
							"You need to specify a type of truck to add, type \"help\" if you want to know further");
				} else {
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
					try {
						fct.writeCamion(new File(FinalsUtils.CAMION_REP + "available"), camionsDisp);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case "lstruck":
				fct.listTruck(camionsDisp, true);
				break;
			case "loadorder":
				List<Commande> commandeToLoad = new LinkedList<>();
				System.out.println("What order do you want to load ? (Number of the order or \"all\")");
				File fileCommande = new File(FinalsUtils.COMMANDE_REP);
				String[] commandeList = fileCommande.list();
				for (String string : commandeList) {
					System.out.println(string);
				}
				String choiceOrder = sc.nextLine();
				if (choiceOrder.equals("all")) {
					for (String choiceOrderOrdered : commandeList) {
						File fileUnique = new File(FinalsUtils.COMMANDE_REP + choiceOrderOrdered);
						if (fileUnique.exists()) {
							commandeToLoad.add(fct.readCommande(fileUnique));
						} else {
							System.out.println("Order " + fileUnique + " doesn't exist !");
						}
					}
				} else {
					String[] choiceOrderSplitted = choiceOrder.split(" ");
					for (String choiceOrderOrdered : choiceOrderSplitted) {
						File fileUnique = new File(FinalsUtils.COMMANDE_REP + choiceOrderOrdered);
						if (fileUnique.exists()) {
							commandeToLoad.add(fct.readCommande(fileUnique));
						} else {
							System.out.println("Order " + fileUnique + " doesn't exist !");
						}
					}
				}
				System.out.println("What truck do you want to load ? (XL,M,S,all)");
				String choiceLoading = sc.nextLine();
				if (choiceLoading.equals("all")) {
					for (CamionID camionID : camionsDisp) {
						while (commandeToLoad.size() != 0) {
							fct.loadTruck(commandeToLoad, camionID.getCamion().getPlace(), camionID);
						}
					}
				} else {
					CamionID vehiculeChoisi = new CamionID();
					int[] vehiculeTab = fct.listTruck(camionsDisp, false);
					switch (choiceLoading) {
					case "XL":
						if (vehiculeTab[0] != 0) {
							vehiculeChoisi = new CamionID(Camion.TYPE_XL);
							fct.loadTruck(commandeToLoad, vehiculeChoisi.getCamion().getPlace(),
									vehiculeChoisi);
							camionsDisp = fct.removeTruck(camionsDisp, Camion.TYPE_XL);
						} else {
							System.out.println("You have no \"XL\" truck available");
						}
						break;
					case "M":
						if (vehiculeTab[1] != 0) {
							vehiculeChoisi = new CamionID(Camion.TYPE_M);
							fct.loadTruck(commandeToLoad, vehiculeChoisi.getCamion().getPlace(),
									vehiculeChoisi);
							camionsDisp = fct.removeTruck(camionsDisp, Camion.TYPE_M);
						} else {
							System.out.println("You have no \"M\" truck available");
						}
						break;
					case "S":
						if (vehiculeTab[2] != 0) {
							vehiculeChoisi = new CamionID(Camion.TYPE_S);
							fct.loadTruck(commandeToLoad, vehiculeChoisi.getCamion().getPlace(),
									vehiculeChoisi);
							camionsDisp = fct.removeTruck(camionsDisp, Camion.TYPE_S);
						} else {
							System.out.println("You have no \"S\" truck available");
						}
						break;
					default:
						System.out.println("\"" + choiceLoading + "\" is an unknown size");
						break;
					}
				}
				break;
			case "exit":
				fileAvailable.delete();
				try {
					fct.writeCamion(new File(FinalsUtils.CAMION_REP + "available"), camionsDisp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("LOL");
					e.printStackTrace();
				}
				exit = true;
				System.out.println("bye bye");
				break;
			default:
				System.out.println("unknow command");
				break;
			}

		}
		sc.close();
	}

//		File file = new File(FinalsUtils.TEST_FOLDER + fct.createDate());
//
//		Commande commandeTest = new Commande(fct.orderCommande(fct.applyIdToCartonInCommande(fct.createCommande(50))));
//		try {
//			fct.writeCommande(file, commandeTest);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		fct.loadTruck(fct.readCommande(file), 48, Camion.TYPE_XL);

//		System.out.println(16%12);
}
