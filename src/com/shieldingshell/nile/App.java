package com.shieldingshell.nile;

import java.io.File;
import java.io.IOException;

import com.shieldingshell.nile.functions.Functions;
import com.shieldingshell.nile.pojos.Camion;
import com.shieldingshell.nile.pojos.Commande;
import com.shieldingshell.nile.utils.FinalsUtils;

public class App {

	public static void main(String[] args) throws InterruptedException {
		// imports
		Functions fct = new Functions();
		
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
