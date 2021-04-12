package info.spicyclient.e621DesktopFlooder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.atomic.AtomicInteger;

public class Downloader {
	
	public static void download(String url) throws IOException {
		
		new Thread("Yiff downloader thread") {
			@Override
			public void run() {
				
				InputStream in;
				try {
					in = new URL(url).openStream();
					Files.copy(in,
							new File(System.getProperty("user.home") + "/Desktop",
									url.substring(url.length() - 35) + "-e621.net___" + url.substring(url.length() - 4)).toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}.start();
	}
	
}
