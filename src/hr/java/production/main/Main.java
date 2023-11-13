package hr.java.production.main;

import hr.java.production.enums.Cities;
import hr.java.production.exception.ConflictingArticlesException;
import hr.java.production.exception.ConflictingCategoryException;
import hr.java.production.generics.FoodStore;
import hr.java.production.generics.TechnicalStore;
import hr.java.production.model.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import hr.java.production.sort.ProductionSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    final static int NUMBER_OF_CATEGORIES = 3;
    final static int NUMBER_OF_ITEMS = 5;
    final static int NUMBER_OF_FACTORIES = 2;
    final static int NUMBER_OF_STORES = 2;

    /**
     * sluzi za unos kategorija
     *
     * @param scanner sluzi za input
     * @return vraca unesene kategorije
     */
    static ArrayList<Category> inputCategories(Scanner scanner) {
        ArrayList<Category> categories = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_CATEGORIES; i++){
            String name = "";
            boolean error = true;
            System.out.println("Unesi naziv " + (i + 1) + " kategorije:");
            do {
                try {
                    name = scanner.nextLine();
                    checkDuplicateCategory(name, categories);
                    error = false;
                } catch (ConflictingCategoryException e) {
                    System.out.println(e.getMessage());
                    logger.error(e.getMessage());
                }
            } while(error);

            System.out.println("Unesi opis " + (i+1) + ". kategorije: ");
            String desc = scanner.nextLine();
            categories.add(new Category(name, desc));
        }
        return categories;
    }

    /**
     * sluzi za provjeru duplikata unesene kategorije
     *
     * @param name ime
     * @param categories kategorije
     */
    static void checkDuplicateCategory(String name, ArrayList<Category> categories) {
        for (Category cat : categories) {
            if (cat.getName().compareTo(name) == 0) {
                throw new ConflictingCategoryException("Kategorija vec postoji, unesi drugu.");
            }
        }
    }

    /**
     * sluzi za unos artikala
     *
     * @param scanner sluzi za input
     * @param categories kategorije
     * @return vraca unesene artikle
     */
    static ArrayList<Item> inputItems(Scanner scanner, ArrayList<Category> categories) {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            System.out.println("Odaberi vrstu proizvoda:\n1 Hrana\n2 Laptop\n3 Ostalo");
            int typeOfItem = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Odaberi jednu kategoriju: ");
            for (int j = 0; j < categories.size(); j++){
                System.out.println((j+1) + " " + categories.get(j).getName());
            }
            int categoryChoice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Unesi naziv " + (i+1) + ". artikla: ");
            String name = scanner.nextLine();
            Category category = categories.get(categoryChoice - 1);
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
                            items.add(new Poultry(name, category, width, height, length, productionCost, sellingPrice, weight, discount));
                            break;
                        case 2 :
                            items.add(new Beef(name, category, width, height, length, productionCost, sellingPrice, weight, discount));
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Unesi duljinu trajanja garancije: ");
                    int warranty = scanner.nextInt();
                    scanner.nextLine();
                    items.add(new Laptop(name, category, width, height, length, productionCost, sellingPrice, discount, warranty));
                    break;
                case 3:
                    items.add(new Item(name, category, width, height, length, productionCost, sellingPrice, discount));
                    break;
            }
        }
        return items;
    }


    /**
     * sluzi za unos tvornica
     *
     * @param scanner sluzi za input
     * @param items artikli
     * @return vraca unesene tvornice
     */
    static ArrayList<Factory> inputFactories(Scanner scanner, ArrayList<Item> items) {
           ArrayList<Factory> factories = new ArrayList<>();
           for (int i = 0; i < NUMBER_OF_FACTORIES; i++){
               System.out.println("Unesi naziv " + (i+1) + ". tvornice: ");
               String name = scanner.nextLine();
               System.out.println("Unesi ulicu " + (i+1) + ". tvornice");
               Address address = new Address.Builder(scanner.nextLine()).build();
               System.out.println("Unesi broj ulice  " + (i+1) + ". tvornice");
               address.setHouseNumber(scanner.nextLine());
               System.out.println("Odaberi grad  " + (i+1) + ". tvornice");
               int c = 1;
               for (Cities city : Cities.values()) {
                   System.out.println(c++ + " " + city.getName() + ", " + city.getPostalCode());
               }
               int cityChoice = scanner.nextInt();
               scanner.nextLine();
               Cities city = null;
               switch (cityChoice) {
                   case 1 :
                       city = Cities.ZAGREB;
                       break;
                   case 2 :
                       city = Cities.ZABOK;
                       break;
                   case 3 :
                       city = Cities.KRAPINA;
                       break;
                   case 4 :
                       city = Cities.KARLOVAC;
               }
               address.setCity(city);
               System.out.println("Odaberi artikle, jedan po jedan: ");
               int itemChoice = -1;
               Set<Item> selectedItems = new HashSet<>();
               do {
                   for (int j = 0; j < items.size(); j++) {
                       System.out.println(j + 1 + " " + items.get(j).getName());
                   }
                   try {
                       itemChoice = scanner.nextInt();
                       scanner.nextLine();
                       if (itemChoice == 0) break;

                   } catch (InputMismatchException e) {
                       System.out.println("Unesite numeričku vrijednost");
                       logger.error(e.getMessage());
                   }
                   try {
                       checkDuplicateItem(items.get(itemChoice - 1), selectedItems);
                       selectedItems.add(items.get(itemChoice - 1));
                   } catch (ConflictingArticlesException e) {
                       System.out.println(e.getMessage());
                       logger.error(e.getMessage());
                   }
               } while (true);

               factories.add(new Factory(name, address, selectedItems));
           }
           return factories;
    }

    /**
     * sluzi za unos trgovina
     *
     * @param scanner sluzi za input
     * @param items artikli
     * @return vraca unesene trgovine
     */
    static ArrayList<Store> inputStores(Scanner scanner, ArrayList<Item> items) {
        ArrayList<Store> stores = new ArrayList<Store>();
        for (int i = 0; i < NUMBER_OF_STORES; i++) {
            System.out.println("Unesi ime " + (i+1) + ". trgovine: ");
            String name = scanner.nextLine();
            System.out.println("Unesi web adresu " + (i+1) + ". trgovine: ");
            String webAdress = scanner.nextLine();
            System.out.println("Odaberi artikle, jedan po jedan: ");
            int itemChoice = -1;
            Set<Item> selectedItems = new HashSet<>();
            do {
                for (int j = 0; j < items.size(); j++) {
                    System.out.println(j + 1 + " " + items.get(j).getName());
                }
                try {
                    itemChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (itemChoice == 0) break;

                } catch (InputMismatchException e) {
                    System.out.println("Unesite numeričku vrijednost");
                    logger.error(e.getMessage());
                }
                try {
                    checkDuplicateItem(items.get(itemChoice - 1), selectedItems);
                    selectedItems.add(items.get(itemChoice - 1));
                } catch (ConflictingArticlesException e) {
                    System.out.println(e.getMessage());
                    logger.error(e.getMessage());
                }
            } while (true);
            System.out.println("Unesite vrstu trgovine:\n1 Tehnička trgovina\n2 Trgovina hranom");
            int typeOfStore = scanner.nextInt();
            scanner.nextLine();
            stores.add(typeOfStore == 1 ? new TechnicalStore(name, webAdress, selectedItems) : new FoodStore(name, webAdress, selectedItems));
        }
        return stores;
    }

    /**
     * sluzi za provjeru duplikata unesenih artikala
     *
     * @param item artikli
     * @param selectedItems odabrani artikli
     * @throws ConflictingArticlesException iznimka
     */
    static void checkDuplicateItem(Item item, Set<Item> selectedItems) throws ConflictingArticlesException {
        if (selectedItems.contains(item)) throw new ConflictingArticlesException("Artikl vec postoji, unesi drugi.");
    }

    public static void main(String[] args) {

        logger.info("Program je pokrenut.");

        Scanner scanner = new Scanner(System.in);

        ArrayList<Category> categories = inputCategories(scanner);
        logger.info("Unesene su kategorije");
        ArrayList<Item> items = inputItems(scanner, categories);
        logger.info("Uneseni su artikli");
        ArrayList<Factory> factories = inputFactories(scanner, items);
        logger.info("Unesene su tvornice");
        ArrayList<Store> stores = inputStores(scanner, items);
        logger.info("Unesene su trgovine");

        Map<Category, ArrayList<Item>> itemsByCategory = new HashMap<Category, ArrayList<Item>>();
        for (Category c : categories) {
            ArrayList<Item> getItemsByCategory = new ArrayList<>();
            for (Item i : items) {
                if (i.getCategory().equals(c)) getItemsByCategory.add(i);
            }
            itemsByCategory.put(c, sortItemsByPrice(getItemsByCategory));
        }

        Factory maxVolumeFactory = factories.get(0);
        BigDecimal maxVol = new BigDecimal(0);
        for (int i = 0; i < factories.size(); i++) {
            ArrayList<Item> itemsByFactory = new ArrayList<Item>(factories.get(i).getItems());
            for (int j = 0; j < itemsByFactory.size(); j++) {
                BigDecimal itemVol = itemsByFactory.get(j).getWidth().multiply(itemsByFactory.get(j).getHeight()).multiply(itemsByFactory.get(j).getLength());
                if (itemVol.compareTo(maxVol) > 0) {
                    maxVol = itemVol;
                    maxVolumeFactory = factories.get(i);
                }
            }
        }

        Store minCostStore = stores.get(0);
        BigDecimal minCost = BigDecimal.valueOf(Integer.MAX_VALUE);
        for (int i = 0; i < stores.size(); i++) {
            ArrayList<Item> itemsByStore = new ArrayList<Item>(stores.get(i).getItems());
            for (int j = 0; j < itemsByStore.size(); j++) {
                BigDecimal itemCost = itemsByStore.get(j).getSellingPrice();
                if (itemCost.compareTo(minCost) < 0) {
                    minCost = itemCost;
                    minCostStore = stores.get(i);
                }
            }
        }

        System.out.println("\nTvornica koja proizvodi artikl s najvecim volumenom je " + maxVolumeFactory.getName());
        System.out.println("Trgovina koja prodaje najjeftiniji proizvod je " + minCostStore.getName());

        Integer maxKcal = 0;
        Item maxKcalEdible = items.get(0);
        BigDecimal maxPrice = new BigDecimal(0);
        Item maxPriceEdible = items.get(0);
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Edible) {
                if (((Edible) items.get(i)).calculateKCal() > maxKcal) {
                    maxKcal = ((Edible) items.get(i)).calculateKCal();
                    maxKcalEdible = items.get(i);
                }
                if (((Edible) items.get(i)).calculateSellingPrice().compareTo(maxPrice) > 0) {
                    maxPrice = ((Edible) items.get(i)).calculateSellingPrice();
                    maxPriceEdible = items.get(i);
                }
            }
        }

        System.out.println("\nNamirnica koja ima najveci broj kcal je " + maxKcalEdible.getName());
        System.out.println("Namirnica koja ima najvecu ukupnu cijenu s obzirom na tezinu je " + maxPriceEdible.getName());

        Item shortestWarrantyLaptop = items.get(0);
        Integer shortestWarranty = Integer.MAX_VALUE;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Laptop && ((Laptop) items.get(i)).getWarranty() < shortestWarranty) {
                shortestWarranty = ((Laptop) items.get(i)).getWarranty();
                shortestWarrantyLaptop = items.get(i);
            }
        }

        System.out.println("\nLaptop koji ima najkraci garantni rok je " + shortestWarrantyLaptop.getName());
        System.out.println();


        for (Category c : itemsByCategory.keySet()) {
            System.out.println("Najskuplji artikl iz kategorije " + c.toString() + " je " + itemsByCategory.get(c).get(0).getName());
            System.out.println("Najjeftiniji artikl iz kategorije " + c.toString() + " je " + itemsByCategory.get(c).get(itemsByCategory.get(c).size() - 1).getName());
        }

        System.out.println();

        items = sortItemsByPrice(items);
        ArrayList<Item> edibleItems = new ArrayList<>();
        ArrayList<Item> technicalItems = new ArrayList<>();
        for (Item item : items) {
            boolean b = item instanceof Edible ? edibleItems.add(item) : technicalItems.add(item);
        }

        System.out.println("Najskuplji jestivi artikl je " + edibleItems.get(0).getName() + ", a najjeftiniji " + edibleItems.get(edibleItems.size() - 1).getName());
        System.out.println("Najskuplji tehnicki artikl je " + technicalItems.get(0).getName() + ", a najjeftiniji " + technicalItems.get(edibleItems.size() - 1).getName());

        items.stream().sorted((i1, i2) -> i1.getHeight().multiply(i1.getLength()).multiply(i1.getHeight()).compareTo(i2.getHeight().multiply(i2.getLength()).multiply(i2.getHeight()))).collect(Collectors.toList());

        BigDecimal avgVolume = items.stream()
                .map(item -> item.getVolume())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(items.size()), 2, BigDecimal.ROUND_HALF_UP);

        BigDecimal avgPrice = items.stream()
                .filter(item -> item.getVolume().compareTo(avgVolume) > 0)
                .map(item -> item.getSellingPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(items.size()), 2, BigDecimal.ROUND_HALF_UP);

        System.out.println("\nSrednja cijena svih artikala koji imaju nadprosječni volumen je " + avgPrice);

        double avgItems = stores.stream()
                .mapToInt(store -> store.getItems().size())
                .average().getAsDouble();


        List<Store> aboveAvgItemsStores = (stores.stream()
                .filter(store -> store.getItems().size() > avgItems)).toList();

        System.out.println("\nTrgovine koje imaju nadprosječan broj artikala su ");
        for (Store str : aboveAvgItemsStores) {
            System.out.println(str.getName());
        }

        List<Item> discountedItems = items.stream()
                .filter(item -> item.getDiscount().compareTo(BigDecimal.ZERO) > 0)
                .toList();
        if (!discountedItems.isEmpty()) {
            System.out.println("\nProizvodi pronadeni");
        } else {
            Optional result = Optional.empty();
            System.out.println("\nNe postoje proizvodi s popustom");
        }


    }

    private static ArrayList<Item> sortItemsByPrice(ArrayList<Item> items) {
        Collections.sort(items, new ProductionSorter(true));
        return items;
    }
}
