package info.spicyclient.e621DesktopFlooder;

import java.util.ArrayList;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

public class YiffScraper {
	
	public static void getAndDownloadPosts(int postAmount) {
		
		String request = "";
		try {
			request = NetworkManager.getNetworkManager().sendGet(new HttpGet("https://e621.net/posts.json?limit=" + postAmount));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<String> posts = new ArrayList<>();
		
		for (int i = 0; i< postAmount; i++) {
			
			final int threadSafeI = i;
			final String threadSafeRequest = request;
			
			new Thread("Yiff scraper and downloader thread") {
				
				public void run() {
					
					try {
						
						// Gets and downloads full image
						Downloader.download(new JSONObject(new JSONObject(new JSONObject(threadSafeRequest).toString()).getJSONArray("posts").get(threadSafeI).toString()).getJSONObject("sample").getString("url"));
						
					} catch (Exception e) {
						
						try {
							
							// Gets and downloads smaller preview if full image fails
							Downloader.download(new JSONObject(new JSONObject(new JSONObject(threadSafeRequest).toString()).getJSONArray("posts").get(threadSafeI).toString()).getJSONObject("preview").getString("url"));
							
						} catch (Exception e2) {
							
						}
						
					}
					
				}
				
			}.start();
			
		}
		
	}
	
}
