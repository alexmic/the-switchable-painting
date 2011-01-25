package http.core.handler.base;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import javax.imageio.ImageIO;
import com.google.code.morphia.Datastore;
import cv.detector.fast.Fast12;
import cv.detector.fast.FastStruct;
import http.core.handler.Handler;
import http.exception.HttpHandlerErrorException;

public class TestFDHandler implements Handler {
	
	private Datastore ds = null;
	
	public TestFDHandler(Datastore ds)
	{
		this.ds = ds;
	}
	
	@Override
	public String get(Map<String, String> requestParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String post(Map<String, String> requestParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String put(Map<String, String> requestParams) throws HttpHandlerErrorException {
		String path = "/Users/alexis/Desktop/Dev/IndividualProject/server/storage/img";
		if (requestParams.containsKey("id")) {
			String pID = requestParams.get("id");
			char[] pIDChars = pID.toCharArray(); 
			String imgPath = path + "/" + pIDChars[0] + "/" + pIDChars[1] + "/" + pID + ".png";
			String drawnImgPath = path + "/" + pIDChars[0] + "/" + pIDChars[1] + "/" + pID + "_CORNERS.png";
			BufferedImage img;
			try {
				img = ImageIO.read(new File(imgPath));
			
			// Perform feature detection using FAST12.
			Long start = System.currentTimeMillis();
			FastStruct results = Fast12.detect(img, 16);
			System.out.println("FAST found " + results.count() + " corners.");
		    System.out.println(System.currentTimeMillis() - start);
		    // Draw up some rectangles to have something to demonstrate.
		    Graphics2D g2 = img.createGraphics();
		    g2.setColor(new Color(250, 0 ,0));
		    int[] xs = results.xs();
		    int[] ys = results.ys();
		    int count = results.count();
		    for (int i = 0; i < count; ++i) {
		    	g2.drawRect(xs[i], ys[i], 1, 1);
		    }
		    File out = new File(drawnImgPath);
		    ImageIO.write(img, "png", out);
			
		    // okay kinda hacky way of outputting JSON.. fix it later.
			return "{\"s\":true,\"id\":\"" + pID + "\",\"msg\":\"\"}";
			
			} catch (Exception e) {
				// Wrap up any exceptions to be handled in the caller.
				throw new HttpHandlerErrorException(e);
			}
		} else {
			// If not ID was found, then return an error msg in an OK response,
			// so that the user knows what they omitted.
			return "{\"s\":false,\"msg\":\"No id was found in request.\"}";
		}
	}

	@Override
	public String delete(Map<String, String> requestParams) {
		// TODO Auto-generated method stub
		return null;
	}

}
