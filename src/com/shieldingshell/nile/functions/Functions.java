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
import com.shieldingshell.nile.utils.FinalsUtils;

public class Functions {

	private static Random numberGenerator = new Random();

	public <T> T randomElement(T[] elements) {
		return elements[numberGenerator.nextInt(elements.length)];
	}

	public Long createDate() {
		Date date = new Date();
		return date.getTime();
	}

	public int[] listTruck(List<CamionID> camions, boolean print) {
		int xl = 0;
		int m = 0;
		int s = 0;
		for (CamionID camionID : camions) {
			Camion typeCamion = camionID.getCamion();
			if (typeCamion.equals(Camion.TYPE_XL)) {
				xl++;
			}
			if (typeCamion.equals(Camion.TYPE_M)) {
				m++;
			}
			if (typeCamion.equals(Camion.TYPE_S)) {
				s++;
			}
		}
		int[] camionTab = { xl, m, s };
		if (print) {
			System.out.println("You have " + camions.size() + " trucks available");
			System.out.println(xl + " truck(s) XL " + m + " truck(s) M and " + s + " truck(s) S");
		}
		return camionTab;
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

	public void writeCamion(File file, LinkedList<CamionID> camions) throws IOException {
		try (FileOutputStream fop = new FileOutputStream(file); ObjectOutputStream oop = new ObjectOutputStream(fop)) {
			if (!file.exists()) {
				file.createNewFile();
			}
			oop.writeObject(camions);
		}
	}

	public LinkedList<CamionID> readCamion(File file) {
		LinkedList<CamionID> camions = new LinkedList<>();
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
			while ((camions = (LinkedList<CamionID>) ois.readObject()) != null) {
				System.out.println("Loading truck...");
			}
		} catch (IOException | ClassNotFoundException e) {
			if (e instanceof EOFException) {

			} else {
				e.printStackTrace();
			}
		}
		return camions;
	}

