package info.spicyclient.e621DesktopFlooder;

import java.util.ArrayList;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

public class YiffScraper {
	
	public static ArrayList<String> getPosts(int postAmount) {
		String request = "";
		try {
			request = NetworkManager.getNetworkManager().sendGet(new HttpGet("https://e621.net/posts.json?limit=" + postAmount));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<String> posts = new ArrayList<>();
		
		for (int i = 0; i< postAmount; i++) {
			try {
				
				// Get full image
				posts.add(new JSONObject(new JSONObject(new JSONObject(request).toString()).getJSONArray("posts").get(i).toString()).getJSONObject("sample").getString("url"));
				
			} catch (Exception e) {
				
				try {
					
					// Get smaller preview if full image fails
					posts.add(new JSONObject(new JSONObject(new JSONObject(request).toString()).getJSONArray("posts").get(i).toString()).getJSONObject("preview").getString("url"));
					
				} catch (Exception e2) {
					
				}
				
			}
		}
		
		return posts;
		
	}
	
}
