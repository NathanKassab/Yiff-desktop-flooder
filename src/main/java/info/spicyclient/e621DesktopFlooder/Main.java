package info.spicyclient.e621DesktopFlooder;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<String> yiffToDownload = YiffScraper.getPosts(250);
		for (String yiff : yiffToDownload) {
			
			// Download it
			
			try {
				Downloader.download(yiff);
			} catch (IOException e) {
				
			}
		}
		
	}

}
