package view;

import exceptions.AddToyException;
import exceptions.InsufficientAmountException;
import exceptions.ToyNotFoundException;
import exceptions.ToyNotLargerThanException;
import mapping.dtos.ToyDto;
import model.TypeToy;
import services.ToyStoreImpl;
import services.impl.ToyServiceImpl;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class ToyStore {
    public static void main(String[] args) throws Exception {
        String op = "1";
        ToyStoreImpl impl = new ToyServiceImpl();

        do {
            Scanner s = new Scanner(System.in);
            System.out.println("----MENU----");
            System.out.println("1.List toys \n2.Add toy  \n3.Max toy \n4.Min toy \n5.Sort toys \n6.Search \n7.Increase" +
                    "\n8.Decrease \n9.Show by Types \n10.Show larger than \n11.Verify Exist \n12.Show total amount of Toys" +
                    "\n13.Exit");
            op = s.next();
            switch (op) {
                case "1" -> {
                    CompletableFuture<List<ToyDto>> future = CompletableFuture.supplyAsync(() -> {
                        System.out.println("----LIST TOYS----");
                        List<ToyDto> toyList = impl.listToys();
                        if (!toyList.isEmpty()) {
                            for (ToyDto toy : toyList) {
                                System.out.println(toy);
                                System.out.println("Loading toy...");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            System.out.println("There are no toys on the list yet");
                        }
                        return toyList;
                    });
                    future.get();
                    System.out.println("the task is done!");
                }
                case "2" -> {
                    /*CompletableFuture<List<ToyDto>> future = CompletableFuture.supplyAsync(() -> {
                        try {
                            System.out.println("----ADD TOY----");
                            System.out.println("Enter the name: ");
                            String name = s.next();
                            System.out.println("Choose the type: \n0 is Female\n1 is Male\n2 is Unisex: ");
                            TypeToy type = TypeToy.getTypeByValue(Integer.parseInt(s.next()));
                            System.out.println("Enter the price: ");
                            Integer price = Integer.valueOf(s.next());
                            impl.addToy(new ToyDto(name, type, price, +1));
                        } catch (AddToyException e){
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    });
                    future.get();
                    System.out.println("the task is done!");*/
                }

                case "3" -> {
                    CompletableFuture<List<ToyDto>> future = CompletableFuture.supplyAsync(() -> {
                        System.out.println("----MAX TOY----");
                        try {
                            System.out.println("Loading the higher toy type...");
                            Thread.sleep(3000);
                            System.out.println(impl.maxToy());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    });
                    future.get();
                    System.out.println("the task is done!");
                }
                case "4" -> {
                    CompletableFuture<List<ToyDto>> future = CompletableFuture.supplyAsync(() -> {
                        System.out.println("----MIN TOY----");
                        try {
                            System.out.println("Loading the lowest toy type...");
                            Thread.sleep(3000);
                            System.out.println(impl.minToy());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    });
                    future.get();
                    System.out.println("the task is done!");
                }
                case "5" -> {
                    CompletableFuture<List<ToyDto>> future = CompletableFuture.supplyAsync(() -> {
                        System.out.println("----SORT TOYS----");
                        try {
                            System.out.println("Sorting the toys....");
                            Thread.sleep(2000);
                            System.out.println(impl.sort());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    });
                    future.get();
                    System.out.println("the task is done!");
                }
                case "6" -> {
                    CompletableFuture<List<ToyDto>> future = CompletableFuture.supplyAsync(() -> {
                        System.out.println("----SEARCH----");
                        System.out.println("Enter the toy name: ");
                        try {
                            String name = s.next();
                            System.out.println("Looking for the "+ name + "...");
                            Thread.sleep(2000);
                            System.out.println(impl.search(name));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    });
                    future.get();
                    System.out.println("the task is done!");
                }
                case "7" -> {
                    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                        System.out.println("----INCREASE----");
                        System.out.println("Enter the toy name: ");
                        try {
                            String name = s.next();
                            System.out.println("Enter the amount: ");
                            int amount = Integer.parseInt(s.next());
                            ToyDto toyDto = impl.search(name);
                            impl.increase(toyDto, amount);

                            System.out.println(impl.search(name));
                        } catch (InsufficientAmountException | ToyNotFoundException e) {
                            System.out.println(e.getMessage());
                        }catch (NumberFormatException e) {
                            System.out.println("Error: Invalid quantity format.");
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    future.get();
                    System.out.println("the task is done!");
                }
                case "8" -> {
                    CompletableFuture<List<ToyDto>> future = CompletableFuture.supplyAsync(() -> {
                        try {
                            System.out.println("----DECREASE----");
                            System.out.println("Enter the toy name: ");
                            String name = s.next();
                            System.out.println("Enter the amount: ");
                            int amount = Integer.parseInt(s.next());
                            ToyDto toyDto = impl.search(name);
                            impl.decrease(toyDto, amount);

                            System.out.println(impl.search(name));
                        } catch (InsufficientAmountException e){
                            System.out.println(e.getMessage());
                        }catch (ToyNotFoundException e){
                            System.out.println(e.getMessage());
                        }catch (NumberFormatException e) {
                            System.out.println("Error: Invalid quantity format.");
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    });
                    future.get();
                    System.out.println("the task is done!");
                }
                case "9" -> {
                    CompletableFuture<List<ToyDto>> future = CompletableFuture.supplyAsync(() -> {
                        try {
                            System.out.println("----SHOW BY TYPES----");
                            System.out.println(impl.showByType());
                            System.out.println("Loading...");
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    });
                    future.get();
                    System.out.println("the task is done!");
                }
                case "10" -> {
                    CompletableFuture<List<ToyDto>> future = CompletableFuture.supplyAsync(() -> {
                        try {
                            System.out.println("----SHOW LARGER THAN----");
                            System.out.println("Enter the number: ");
                            double number = Integer.parseInt(s.next());
                            List<ToyDto> toyList = impl.showLargerThan(number);
                            for (ToyDto toy : toyList) {
                                System.out.println(toy);
                                System.out.println("Loading toy...");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (NumberFormatException e) {
                            System.out.println("Error: Invalid quantity format.");
                        }catch (ToyNotLargerThanException e){
                            System.out.println(e.getMessage());
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    });
                    future.get();
                    System.out.println("the task is done!");
                }
                case "11" -> {
                    CompletableFuture<List<ToyDto>> future = CompletableFuture.supplyAsync(() -> {
                        try {
                            System.out.println("----VERIFY EXIST----");
                            System.out.println("Enter the toy name: ");
                            String name = s.next();
                            System.out.println("Looking for " + name + "...");
                            Thread.sleep(2000);
                            if (impl.verifyExist(name)) {
                                System.out.println("The toy " + name + " exists in the list");
                            } else {
                                System.out.println("The toy does not exist in the list");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    });
                    future.get();
                    System.out.println("the task is done!");
                }
                case "12" -> {
                    CompletableFuture<List<ToyDto>> future = CompletableFuture.supplyAsync(() -> {
                        try {
                            System.out.println("----SHOW TOTAL AMOUNT OF TOYS----");
                            System.out.println("In the store are " + impl.totalToys());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    });
                    future.get();
                    System.out.println("the task is done!");
                }
            }
        } while (!op.equals("13")) ;
    }
}