	public Commande readCommande(File file) {
		Commande commande = new Commande();
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
			while ((commande = (Commande) ois.readObject()) != null) {
				System.out.println("Loading order...");
			}
		} catch (IOException | ClassNotFoundException e) {
			if (e instanceof EOFException) {
				System.out.println("OK");
			} else {
				e.printStackTrace();
			}
		}
		return commande;
	}

	public Commande loadTruck(List<Commande> commandes, int spaceTruck, CamionID camionID) {
		Commande commandeRestante = new Commande();
		for (Commande commande : commandes) {
			int storyTruck = camionID.getCamion().getHauteur();
			int space = 1;
			int emptySpaceTruck = spaceTruck;
			int emptyStory = storyTruck;
			List<CartonID> cartons = commande.getCartons();
			List<CartonID> cartonsID = new LinkedList<>();
			List<CartonID> cartonXL = cartons.stream().filter(cartonID -> cartonID.getCarton().getPlace() == 8)
					.collect(Collectors.toList());
			List<CartonID> cartonL = cartons.stream().filter(cartonID -> cartonID.getCarton().getPlace() == 4)
					.collect(Collectors.toList());
			List<CartonID> cartonM = cartons.stream().filter(cartonID -> cartonID.getCarton().getPlace() == 2)
					.collect(Collectors.toList());
			List<CartonID> cartonS = cartons.stream().filter(cartonID -> cartonID.getCarton().getPlace() == 1)
					.collect(Collectors.toList());
			// while there is no carton left in the commande AND there is no story left
			while (!cartons.isEmpty() && emptyStory != 0) {
				// while there is no empty space in the story AND there is no cartons in left in
				// the commande
				while (emptySpaceTruck != 0 && !cartons.isEmpty() && emptyStory != 0) {
					// we load until there is no space enough for a XL carton AND there is no
					// cartonXL left
					while (emptySpaceTruck >= 8 && !cartonXL.isEmpty()) {
						cartonXL.get(0).setStory(storyTruck - emptyStory + 1);
						cartonXL.get(0).setSpace(space);
						space += cartonXL.get(0).getCarton().getPlace();
						cartonsID.add(cartonXL.get(0));
						cartonXL.remove(0);
						emptySpaceTruck -= 8;
					}
					while (emptySpaceTruck >= 4 && !cartonL.isEmpty()) {
						cartonL.get(0).setStory(storyTruck - emptyStory + 1);
						cartonL.get(0).setSpace(space);
						space += cartonL.get(0).getCarton().getPlace();
						cartonsID.add(cartonL.get(0));
						cartonL.remove(0);
						emptySpaceTruck -= 4;
					}
					while (emptySpaceTruck >= 2 && !cartonM.isEmpty()) {
						cartonM.get(0).setStory(storyTruck - emptyStory + 1);
						cartonM.get(0).setSpace(space);
						space += cartonM.get(0).getCarton().getPlace();
						cartonsID.add(cartonM.get(0));
						cartonM.remove(0);
						emptySpaceTruck -= 2;
					}
					while (emptySpaceTruck >= 1 && !cartonS.isEmpty()) {
						cartonS.get(0).setStory(storyTruck - emptyStory + 1);
						cartonS.get(0).setSpace(space);
						space += cartonS.get(0).getCarton().getPlace();
						cartonsID.add(cartonS.get(0));
						cartonS.remove(0);
						emptySpaceTruck -= 1;
					}
					cartons.removeAll(cartons);
					cartons.addAll(cartonXL);
					cartons.addAll(cartonL);
					cartons.addAll(cartonM);
					cartons.addAll(cartonS);
					emptyStory -= 1;
					// we take a new story
					emptySpaceTruck = spaceTruck;
					space = 1;
				}
				System.out.println("Camion loaded with " + cartonsID.size() + " cartons loaded in "
						+ (storyTruck - emptyStory) + " story");
			}
			camionID.setCartons(cartonsID);
			System.out.println(camionID);
			if (emptyStory == 0 && !cartons.isEmpty()) {
				File camionAvailable = new File(FinalsUtils.CAMION_REP + "available");
				System.out.println("The camion " + camionID.getCamion()
						+ " is fully loaded, there is no space left and " + cartons.size() + " cartons left");
				LinkedList<CamionID> camionsDisp = readCamion(camionAvailable);
				camionsDisp.remove(camionID);
				camionAvailable.delete();
				try {
					camionAvailable.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					writeCamion(camionAvailable, camionsDisp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			System.out.println(cartons);
			System.out.println();
			commandeRestante = new Commande(cartons, commande.getCommandeName());
			if (commandeRestante.getCartons().isEmpty()) {
				commandeRestante.setCartons(cartonsID);
				System.out.println("The order " + commandeRestante.getCommandeName() + " has been entirely shipped");
				File fileToDelete = new File(FinalsUtils.COMMANDE_REP + commandeRestante.getCommandeName());
				File fileWithCommandeShipped = new File(
						FinalsUtils.COMMANDE_SHIPPED + commandeRestante.getCommandeName());
				try {
					fileWithCommandeShipped.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					writeCommande(fileWithCommandeShipped, commandeRestante);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				fileToDelete.delete();
			} else {
				cartonsLeftinCommande(commandeRestante);
			}
		}
		return commandeRestante;
	}

	public void cartonsLeftinCommande(Commande commande) {
		File file = new File(FinalsUtils.COMMANDE_REP + commande.getCommandeName() + "bis");
		System.out.println(
				"Boxes who are not in trucks are put in another \"commande\" " + commande.getCommandeName() + "bis");
		try {
			writeCommande(file, commande);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public LinkedList<CamionID> removeTruck(LinkedList<CamionID> camions, Camion camion) {
		LinkedList<CamionID> camionsType = camions.stream().filter(camionsID -> camionsID.getCamion().equals(camion))
				.collect(Collectors.toCollection(LinkedList::new));
		camionsType.remove(0);
		camions = camions.stream().filter(camionsID -> !camionsID.getCamion().equals(camion))
				.collect(Collectors.toCollection(LinkedList::new));
		camions.addAll(camionsType);
		return camions;
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
