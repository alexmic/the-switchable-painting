package http.core.handler.base;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import javax.imageio.ImageIO;
import util.json.JSONObject;
import com.google.code.morphia.Datastore;
import cv.common.Filter;
import cv.detector.fast.Fast12;
import cv.detector.fast.FastCorner;
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
			String imgPath = path + "/" + pIDChars[0] + "/" + pIDChars[1] + "/" + pID;
			String[] exts = {"png", "jpg", "jpeg", "gif"};
			String ext = "";
			for (int i = 0; i < exts.length; ++i) {
				if (new File(imgPath + "." + exts[i]).exists()) {
					ext = exts[i];
					break;
				}
			}
			String drawnImgPath = path + "/" + pIDChars[0] + "/" + pIDChars[1] + "/" + pID + "_CORNERS." + ext;
			BufferedImage img;
			try {
				long start = System.currentTimeMillis();
				img = Filter.grayScale(ImageIO.read(new File(imgPath + "." + ext)));
				System.out.println("Time for grayscale: " + (System.currentTimeMillis() - start));
				
				// Perform feature detection using FAST12.
				int w = img.getWidth();
				int h = img.getHeight();
				int[][] pixels = new int[h][w];
				start = System.currentTimeMillis();
				for (int y = 0; y < h; ++y) {
					for (int x = 0; x < w; ++x) {
						pixels[y][x] = img.getRGB(x, y) & 0xFF;
					}
				}
				System.out.println("Time for copy: " + (System.currentTimeMillis() - start));
				start = System.currentTimeMillis();
				FastCorner[] results = Fast12.detect(pixels, w, h, 18, 250);
				System.out.println("Time for FAST: " + (System.currentTimeMillis() - start));
				
				// Draw up some rectangles to have something to demonstrate.
				Graphics2D g2 = img.createGraphics();
				g2.setColor(new Color(250, 0 ,0));
				int count = results.length;
				for (int i = 0; i < count; ++i) {
					FastCorner c = results[i];
					g2.drawRect(c.x(), c.y(), 1, 1);
				}
				File out = new File(drawnImgPath);
				ImageIO.write(img, "jpg", out);
				return new JSONObject().put("s", true).put("id", pID).put("msg", "").toString();
			} catch (Exception e) {
				// Wrap up any exceptions to be handled in the HttpJob. Exceptions are errors
				// which will lead to an error response (i.e not 200) and will not contain
				// a response body.
				throw new HttpHandlerErrorException(e);
			}
		} else {
			// If not ID was found, then return an error msg in an 200 response,
			// so that the user knows what they omitted.
			try {
				return new JSONObject().put("s", false).put("msg", "No id was found in request.").toString();
			} catch(Exception e) {
				throw new HttpHandlerErrorException(e);
			}
		}
	}

	@Override
	public String delete(Map<String, String> requestParams) {
		// TODO Auto-generated method stub
		return null;
	}

}
