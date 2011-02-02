package model;

import com.google.code.morphia.annotations.*;
import com.google.code.morphia.emul.org.bson.types.ObjectId;
import cv.descriptor.FeatureVector;

@Entity
public class Painting {

	@Id private ObjectId id = new ObjectId();
	private String title;
	private String artist;
	private String paintingId;
	private int descriptorType = 1;
	@Embedded private FeatureVector[] featureVectors;
	
	public Painting setDescriptorType(int type)
	{
		this.descriptorType = type;
		return this;
	}
	
	public int getDescriptorType()
	{
		return this.descriptorType;
	}
	
	public Painting setPaintingId(String pID)
	{
		this.paintingId = pID;
		return this;
	}
	
	public Painting setTitle(String title)
	{
		this.title = title;
		return this;
	}
	
	public Painting setArtist(String artist)
	{
		this.artist = artist;
		return this;
	}
	
	public Painting setFeatureVectors(FeatureVector[] featureVectors)
	{
		this.featureVectors = featureVectors;
		return this;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public String getArtist()
	{
		return this.artist;
	}
	
	public FeatureVector[] getFeatureVectors()
	{
		return this.featureVectors;
	}
	
	public ObjectId getId()
	{
		return this.id;
	}
	
	public String getPaintingId()
	{
		return this.paintingId;
	}
	
	public float getMatchScore(Painting painting, float threshold)
	{
		int matched = 0;
		double minDistance = Double.MAX_VALUE;
		FeatureVector[] otherFeatureVectors = painting.getFeatureVectors();
		for (FeatureVector anfv : otherFeatureVectors) {
			for (int i = 0; i < this.featureVectors.length; ++i) {
				minDistance = Math.min(this.featureVectors[i].getDistance(anfv), minDistance);
			}
			if (minDistance < threshold) matched++;
			minDistance = Double.MAX_VALUE;
		}
		return (float) matched / otherFeatureVectors.length;
	}
}
