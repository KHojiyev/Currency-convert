package module3.lesson9_API.task1_Converter;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CurrencyConverter {
    public static Currency activeCurrency1;
    public static Currency activeCurrency2;
    public static List<Currency> currencyList = new ArrayList<>();

    public static void main(String[] args) {
        Gson gson = new Gson();
        try {
            URL url = new URL("https://cbu.uz/oz/arkhiv-kursov-valyut/json/");
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
            currencyList = Arrays.asList(gson.fromJson(bufferedReader, Currency[].class));

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("===Welcome==");
        while (true) {
            System.out.println("1.USD 2.EURO 3.CNY 4.UZS-SUM x.Exit");
            System.out.print("_");
            Scanner scanner = new Scanner(System.in);
            String selectCommand = scanner.nextLine();
            double amount;
            int selectCurrency;
            switch (selectCommand) {
                case "1":
                    System.out.println("1.EURO 2.CNY 3.UZS-SUM");
                    scanner = new Scanner(System.in);
                    System.out.print("_");
                    selectCurrency = scanner.nextInt();
                    switch (selectCurrency) {
                        case 2:
                            currencyFinder("USD","CNY");
                            System.out.print("Amount money: ");
                            amount = scanner.nextDouble();
                            result(amount);
                            break;
                        case 1:
                            currencyFinder("USD","EUR");
                            System.out.print("Amount money: ");
                            amount = scanner.nextDouble();
                            result(amount);
                            break;
                        case 3:
                            currencyFinder("USD"," ");
                            System.out.print("Amount money: ");
                            amount = scanner.nextDouble();
                            defaultResult(amount);
                            break;
                        default:
                            break;
                    }
                    break;
                case "2":
                    System.out.println("1.USD 2.CNY 3.UZS-SUM ");
                    scanner = new Scanner(System.in);
                    System.out.print("_");
                    selectCurrency = scanner.nextInt();
                    switch (selectCurrency) {
                        case 2:
                            currencyFinder("EURO","CNY");
                            System.out.print("Amount money: ");
                            amount = scanner.nextDouble();
                            result(amount);
                            break;
                        case 1:
                            currencyFinder("EUR","USD");
                            System.out.print("Amount money: ");
                            amount = scanner.nextDouble();
                            result(amount);
                            break;
                        case 3:
                            currencyFinder("EUR"," ");
                            System.out.print("Amount money: ");
                            amount = scanner.nextDouble();
                            defaultResult(amount);
                            break;
                        default:
                            break;
                    }
                    break;
                case "3":
                    System.out.println("1.USD 2.EURO 3.UZS-SUM ");
                    scanner = new Scanner(System.in);
                    System.out.print("_");
                    selectCurrency = scanner.nextInt();
                    switch (selectCurrency) {
                        case 2:
                            currencyFinder("CNY","USD");
                            System.out.print("Amount money: ");
                            amount = scanner.nextDouble();
                            result(amount);
                            break;
                        case 1:
                            currencyFinder("CNY","EUR");
                            System.out.print("Amount money: ");
                            amount = scanner.nextDouble();
                            result(amount);
                            break;
                        case 3:
                            currencyFinder("CNY"," ");
                            System.out.print("Amount money: ");
                            amount = scanner.nextDouble();
                            defaultResult(amount);
                            break;
                        default:
                            break;
                    }
                    break;
                case "4":
                    System.out.println("1.USD 2.EURO 3.CNY ");
                    scanner = new Scanner(System.in);
                    System.out.print("_");
                    selectCurrency = scanner.nextInt();
                    switch (selectCurrency) {
                        case 2:
                            currencyFinder("EUR"," ");
                            System.out.print("Amount money: ");
                            amount = scanner.nextDouble();
                            fromUZSSUM(amount);
                            break;
                        case 1:
                            currencyFinder("USD"," ");
                            System.out.print("Amount money: ");
                            amount = scanner.nextDouble();
                            fromUZSSUM(amount);
                            break;
                        case 3:
                            currencyFinder("CNY"," ");
                            System.out.print("Amount money: ");
                            amount = scanner.nextDouble();
                            fromUZSSUM(amount);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private static void fromUZSSUM(double amount) {
        double rate = (Double.parseDouble(activeCurrency1.getRate()));
        System.out.println("Amount: "+amount+ " Uzbekistan sum"+" <=> "+"result: "+(amount/rate)+" "+activeCurrency1.getCcyNm_EN());
    }

    private static void defaultResult(double amount) {
        double rate = (Double.parseDouble(activeCurrency1.getRate()));
        System.out.println("Amount: "+amount+" "+activeCurrency1.getCcyNm_EN() +" <=> "+"result: "+(rate*amount)+" ");
    }

    private static void result(double amount) {
        double rate = (Double.parseDouble(activeCurrency1.getRate())/Double.parseDouble(activeCurrency2.getRate()));
        System.out.println("Amount: "+amount+" "+activeCurrency1.getCcyNm_EN() +" <=> "+"result: "+(rate*amount)+" "+activeCurrency2.getCcyNm_EN());
    }

    private static void currencyFinder(String currencyName1,String currencyName2) {
        for (Currency currency : currencyList) {
            if (currency.getCcy().equals(currencyName1)) {
                activeCurrency1 = currency;
            }
        }
        for (Currency currency : currencyList) {
            if(currency.getCcy().equals(currencyName2)){
                activeCurrency2 = currency;
            }
        }
    }


}
