import kong.unirest.Unirest;

public class ExchangeRateConverter {
    public static void main(String[] args) {
        String url = "https://1150-exchange-rates.azurewebsites.net/latest?base=USD";

        RateResponse response = Unirest.get(url).asObject(RateResponse.class).getBody();
        String dateRequested = response.date;
        double rate = response.rates.EUR;
        System.out.println("Date: " + dateRequested + " Rate: " + rate);
    }
}

class RateResponse {
    public String base;
    public String date;
    public Rates rates;

}

class Rates {
    public double EUR;
}
