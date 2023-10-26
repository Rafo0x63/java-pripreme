package hr.java.production.main;

import hr.java.production.model.*;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    static Category[] inputCategories(Scanner scanner) {
        Category[] categories = new Category[3];
        for (int i = 0; i < categories.length; i++){
            System.out.println("Unesi naziv " + (i+1) + ". kategorije: ");
            String name = scanner.nextLine();
            System.out.println("Unesi opis " + (i+1) + ". kategorije: ");
            String desc = scanner.nextLine();
            Category category = new Category(name, desc);
            categories[i] = category;
        }
        return categories;
    }

    static Item[] inputItems(Scanner scanner, Category[] categories) {
        Item[] items = new Item[5];
        for (int i = 0; i < items.length; i++) {
            Item item = null;
            System.out.println("Odaberi vrstu proizvoda:\n1 Hrana\n2 Laptop\n3 Ostalo");
            int typeOfItem = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Odaberi jednu kategoriju: ");
            for (int j = 0; j < categories.length; j++){
                System.out.println((j+1) + " " + categories[j].getName());
            }
            int categoryChoice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Unesi naziv " + (i+1) + ". artikla: ");
            String name = scanner.nextLine();
            Category category = categories[categoryChoice - 1];
            System.out.println("Unesi sirinu proizvoda: ");
            BigDecimal width = scanner.nextBigDecimal();
            scanner.nextLine();
            System.out.println("Unesi visinu proizvoda: ");
            BigDecimal height = scanner.nextBigDecimal();
            scanner.nextLine();
            System.out.println("Unesi duljinu proizvoda: ");
            BigDecimal length = scanner.nextBigDecimal();
            scanner.nextLine();
            System.out.println("Unesi cijenu proizvodnje proizvoda: ");
            BigDecimal productionCost = scanner.nextBigDecimal();
            scanner.nextLine();
            System.out.println("Unesi prodajnu cijenu proizvoda: ");
            BigDecimal sellingPrice = scanner.nextBigDecimal();
            scanner.nextLine();
            System.out.println("Unesi popust: ");
            BigDecimal tempDiscount = scanner.nextBigDecimal();
            scanner.nextLine();
            Discount discount = new Discount(tempDiscount.divide(BigDecimal.valueOf(100)));
            if (discount.discountAmount().compareTo(BigDecimal.ZERO) > 0) {
                sellingPrice = sellingPrice.multiply(discount.discountAmount());
            }

            switch (typeOfItem) {
                case 1 :
                    System.out.println("Odaberi podvrstu:\n1 Perad\n2 Goved");
                    int subType = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Unesi težinu proizvoda: ");
                    BigDecimal weight = scanner.nextBigDecimal();
                    scanner.nextLine();
                    switch (subType) {
                        case 1 :
                            item = new Poultry(name, category, width, height, length, productionCost, sellingPrice, weight, discount);
                            break;
                        case 2 :
                            item = new Beef(name, category, width, height, length, productionCost, sellingPrice, weight, discount);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Unesi duljinu trajanja garancije: ");
                    int warranty = scanner.nextInt();
                    scanner.nextLine();
                    item = new Laptop(name, category, width, height, length, productionCost, sellingPrice, discount, warranty);
                    break;
                case 3:
                    item = new Item(name, category, width, height, length, productionCost, sellingPrice, discount);
                    break;
            }

            items[i] = item;
        }
        return items;
    }


    static Factory[] inputFactories(Scanner scanner, Item[] items) {
           Factory[] factories = new Factory[2];
           for (int i = 0; i < factories.length; i++){
               System.out.println("Unesi naziv " + (i+1) + ". tvornice: ");
               String name = scanner.nextLine();
               System.out.println("Unesi ulicu " + (i+1) + ". tvornice");
               Address address = new Address.Builder(scanner.nextLine()).build();
               System.out.println("Unesi broj ulice  " + (i+1) + ". tvornice");
               address.setHouseNumber(scanner.nextLine());
               System.out.println("Unesi grad " + (i+1) + ". tvornice");
               address.setCity(scanner.nextLine());
               System.out.println("Unesi postanski broj " + (i+1) + ". tvornice");
               address.setPostalCode(scanner.nextLine());
               System.out.println("Odaberi artikle, unesi željene brojeve odvojene razmakom: ");
               for (int j = 0; j < items.length; j++) {
                   System.out.println((j+1) + " " + items[j].getName());
               }
               String itemChoiceStr = scanner.nextLine();
               String[] strings = itemChoiceStr.split(" ");
               int[] itemChoiceArr = new int[strings.length];
               for (int j = 0; j < strings.length; j++) {
                   itemChoiceArr[j] = Integer.parseInt(strings[j]);
               }
               Item[] selectedItems = new Item[itemChoiceArr.length];
               for (int j = 0; j < itemChoiceArr.length; j++) {
                   selectedItems[j] = items[itemChoiceArr[j] - 1];
               }
               factories[i] = new Factory(name, address, selectedItems);
           }
           return factories;
    }

    static Store[] inputStores(Scanner scanner, Item[] items) {
        Store[] stores = new Store[2];
        for (int i = 0; i < stores.length; i++) {
            System.out.println("Unesi ime " + (i+1) + ". trgovine: ");
            String name = scanner.nextLine();
            System.out.println("Unesi web adresu " + (i+1) + ". trgovine: ");
            String webAdress = scanner.nextLine();
            System.out.println("Odaberi artikle, unesi željene brojeve odvojene razmakom: ");
            for (int j = 0; j < items.length; j++) {
                System.out.println((j+1) + " " + items[j].getName());
            }
            String itemChoiceStr = scanner.nextLine();
            String[] strings = itemChoiceStr.split(" ");
            int[] itemChoiceArr = new int[strings.length];
            for (int j = 0; j < strings.length; j++) {
                itemChoiceArr[j] = Integer.parseInt(strings[j]);
            }
            Item[] selectedItems = new Item[itemChoiceArr.length];
            for (int j = 0; j < itemChoiceArr.length; j++) {
                selectedItems[j] = items[itemChoiceArr[j] - 1];
            }
            stores[i] = new Store(name, webAdress, selectedItems);
        }
        return stores;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Category[] categories = inputCategories(scanner);
        Item[] items = inputItems(scanner, categories);
        Factory[] factories = inputFactories(scanner, items);
        Store[] stores = inputStores(scanner, items);

        Factory maxVolumeFactory = factories[0];
        BigDecimal maxVol = new BigDecimal(0);
        for (int i = 0; i < factories.length; i++) {
            Item[] itemsByFactory = factories[i].getItems();
            for (int j = 0; j < itemsByFactory.length; j++) {
                BigDecimal itemVol = itemsByFactory[j].getWidth().multiply(itemsByFactory[j].getHeight()).multiply(itemsByFactory[j].getLength());
                if (itemVol.compareTo(maxVol) > 0) {
                    maxVol = itemVol;
                    maxVolumeFactory = factories[i];
                }
            }
        }

        Store minCostStore = stores[0];
        BigDecimal minCost = BigDecimal.valueOf(Integer.MAX_VALUE);
        for (int i = 0; i < stores.length; i++) {
            Item[] itemsByStore = stores[i].getItems();
            for (int j = 0; j < itemsByStore.length; j++) {
                BigDecimal itemCost = itemsByStore[j].getSellingPrice();
                if (itemCost.compareTo(minCost) < 0) {
                    minCost = itemCost;
                    minCostStore = stores[i];
                }
            }
        }

        System.out.println("\nTvornica koja proizvodi artikl s najvecim volumenom je " + maxVolumeFactory.getName());
        System.out.println("Trgovina koja prodaje najjeftiniji proizvod je " + minCostStore.getName());

        Integer maxKcal = 0;
        Item maxKcalEdible = items[0];
        BigDecimal maxPrice = new BigDecimal(0);
        Item maxPriceEdible = items[0];
        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof Edible) {
                if (((Edible) items[i]).calculateKCal() > maxKcal) {
                    maxKcal = ((Edible) items[i]).calculateKCal();
                    maxKcalEdible = items[i];
                }
                if (((Edible) items[i]).calculateSellingPrice().compareTo(maxPrice) > 0) {
                    maxPrice = ((Edible) items[i]).calculateSellingPrice();
                    maxPriceEdible = items[i];
                }
            }
        }

        System.out.println("\nNamirnica koja ima najveci broj kcal je " + maxKcalEdible.getName());
        System.out.println("Namirnica koja ima najvecu ukupnu cijenu s obzirom na tezinu je " + maxPriceEdible.getName());

        Item shortestWarrantyLaptop = items[0];
        Integer shortestWarranty = Integer.MAX_VALUE;
        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof Laptop && ((Laptop) items[i]).getWarranty() < shortestWarranty) {
                shortestWarranty = ((Laptop) items[i]).getWarranty();
                shortestWarrantyLaptop = items[i];
            }
        }

        System.out.println("\nLaptop koji ima najkraci garantni rok je " + shortestWarrantyLaptop.getName());
    }
}
