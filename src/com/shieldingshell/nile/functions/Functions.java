package com.shieldingshell.nile.functions;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.shieldingshell.nile.pojos.Carton;
import com.shieldingshell.nile.pojos.Commande;

public class Functions {

	private static Random numberGenerator = new Random();

	public <T> T randomElement(T[] elements) {
		return elements[numberGenerator.nextInt(elements.length)];
	}

	public Commande createCommande(int nbreCarton) {
		Carton[] allCommande = Carton.values();
		List<Carton> cartons = new LinkedList<>();
		int i = 0;
		while (i != nbreCarton) {
			Carton randomCarton = randomElement(allCommande);
			cartons.add(randomCarton);
		}
		Commande newCommande = new Commande(cartons);
		return newCommande;
	}

	public void writeCommande(File file, Commande commande) throws IOException {
		try (FileOutputStream fop = new FileOutputStream(file); ObjectOutputStream oop = new ObjectOutputStream(fop)) {
			if (!file.exists()) {
				file.createNewFile();
			}
			oop.writeObject(commande);
		}
	}

	public Commande readCommande(File file) {
		Commande commande = new Commande();
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
			while ((commande = (Commande) ois.readObject()) != null) {
				System.out.println(commande.getCartons());
			}
		} catch (IOException | ClassNotFoundException e) {
			if (e instanceof EOFException) {
				System.out.println("End of file");
			} else {
				e.printStackTrace();
			}
		}
		return commande;
	}

	public String[] listDirectory(String currentDirectory) {
		File file = new File(currentDirectory);
		return file.list();
	}

}
