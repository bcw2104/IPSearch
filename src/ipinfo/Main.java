package ipinfo;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Main {

	public static void main(String[] args) {
		IpHandler handler = new IpHandler();
		Scanner input = new Scanner(System.in);

		System.out.print("Ip Address : ");
		String ip = input.nextLine();

		JSONObject data = null;

		try {
			data = handler.getInfo(ip);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		handler.printJson(data);

		try {
			data = handler.getCountry(ip);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		handler.printJson(data);
	}

}
