package com.shieldingshell.nile.functions;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.shieldingshell.nile.pojos.Camion;
import com.shieldingshell.nile.pojos.CamionID;
import com.shieldingshell.nile.pojos.Carton;
import com.shieldingshell.nile.pojos.CartonID;
import com.shieldingshell.nile.pojos.Commande;

public class Functions {

	private static Random numberGenerator = new Random();

	public <T> T randomElement(T[] elements) {
		return elements[numberGenerator.nextInt(elements.length)];
	}

	public long createDate() {
		Date date = new Date();
		return date.getTime();
	}

	public List<Carton> createCommande(int nbreCarton) {
		Carton[] allCommande = Carton.values();
		List<Carton> cartons = new LinkedList<>();
		int i = 0;
		while (i != nbreCarton) {
			Carton randomCarton = randomElement(allCommande);
			cartons.add(randomCarton);
			i++;
		}

		return cartons;
	}

	public List<CartonID> applyIdToCartonInCommande(List<Carton> cartons) throws InterruptedException {
		List<CartonID> cartonsWithID = new LinkedList<>();
		for (Carton carton : cartons) {
			CartonID cartonID = new CartonID(carton, createDate());
			Thread.sleep(1);
			cartonsWithID.add(cartonID);
		}
		return cartonsWithID;
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
	
	public CamionID loadTruck(Commande commande, int spaceTruck, int storyTruck, Camion camion) {
		int emptySpaceTruck = spaceTruck;
		int emptyStory = storyTruck;
		List<CartonID> cartons = commande.getCartons();
		List<CartonID> cartonsID = new LinkedList<>(); 
		while(cartons.size()!=0 || emptyStory == 0) {
			while(emptySpaceTruck!=0 || cartons.size() !=0) {
				// we gather XL
				List<CartonID> cartonXL = cartons.stream().filter(cartonID -> cartonID.getCarton().getPlace() == 8)
						.collect(Collectors.toList());
				//we load until there is no space left
				while(emptySpaceTruck <= 8 || emptyStory == 0) {
					cartonsID.add(cartonXL.get(0));
					cartonXL.remove(0);
				}
				
				// we gather L
				List<CartonID> cartonL = cartons.stream().filter(cartonID -> cartonID.getCarton().getPlace() == 4)
						.collect(Collectors.toList());
				while(emptySpaceTruck <= 4 || emptyStory == 0) {
					cartonsID.add(cartonL.get(0));
					cartonL.remove(0);
				}
				
				// we gather M
				List<CartonID> cartonM = cartons.stream().filter(cartonID -> cartonID.getCarton().getPlace() == 2)
						.collect(Collectors.toList());
				while(emptySpaceTruck <= 2 || emptyStory == 0) {
					cartonsID.add(cartonM.get(0));
					cartonM.remove(0);
				}
				
				// we gather S
				List<CartonID> cartonS = cartons.stream().filter(cartonID -> cartonID.getCarton().getPlace() == 1)
						.collect(Collectors.toList());
				while(emptySpaceTruck <= 1 || emptyStory == 0) {
					cartonsID.add(cartonS.get(0));
					cartonS.remove(0);
				}
			}
		}
		CamionID camionID = new CamionID(camion, cartonsID);
		return camionID;
	}

	public List<CartonID> orderCommande(List<CartonID> cartons) {
		// we gather XL
		List<CartonID> cartonXL = cartons.stream().filter(cartonID -> cartonID.getCarton().getPlace() == 8)
				.collect(Collectors.toList());
		// we gather L
		List<CartonID> cartonL = cartons.stream().filter(cartonID -> cartonID.getCarton().getPlace() == 4)
				.collect(Collectors.toList());
		// we gather M
		List<CartonID> cartonM = cartons.stream().filter(cartonID -> cartonID.getCarton().getPlace() == 2)
				.collect(Collectors.toList());
		// we gather S
		List<CartonID> cartonS = cartons.stream().filter(cartonID -> cartonID.getCarton().getPlace() == 1)
				.collect(Collectors.toList());

		cartonXL.addAll(cartonL);
		cartonXL.addAll(cartonM);
		cartonXL.addAll(cartonS);

		return cartonXL;
	}

	public String[] listDirectory(String currentDirectory) {
		File file = new File(currentDirectory);
		return file.list();
	}

}
